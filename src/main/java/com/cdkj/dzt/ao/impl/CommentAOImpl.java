package com.cdkj.dzt.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.dzt.ao.ICommentAO;
import com.cdkj.dzt.bo.IAccountBO;
import com.cdkj.dzt.bo.IArticleBO;
import com.cdkj.dzt.bo.ICommentBO;
import com.cdkj.dzt.bo.IKeywordBO;
import com.cdkj.dzt.bo.IProductBO;
import com.cdkj.dzt.bo.IUserBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.domain.Article;
import com.cdkj.dzt.domain.Comment;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.dto.res.XN001400Res;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.ECommentStatus;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EReaction;
import com.cdkj.dzt.exception.BizException;

@Service
public class CommentAOImpl implements ICommentAO {

    @Autowired
    private ICommentBO commentBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IArticleBO articleBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IKeywordBO keywordBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    @Transactional
    public String comment(String content, String commer, String parentCode) {
        userBO.getRemoteUser(commer);
        // 判断是否含有关键字
        String status = ECommentStatus.PUBLISHED.getCode();
        EReaction result = keywordBO.checkContent(content);
        if (EReaction.REFUSE.getCode().equals(result.getCode())) {
            status = ECommentStatus.FILTERED.getCode();
        }
        String topCode = parentCode;
        if (parentCode.startsWith(EGeneratePrefix.COMMENT.getCode())) {
            topCode = commentBO.getComment(parentCode).getTopCode();
        }
        Comment data = new Comment();
        String code = OrderNoGenerater.generateME(EGeneratePrefix.COMMENT
            .getCode());
        data.setCode(code);
        // type:0为文章，1为产品
        data.setType(EBoolean.NO.getCode());
        data.setContent(content);
        data.setStatus(status);
        data.setCommer(commer);
        data.setCommentDatetime(new Date());
        data.setParentCode(parentCode);
        data.setTopCode(topCode);
        commentBO.saveComment(data);
        if (ECommentStatus.FILTERED.getCode().equals(status)) {
            code = code + "; filter";
        }
        return code;
    }

    @Override
    public void approveComment(String code, String result, String approver,
            String remark) {
        Comment data = commentBO.getComment(code);
        if (!ECommentStatus.FILTERED.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "评论不在可审核范围内,不能审核");
        }
        String status = ECommentStatus.APPROVE_YES.getCode();
        if (EBoolean.NO.getCode().equals(result)) {
            status = ECommentStatus.APPROVE_NO.getCode();
        }
        commentBO.approveComment(data, status, approver, remark);
    }

    @Override
    public void dropComment(String code) {
        if (!commentBO.isCommentExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        commentBO.removeComment(code);
    }

    @Override
    public Paginable<Comment> queryCommentPage(int start, int limit,
            Comment condition) {
        Paginable<Comment> page = commentBO.getPaginable(start, limit,
            condition);
        List<Comment> commentList = page.getList();
        for (Comment comment : commentList) {
            XN001400Res user = userBO.getRemoteUser(comment.getCommer());
            comment.setCommerRealName(user.getNickname());
            comment.setPhoto(user.getPhoto());
            // type:0为文章，1为产品
            if (EBoolean.NO.getCode().equals(comment.getType())) {
                Article article = articleBO.getArticle(comment.getParentCode());
                comment.setName(article.getTitle());
            } else if (EBoolean.YES.getCode().equals(comment.getType())) {
                Product product = productBO.getProduct(comment.getTopCode());
                comment.setName(product.getModelName());
            }
        }
        return page;
    }

    @Override
    public List<Comment> queryCommentList(Comment condition) {
        return commentBO.queryCommentList(condition);
    }

    @Override
    public Comment getComment(String code) {
        Comment comment = commentBO.getComment(code);
        XN001400Res user = userBO.getRemoteUser(comment.getCommer());
        comment.setCommerRealName(user.getNickname());
        // type:0为文章，1为产品
        if (EBoolean.NO.getCode().equals(comment.getType())) {
            Article article = articleBO.getArticle(comment.getParentCode());
            comment.setName(article.getTitle());
        } else if (EBoolean.YES.getCode().equals(comment.getType())) {
            Product product = productBO.getProduct(comment.getTopCode());
            comment.setName(product.getModelName());
        }
        return comment;
    }

}
