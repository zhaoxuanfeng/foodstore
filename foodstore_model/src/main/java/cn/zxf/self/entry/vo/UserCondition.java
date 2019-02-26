package cn.zxf.self.entry.vo;

import java.util.Date;

/**
 * @ClassName UserCondition
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/25
 */
public class UserCondition {

    private Long id;
    private String userName;
    private String account;
    private String realname;
    private Date startTime;
    private Date endTime;
    private String sex;
    private String phone;
    private String address;
    private Integer flag;
    private Integer page_size;
    private Integer page_number;
    private Date create_startTime;
    private Date create_endTime;

    private Date modify_startTime;
    private Date modify_endTime;
    private String email;

    public Date getCreate_startTime() {
        return create_startTime;
    }

    public void setCreate_startTime(Date create_startTime) {
        this.create_startTime = create_startTime;
    }

    public Date getCreate_endTime() {
        return create_endTime;
    }

    public void setCreate_endTime(Date create_endTime) {
        this.create_endTime = create_endTime;
    }

    public Date getModify_startTime() {
        return modify_startTime;
    }

    public void setModify_startTime(Date modify_startTime) {
        this.modify_startTime = modify_startTime;
    }

    public Date getModify_endTime() {
        return modify_endTime;
    }

    public void setModify_endTime(Date modify_endTime) {
        this.modify_endTime = modify_endTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public Integer getPage_number() {
        return page_number;
    }

    public void setPage_number(Integer page_number) {
        this.page_number = page_number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
