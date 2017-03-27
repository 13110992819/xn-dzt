package com.xnjr.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnjr.mall.ao.IStoreAO;
import com.xnjr.mall.bo.IAccountBO;
import com.xnjr.mall.bo.ISmsOutBO;
import com.xnjr.mall.bo.IStoreBO;
import com.xnjr.mall.bo.IStoreTicketBO;
import com.xnjr.mall.bo.IUserBO;
import com.xnjr.mall.bo.base.Paginable;
import com.xnjr.mall.core.OrderNoGenerater;
import com.xnjr.mall.core.StringValidater;
import com.xnjr.mall.domain.Store;
import com.xnjr.mall.domain.StoreTicket;
import com.xnjr.mall.domain.User;
import com.xnjr.mall.dto.req.XN808200Req;
import com.xnjr.mall.dto.req.XN808201Req;
import com.xnjr.mall.dto.req.XN808203Req;
import com.xnjr.mall.dto.req.XN808204Req;
import com.xnjr.mall.enums.EStoreLevel;
import com.xnjr.mall.enums.EStoreStatus;
import com.xnjr.mall.enums.EStoreTicketStatus;
import com.xnjr.mall.enums.EUserKind;
import com.xnjr.mall.exception.BizException;

/** 
 * @author: zuixian 
 * @since: 2016年9月20日 下午1:27:27 
 * @history:
 */
@Service
public class StoreAOImpl implements IStoreAO {

    @Autowired
    private IStoreBO storeBO;

    @Autowired
    private IStoreTicketBO storeTicketBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Override
    @Transactional
    public String addStoreOss(XN808200Req req) {
        Store store = new Store();
        // 验证推荐人是否是平台的已注册用户,将userReferee手机号转化为用户编号
        String systemCode = req.getSystemCode();
        String userReferee = req.getUserReferee();
        String userRefereeUserId = storeBO.isUserRefereeExist(userReferee,
            systemCode);
        store.setUserReferee(userRefereeUserId);
        // 验证B端用户
        String bUser = userBO.isUserExist(req.getMobile(), EUserKind.F2,
            req.getSystemCode());
        if (StringUtils.isBlank(bUser)) { // 注册B端用户
            bUser = userBO.doSaveBUser(req.getMobile(), req.getUpdater(),
                req.getSystemCode(), req.getCompanyCode());
        } else {
            // 判断该用户是否有店铺了
            storeBO.getStoreByUser(bUser);
        }

        String code = OrderNoGenerater.generateM("SJ");
        store.setCode(code);
        store.setName(req.getName());
        store.setLevel(req.getLevel());
        store.setType(req.getType());
        store.setSlogan(req.getSlogan());

        store.setAdvPic(req.getAdvPic());
        store.setPic(req.getPic());
        store.setDescription(req.getDescription());
        store.setProvince(req.getProvince());
        store.setCity(req.getCity());
        store.setArea(req.getArea());
        store.setAddress(req.getAddress());
        store.setLongitude(req.getLongitude());
        store.setLatitude(req.getLatitude());

        store.setBookMobile(req.getBookMobile());
        store.setSmsMobile(req.getSmsMobile());
        store.setPdf(req.getPdf());

        store.setLegalPersonName(req.getLegalPersonName());
        store.setRate1(StringValidater.toDouble(req.getRate1()));
        store.setRate2(StringValidater.toDouble(req.getRate2()));
        store.setStatus(EStoreStatus.PASS.getCode());

        store.setUpdater(req.getUpdater());
        store.setUpdateDatetime(new Date());
        store.setRemark(req.getRemark());
        store.setOwner(bUser);
        store.setContractNo(OrderNoGenerater.generateM("ZHS-"));
        store.setSystemCode(req.getSystemCode());
        store.setCompanyCode(req.getCompanyCode());
        storeBO.saveStoreOss(store);
        return code;
    }

    @Override
    @Transactional
    public String addStore(XN808201Req req) {
        Store data = new Store();
        // 验证推荐人是否是平台的已注册用户,将userReferee手机号转化为用户编号
        String systemCode = req.getSystemCode();
        String userReferee = req.getUserReferee();
        String userId = storeBO.isUserRefereeExist(userReferee, systemCode);
        data.setUserReferee(userId);

        String code = OrderNoGenerater.generateM("SJ");
        data.setCode(code);
        data.setName(req.getName());
        data.setLevel(EStoreLevel.NOMAL.getCode());
        data.setType(req.getType());
        data.setSlogan(req.getSlogan());

        data.setAdvPic(req.getAdvPic());
        data.setPic(req.getPic());
        data.setDescription(req.getDescription());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());

        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setLongitude(req.getLongitude());
        data.setLatitude(req.getLatitude());
        data.setBookMobile(req.getBookMobile());

        data.setSmsMobile(req.getSmsMobile());
        data.setPdf(req.getPdf());
        data.setLegalPersonName(req.getLegalPersonName());
        data.setRate1(StringValidater.toDouble(req.getRate1()));
        data.setRate2(StringValidater.toDouble(req.getRate2()));

        data.setStatus(EStoreStatus.TOCHECK.getCode());

        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        data.setOwner(req.getOwner());
        data.setSystemCode(req.getSystemCode());
        data.setCompanyCode(req.getCompanyCode());
        storeBO.saveStore(data);
        return code;
    }

    @Override
    public void checkStore(String code, String checkResult, String checkUser,
            String remark) {
        Store dbStore = storeBO.getStore(code);
        if (!EStoreStatus.TOCHECK.getCode().equals(dbStore.getStatus())) {
            throw new BizException("xn000000", "商家不处于待审核状态，不能进行审核操作");
        }
        storeBO.checkStore(dbStore, checkResult, checkUser, remark);
    }

    @Override
    public void editStore(XN808203Req req) {
        Store data = new Store();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setType(req.getType());
        data.setLegalPersonName(req.getLegalPersonName());
        data.setRate1(StringValidater.toDouble(req.getRate1()));
        data.setRate2(StringValidater.toDouble(req.getRate2()));

        data.setSlogan(req.getSlogan());
        data.setAdvPic(req.getAdvPic());
        data.setPic(req.getPic());
        data.setDescription(req.getDescription());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());

        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setLongitude(req.getLongitude());
        data.setLatitude(req.getLatitude());
        data.setBookMobile(req.getBookMobile());
        data.setSmsMobile(req.getSmsMobile());
        data.setPdf(req.getPdf());

        data.setStatus(EStoreStatus.TOCHECK.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        storeBO.refreshStore(data);
    }

    @Override
    public void putOn(XN808204Req req) {
        Store dbStore = storeBO.getStore(req.getCode());
        if (EStoreStatus.PASS.getCode().equals(dbStore.getStatus())
                || EStoreStatus.OFF.getCode().equals(dbStore.getStatus())) {
            dbStore.setUiLocation(req.getUiLocation());
            dbStore.setUiOrder(req.getUiOrder());
            dbStore.setRate1(StringValidater.toDouble(req.getRate1()));
            dbStore.setRate2(StringValidater.toDouble(req.getRate2()));
            dbStore.setRate3(StringValidater.toDouble(req.getRate3()));
            dbStore.setIsDefault(req.getIsDefault());

            dbStore.setStatus(EStoreStatus.ON_OPEN.getCode());
            dbStore.setUpdater(req.getUpdater());
            dbStore.setUpdateDatetime(new Date());
            dbStore.setRemark(req.getRemark());
            storeBO.putOn(dbStore);
        } else {
            throw new BizException("xn000000", "当前店铺不是可以上架状态，不能上架操作");
        }
    }

    @Override
    public void putOff(String code, String updater, String remark) {
        Store dbStore = storeBO.getStore(code);
        if (EStoreStatus.ON_OPEN.getCode().equals(dbStore.getStatus())
                || EStoreStatus.ON_CLOSE.getCode().equals(dbStore.getStatus())) {
            dbStore.setStatus(EStoreStatus.OFF.getCode());
            dbStore.setUpdater(updater);
            dbStore.setUpdateDatetime(new Date());
            dbStore.setRemark(remark);
            storeBO.putOff(dbStore);
        } else {
            throw new BizException("xn000000", "当前店铺不是可以下架状态，不能下架操作");
        }

    }

    @Override
    public void closeOpen(String code) {
        Store dbStore = storeBO.getStore(code);
        if (EStoreStatus.ON_OPEN.getCode().equals(dbStore.getStatus())) {
            dbStore.setStatus(EStoreStatus.ON_CLOSE.getCode());
            dbStore.setRemark("商家自行关店");
        } else if (EStoreStatus.ON_CLOSE.getCode().equals(dbStore.getStatus())) {
            dbStore.setStatus(EStoreStatus.ON_OPEN.getCode());
            dbStore.setRemark("商家自行开店");
        } else {
            throw new BizException("xn000000", "上架的店铺才能进行开关店操作");
        }
        dbStore.setUpdater(dbStore.getOwner());
        dbStore.setUpdateDatetime(new Date());
        storeBO.openClose(dbStore);
    }

    @Override
    public void upLevel(String code) {
        Store dbStore = storeBO.getStore(code);
        if (EStoreLevel.NOMAL.getCode().equals(dbStore.getLevel())) {
            dbStore.setLevel(EStoreLevel.FINANCIAL.getCode());
            dbStore.setRemark("商家自行升级成理财型店铺");
        } else {
            throw new BizException("xn000000", "店铺不能进行升级操作");
        }
        dbStore.setUpdater(dbStore.getOwner());
        dbStore.setUpdateDatetime(new Date());
        storeBO.upLevel(dbStore);

    }

    @Override
    public Paginable<Store> queryStorePageFront(int start, int limit,
            Store condition) {
        Paginable<Store> paginable = storeBO.getPaginable(start, limit,
            condition);
        List<Store> storeList = paginable.getList();
        if (CollectionUtils.isNotEmpty(storeList)) {
            for (Store store : storeList) {
                StoreTicket stCondition = new StoreTicket();
                stCondition.setStoreCode(store.getCode());
                stCondition.setStatus(EStoreTicketStatus.ONLINE.getCode());
                List<StoreTicket> storeTickets = storeTicketBO
                    .queryStoreTicketList(stCondition);
                store.setStoreTickets(storeTickets);
            }
        }
        return paginable;
    }

    @Override
    public Paginable<Store> queryStorePageOss(int start, int limit,
            Store condition) {
        return storeBO.getPaginable(start, limit, condition);
    }

    @Override
    public Store getStoreOss(String code) {
        return storeBO.getStore(code);
    }

    @Override
    public Store getStoreFront(String code, String fromUser) {
        Store store = storeBO.getStore(code);
        // 设置推荐人手机号
        String refereeUserId = store.getUserReferee();
        if (StringUtils.isNotBlank(refereeUserId)) {
            User remoteRes = userBO.getRemoteUser(refereeUserId);
            store.setRefereeMobile(remoteRes.getMobile());
        }
        StoreTicket condition = new StoreTicket();
        condition.setStoreCode(store.getCode());
        condition.setStatus(EStoreTicketStatus.ONLINE.getCode());
        store.setStoreTickets(storeTicketBO.queryStoreTicketList(condition));
        return store;

    }

    @Override
    public List<Store> getMyStore(String userId) {
        Store condition = new Store();
        condition.setOwner(userId);
        List<Store> list = storeBO.queryStoreList(condition);
        for (Store store : list) {
            // 设置推荐人手机号
            String refereeUserId = store.getUserReferee();
            if (StringUtils.isNotBlank(refereeUserId)) {
                User remoteRes = userBO.getRemoteUser(refereeUserId);
                store.setRefereeMobile(remoteRes.getMobile());
            }
        }
        return list;
    }
}
