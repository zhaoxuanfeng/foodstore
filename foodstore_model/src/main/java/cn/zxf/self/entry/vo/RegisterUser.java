package cn.zxf.self.entry.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName RegisterUser
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/21
 */
public class RegisterUser {

    private String accountName;

    private String accountPassword;

    private String email;
    private String name;

    private String realname;



    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    private String sex;

    private String phone;

    private String address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "RegisterUser{" +
                "accountName='" + accountName + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", realname='" + realname + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
