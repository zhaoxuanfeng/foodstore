package cn.zxf.self.vo;

import java.util.List;

/**
 * @ClassName RoleFuncModel
 * @Description TODO
 * @Author zxf
 * @DATE 2019/3/5
 */
public class RoleFuncModel {

    private Long manageRoleId;

    private List<Long> manageFuncIds;

    private String addmodify;

    private String  roleCode;

    private String  roleName;

    private Integer isDelete;

    public Long getManageRoleId() {
        return manageRoleId;
    }

    public void setManageRoleId(Long manageRoleId) {
        this.manageRoleId = manageRoleId;
    }

    public List<Long> getManageFuncIds() {
        return manageFuncIds;
    }

    public void setManageFuncIds(List<Long> manageFuncIds) {
        this.manageFuncIds = manageFuncIds;
    }

    public String getAddmodify() {
        return addmodify;
    }

    public void setAddmodify(String addmodify) {
        this.addmodify = addmodify;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "RoleFuncModel{" +
                "manageRoleId=" + manageRoleId +
                ", manageFuncIds=" + manageFuncIds +
                ", addmodify='" + addmodify + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
