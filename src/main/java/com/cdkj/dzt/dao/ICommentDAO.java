package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.Comment;

public interface ICommentDAO extends IBaseDAO<Comment> {
    String NAMESPACE = ICommentDAO.class.getName().concat(".");

    int update(Comment data);

    int approveComment(Comment data);
}
