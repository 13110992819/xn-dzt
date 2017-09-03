package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Swap;
import com.cdkj.dzt.dto.res.XN620144Res;

@Component
public interface ISwapAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addSwap(String type, String commenter, String content);

    public String addSwapReply(String commenter, String content, String receiver);

    public void dropSwap(String code);

    public void editSwap(String type, String commenter, String receiver);

    public Paginable<Swap> querySwapPage(int start, int limit, Swap condition);

    public List<Swap> querySwapList(Swap condition);

    public Swap getSwap(String code);

    public Paginable<Swap> queryMySwapPage(int start, int limit, Swap condition);

    public Paginable<Swap> queryBfrontSwapPage(int start, int limit,
            Swap condition);

    public Paginable<Swap> queryBLYSwapPage(int start, int limit, Swap condition);

    public XN620144Res totalWD(String userId);
}
