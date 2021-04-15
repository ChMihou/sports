package com.physical.movement.entity;

import com.physical.movement.entity.common.Id;

import java.util.Date;

/**
 * 评论实体
 */
public class Comment extends Id {
    private static final long serialVersionUID = -1207988480570475255L;
    private Integer cid;

    private String comment;

    private Integer cuid;

    private String apply;

    private Integer flag;

    private String aboy;

    private Integer advisoryid;

    public Integer getAdvisoryid() {
        return advisoryid;
    }

    public void setAdvisoryid(Integer advisoryid) {
        this.advisoryid = advisoryid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getAboy() {
        return aboy;
    }

    public void setAboy(String aboy) {
        this.aboy = aboy;
    }
}