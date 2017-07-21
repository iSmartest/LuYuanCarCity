package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/15
 * My mailbox is 1403241630@qq.com
 */

public class ForumBean {
    public String cmd;
    public int forumMenuid;//0省油方案 1.买车宝典 2.保养须知..
    public int nowPage;
    public String uid;
    public ForumBean(String cmd, int forumMenuid, int nowPage,String uid) {
        this.cmd = cmd;
        this.forumMenuid = forumMenuid;
        this.nowPage = nowPage;
        this.uid = uid;
    }
    public ForumBean(String cmd, String uid, int nowPage) {
        this.cmd = cmd;
        this.nowPage = nowPage;
        this.uid = uid;
    }
    public String result;
    public String resultNote;
    public int totalPage;
    public String des;
    public List<fateList> fateList;



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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<ForumBean.fateList> getFateList() {
        return fateList;
    }

    public void setFateList(List<ForumBean.fateList> fateList) {
        this.fateList = fateList;
    }

    public class fateList{
        public String userName;
        public String userIcon;
        public String content;
        public String date;
        public String tumbNum;
        public String commentNum;
        public List<String> imgList;
        public List<fateZanList> fateZanList;
        public List<fateCommentList> fateCommentList;
        public boolean hasFavort(){
            if(fateZanList!=null && fateZanList.size()>0){
                return true;
            }
            return false;
        }

        public boolean hasComment(){
            if(fateCommentList!=null && fateCommentList.size()>0){
                return true;
            }
            return false;
        }
        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserIcon() {
            return userIcon;
        }

        public void setUserIcon(String userIcon) {
            this.userIcon = userIcon;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTumbNum() {
            return tumbNum;
        }

        public void setTumbNum(String tumbNum) {
            this.tumbNum = tumbNum;
        }

        public String getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(String commentNum) {
            this.commentNum = commentNum;
        }

        public List<String> getImgList() {
            return imgList;
        }

        public void setImgList(List<String> imgList) {
            this.imgList = imgList;
        }

        public List<ForumBean.fateList.fateZanList> getFateZanList() {
            return fateZanList;
        }

        public void setFateZanList(List<ForumBean.fateList.fateZanList> fateZanList) {
            this.fateZanList = fateZanList;
        }

        public List<ForumBean.fateList.fateCommentList> getFateCommentList() {
            return fateCommentList;
        }

        public void setFateCommentList(List<ForumBean.fateList.fateCommentList> fateCommentList) {
            this.fateCommentList = fateCommentList;
        }

        public class fateZanList{
            public String userId;//点赞人的ID
            public String userName;//点赞人的昵称

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }

        public class fateCommentList{
            public String userId;
            public String userIcon;
            public String commentTime;
            public String userName;
            public String comment;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserIcon() {
                return userIcon;
            }

            public void setUserIcon(String userIcon) {
                this.userIcon = userIcon;
            }

            public String getCommentTime() {
                return commentTime;
            }

            public void setCommentTime(String commentTime) {
                this.commentTime = commentTime;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }
        }
    }
}
