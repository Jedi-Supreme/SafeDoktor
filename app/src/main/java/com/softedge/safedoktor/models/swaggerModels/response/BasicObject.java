package com.softedge.safedoktor.models.swaggerModels.response;

import java.io.Serializable;

public class BasicObject {
    
    private int id ;
    private String name;
    private String info;
    private String info1;
    private String otherinfo;
    
    //for titles
    private int gendergroupid;

    public BasicObject(String name, String info, String info1) {
        this.name = name;
        this.info = info;
        this.info1 = info1;
    }


    public BasicObject(String name, String info, String info1, String otherinfo) {
        this.name = name;
        this.info = info;
        this.info1 = info1;
        this.otherinfo=otherinfo;
    }


    public BasicObject(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getOtherinfo() {
        return otherinfo;
    }

    public void setOtherinfo(String otherinfo) {
        this.otherinfo = otherinfo;
    }

    public int getGendergroupid() {
        return gendergroupid;
    }

    public void setGendergroupid(int gendergroupid) {
        this.gendergroupid = gendergroupid;
    }
}
