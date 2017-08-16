package com.cdkj.dzt.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.ISwapAO;
import com.cdkj.dzt.bo.IOrderBO;
import com.cdkj.dzt.bo.ISwapBO;
import com.cdkj.dzt.bo.IUserBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.Swap;
import com.cdkj.dzt.dto.res.XN001400Res;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.exception.BizException;

@Service
public class SwapAOImpl implements ISwapAO {

    @Autowired
    private ISwapBO swapBO;

    @Autowired
    private IOrderBO orderBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String addSwap(String type, String commenter, String content) {
        String receiver = "0";
        if (EBoolean.YES.getCode().equals(type)) {
            Order order = orderBO.getLastOrder(commenter);
            if (null == order) {
                throw new BizException("xn0000", "您还没有专属顾问");
            }
            receiver = order.getLtUser();
        }
        Long num = swapBO.getTotalCount(commenter);
        Integer orderNo = (int) (num + 1);
        Swap data = new Swap();
        String code = OrderNoGenerater
            .generateM(EGeneratePrefix.SWAP.getCode());
        data.setCode(code);
        data.setType(type);
        data.setCommenter(commenter);
        data.setReceiver(receiver);
        data.setContent(content);
        data.setCommentDatetime(new Date());
        data.setOrderNo(orderNo);
        data.setStatus(EBoolean.NO.getCode());
        swapBO.saveSwap(data);
        return code;
    }

    @Override
    public String addSwapReply(String commenter, String content, String receiver) {
        String type = EBoolean.YES.getCode();
        if (EBoolean.NO.getCode().equals(commenter)) {
            type = EBoolean.NO.getCode();
        }
        Long num = swapBO.getTotalCount(commenter);
        Integer orderNo = (int) (num + 1);
        Swap data = new Swap();
        String code = OrderNoGenerater
            .generateM(EGeneratePrefix.SWAP.getCode());
        data.setCode(code);
        data.setType(type);
        data.setCommenter(commenter);
        data.setReceiver(receiver);
        data.setContent(content);
        data.setCommentDatetime(new Date());
        data.setOrderNo(orderNo);
        data.setStatus(EBoolean.NO.getCode());
        swapBO.saveSwap(data);
        return code;
    }

    @Override
    public void editSwap(String code, String lookUser) {
        Swap swap = swapBO.getSwap(code);
        if (swap.getReceiver().equals(lookUser)) {
            swapBO.refreshSwap(swap);
        }
    }

    @Override
    public void dropSwap(String code) {
        if (!swapBO.isSwapExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        swapBO.removeSwap(code);
    }

    @Override
    public Paginable<Swap> querySwapPage(int start, int limit, Swap condition) {
        Paginable<Swap> page = swapBO.getPaginable(start, limit, condition);
        List<Swap> list = page.getList();
        for (Swap swap : list) {
            if (!EBoolean.NO.getCode().equals(swap.getType())) {
                XN001400Res res = userBO.getRemoteUser(swap.getCommenter());
                XN001400Res receiverRes = userBO.getRemoteUser(swap
                    .getReceiver());
                swap.setCommentName(res.getNickname());
                swap.setReceiveName(receiverRes.getNickname());
            }
        }
        return page;
    }

    @Override
    public List<Swap> querySwapList(Swap condition) {
        return swapBO.querySwapList(condition);
    }

    @Override
    public Swap getSwap(String code) {
        Swap swap = swapBO.getSwap(code);
        if (!EBoolean.NO.getCode().equals(swap.getType())) {
            XN001400Res res = userBO.getRemoteUser(swap.getCommenter());
            XN001400Res receiverRes = userBO.getRemoteUser(swap.getReceiver());
            swap.setCommentName(res.getNickname());
            swap.setReceiveName(receiverRes.getNickname());
        }
        return swap;
    }

    @Override
    public Paginable<Swap> queryMySwapPage(int start, int limit, Swap condition) {
        condition.setOrder("order_no", "asc");
        return swapBO.getPaginable(start, limit, condition);
    }
}
