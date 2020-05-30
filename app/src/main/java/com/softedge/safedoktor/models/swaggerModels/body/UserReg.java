package com.softedge.safedoktor.models.swaggerModels.body;

public class UserReg {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String dateofbirth;
    private String phonenumber;
    private Integer gendergroupid;

    public UserReg(String firstname, String lastname, String password, String dateofbirth, String phonenumber, Integer gendergroupid, String username) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateofbirth = dateofbirth;
        this.phonenumber = phonenumber;
        this.gendergroupid = gendergroupid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getGendergroupid() {
        return gendergroupid;
    }

    public void setGendergroupid(Integer gendergroupid) {
        this.gendergroupid = gendergroupid;
    }
}
