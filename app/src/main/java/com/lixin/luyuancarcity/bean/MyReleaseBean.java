package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/18
 * My mailbox is 1403241630@qq.com
 */

public class MyReleaseBean {
    public String cmd;
    public String uid;
    public int nowPage;
    public MyReleaseBean(String cmd, String uid, int noPage) {
        this.cmd = cmd;
        this.uid = uid;
        this.nowPage = noPage;
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

    public List<MyReleaseBean.fateList> getFateList() {
        return fateList;
    }

    public void setFateList(List<MyReleaseBean.fateList> fateList) {
        this.fateList = fateList;
    }

    public class fateList{
        public String fateId;
        public String userId;
        public String userName;
        public String userICon;
        public String content;
        public String date;
        public List<String> imgList;
        public List<fateZanList> fateZanList;

        public String getFateId() {
            return fateId;
        }

        public void setFateId(String fateId) {
            this.fateId = fateId;
        }

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

        public String getUserICon() {
            return userICon;
        }

        public void setUserICon(String userICon) {
            this.userICon = userICon;
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

        public List<String> getImgList() {
            return imgList;
        }

        public void setImgList(List<String> imgList) {
            this.imgList = imgList;
        }

        public List<MyReleaseBean.fateList.fateZanList> getFateZanList() {
            return fateZanList;
        }

        public void setFateZanList(List<MyReleaseBean.fateList.fateZanList> fateZanList) {
            this.fateZanList = fateZanList;
        }

        public List<MyReleaseBean.fateList.fateCommentList> getFateCommentList() {
            return fateCommentList;
        }

        public void setFateCommentList(List<MyReleaseBean.fateList.fateCommentList> fateCommentList) {
            this.fateCommentList = fateCommentList;
        }

        public class fateZanList{
            public String userId;
            public String userName;

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
        public List<fateCommentList> fateCommentList;
        public class fateCommentList{
            public String userId;
            public String userName;
            public String content;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }

}
