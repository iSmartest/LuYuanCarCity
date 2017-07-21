package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/11
 * My mailbox is 1403241630@qq.com
 */

public class OpinionBean {
    public String cmd;
    public String commodityid;
    public int nowPage;
    public OpinionBean(String cmd, String shopid, int nowPage) {
        this.cmd = cmd;
        this.commodityid = shopid;
        this.nowPage = nowPage;
    }
    public String result;
    public String resultNote;
    public int totalPage;
    public List<commodityCommentLists> commodityCommentLists;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultNote() {
        return resultNote;
    }

    public void setResultNote(String resultNote) {
        this.resultNote = resultNote;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<OpinionBean.commodityCommentLists> getCommodityCommentLists() {
        return commodityCommentLists;
    }

    public void setCommodityCommentLists(List<OpinionBean.commodityCommentLists> commodityCommentLists) {
        this.commodityCommentLists = commodityCommentLists;
    }

    public class commodityCommentLists{
        public String userIcon;//用户的头像
        public String userName;//用户名
        public String userComment;//用户的评论
        public String commentTime;//评论的时间
        public String buyTime; //购买时间
        public String commentReply;//商家的回复
        public String commentStarNum;//五星指数
        public List<String> commentPics;//评论时传的图片

        public String getUserIcon() {
            return userIcon;
        }

        public void setUserIcon(String userIcon) {
            this.userIcon = userIcon;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserComment() {
            return userComment;
        }

        public void setUserComment(String userComment) {
            this.userComment = userComment;
        }

        public String getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(String commentTime) {
            this.commentTime = commentTime;
        }

        public String getBuyTime() {
            return buyTime;
        }

        public void setBuyTime(String buyTime) {
            this.buyTime = buyTime;
        }

        public String getCommentReply() {
            return commentReply;
        }

        public void setCommentReply(String commentReply) {
            this.commentReply = commentReply;
        }

        public String getCommentStarNum() {
            return commentStarNum;
        }

        public void setCommentStarNum(String commentStarNum) {
            this.commentStarNum = commentStarNum;
        }

        public List<String> getCommentPics() {
            return commentPics;
        }

        public void setCommentPics(List<String> commentPics) {
            this.commentPics = commentPics;
        }
    }
}
