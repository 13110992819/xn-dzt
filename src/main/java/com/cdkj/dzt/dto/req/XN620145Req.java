package com.cdkj.dzt.dto.req;

/**
 * 分页查询客服留言
 * @author: asus 
 * @since: 2017年8月16日 下午2:54:23 
 * @history:
 */
public class XN620145Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 8300843400239005732L;

    // 留言人（选填）
    private String commenter;

    // 接收人（选填）
    private String receiver;

    // 状态（选填）
    private String status;

    // 类型（必填）
    private String type;

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
