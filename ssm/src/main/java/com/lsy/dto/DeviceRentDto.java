package com.lsy.dto;

import java.util.List;

/**
 * Created by Administrator on 2017/2/18 0018.
 */
public class DeviceRentDto {

    /**
     * deviceArray : [{"id":"1","name":"car","unit":"辆","price":"80","num":"1","total":80}]
     * fileArray : ["efa382a4-9991-4cb9-b058-aea4542a5115.pdf"]
     * companyName : ss
     * tel :
     * linkNum : ss
     * cardNum :
     * address :
     * fax :
     * rentDate : 2017-02-18
     * backDate :
     * totalDays :
     */

    private String companyName;
    private String tel;
    private String linkMan;
    private String cardNum;
    private String address;
    private String fax;
    private String rentDate;
    private String backDate;
    private Integer totalDays;
    private List<DeviceArrayBean> deviceArray;
    private List<DocBean> fileArray;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public List<DeviceArrayBean> getDeviceArray() {
        return deviceArray;
    }

    public void setDeviceArray(List<DeviceArrayBean> deviceArray) {
        this.deviceArray = deviceArray;
    }

    public List<DocBean> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<DocBean> fileArray) {
        this.fileArray = fileArray;
    }


    public static class DeviceArrayBean {
        /**
         * id : 1
         * name : car
         * unit : 辆
         * price : 80
         * num : 1
         * total : 80
         */

        private Integer id;
        private String name;
        private String unit;
        private float price;
        private Integer num;
        //总租赁费
        private float total;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public float getTotal() {
            return total;
        }


        public void setTotal(float total) {
            this.total = total;
        }
    }

    public static class DocBean {
        private String sourceName;
        private String newName;

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public String getNewName() {
            return newName;
        }

        public void setNewName(String newName) {
            this.newName = newName;
        }
    }
}
