package com.lsy.action;


import java.io.File;

public class FileAction extends BaseAction {
    private String uName;
    private File doc;
    private String docContentType;

    public String execute() throws Exception{
        return SUCCESS;
    }

    public String upload() throws Exception{
    return SUCCESS;

    }

    //set get


    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public File getDoc() {
        return doc;
    }

    public void setDoc(File doc) {
        this.doc = doc;
    }

    public String getDocContentType() {
        return docContentType;
    }

    public void setDocContentType(String docContentType) {
        this.docContentType = docContentType;
    }
}
