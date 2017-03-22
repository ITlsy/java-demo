package com.lsy.action;

import com.lsy.pojo.Bank;
import com.lsy.service.BankService;
import com.lsy.util.QueryParam;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Namespace("/bank")
public class BankAction extends BaseAction {
    @Autowired
    private BankService bankService;
    private Integer id;
    private Bank bank;
    //往前端传值
    private List<Bank> bankList;

    @Action("list")
    public String execute() throws Exception{
        List<QueryParam> queryParamList=QueryParam.builderQueryParamByRequest(getHttpRequest());
        bankList=bankService.findByQueryParam(queryParamList);
       //  bankList=bankService.findAll();
        return SUCCESS;
    }

    @Action("new")
    public String newBank(){
        return SUCCESS;
    }

    @Action(value = "save",results = {
            @Result(type = "redirectAction",params = {"actionName","list"})
    })
    public String saveBank(){
        bankService.save(bank);
        return SUCCESS;
    }

    @Action("edit")
    public String edit(){
       bank=bankService.findById(id);
       return SUCCESS;
    }

    @Action(value = "del",results = {
            @Result(type = "redirectAction",params = {"actionName","list"})
    })
    public String del(){
        bankService.del(id);
        return SUCCESS;
    }




    // get set

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
