package com.example.infraspect;

public class MaterialModel {

    private String data;
    private String pnameformaterial;

    public MaterialModel(){

    }

    public MaterialModel(String data){
        this.data=data;
    }


    public void setdata(String data) {
        this.data=data;
    }

    public String getdata() {
        return data;
    }

    public String getPnameformaterial() {
        return pnameformaterial;
    }

    public void setPnameformaterial(String pnameformaterial) {
        this.pnameformaterial = pnameformaterial;
    }
}
