package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Comment;

@Component
public interface ICommentAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String comment(String content, String commer, String orderCode);

    public void dropComment(String code);

    public void approveComment(String code, String result, String approver,
            String remark);

    public Paginable<Comment> queryCommentPage(int start, int limit,
            Comment condition);

    public List<Comment> queryCommentList(Comment condition);

    public Comment getComment(String code);

}
