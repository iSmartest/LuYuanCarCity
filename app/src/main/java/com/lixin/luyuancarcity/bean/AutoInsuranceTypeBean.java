package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/6/27
 * My mailbox is 1403241630@qq.com
 */

public class AutoInsuranceTypeBean{
    public String result;
    public String resultNote;
    public List<emergencys> emergencys;

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

    public List<AutoInsuranceTypeBean.emergencys> getEmergencys() {
        return emergencys;
    }

    public void setEmergencys(List<AutoInsuranceTypeBean.emergencys> emergencys) {
        this.emergencys = emergencys;
    }

    public class emergencys{
        public String emergencyid;
        public String emergencyIcon;
        public String emergencyTitle;
        public String emergencyDetail;

        public String getEmergencyid() {
            return emergencyid;
        }

        public void setEmergencyid(String emergencyid) {
            this.emergencyid = emergencyid;
        }

        public String getEmergencyIcon() {
            return emergencyIcon;
        }

        public void setEmergencyIcon(String emergencyIcon) {
            this.emergencyIcon = emergencyIcon;
        }

        public String getEmergencyTitle() {
            return emergencyTitle;
        }

        public void setEmergencyTitle(String emergencyTitle) {
            this.emergencyTitle = emergencyTitle;
        }

        public String getEmergencyDetail() {
            return emergencyDetail;
        }

        public void setEmergencyDetail(String emergencyDetail) {
            this.emergencyDetail = emergencyDetail;
        }
    }
}
