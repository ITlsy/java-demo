package com.lsy.dto;

import javax.print.Doc;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class DeviceWorkDto {
    /**
     * workArray : [{"id":"2","name":"搬泥工","price":"100","num":"1","total":100}]
     * fileArray : [{"sourceName":"Android 开发工程师 - 知乎招聘.png","newName":"5f5de7d8-90c6-4544-b331-88c8c806bc18.png"}]
     * companyName : asX
     * address : AZ
     * telephone : az
     * linkMan : sA
     * tel : az
     * cardNum : az
     * beginDate : 2017-02-22
     * lastDate : 2017-02-23
     * totalDays : 1
     */

    private String companyName;
    private String address;
    private String telephone;
    private String linkMan;
    private String tel;
    private String cardNum;
    private String beginDate;
    private String lastDate;
    private Integer totalDays;
    private List<WorkArrayBean> workArray;
    private List<DocBean> fileArray;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public List<WorkArrayBean> getWorkArray() {
        return workArray;
    }

    public void setWorkArray(List<WorkArrayBean> workArray) {
        this.workArray = workArray;
    }

    public List<DocBean> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<DocBean> fileArray) {
        this.fileArray = fileArray;
    }

    public static class WorkArrayBean {
        /**
         * id : 2
         * name : 搬泥工
         * price : 100
         * num : 1
         * total : 100
         */

        private Integer id;
        private String name;
        private float price;
        private Integer num;
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
        /**
         * sourceName : Android 开发工程师 - 知乎招聘.png
         * newName : 5f5de7d8-90c6-4544-b331-88c8c806bc18.png
         */

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
    /**
     * {"workArray":[{"id":"2","name":"搬泥工","price":"100","num":"1","total":100}],
     * "fileArray":[{"sourceName":"Android 开发工程师 - 知乎招聘.png","newName":"5f5de7d8-90c6-4544-b331-88c8c806bc18.png"}],
     * "companyName":"asX","address":"AZ","telephone":"az","linkMan":"sA","tel":"az","cardNum":"az","beginDate":"2017-02-22","lastDate":"2017-02-23","totalDays":"1"}
     */



}