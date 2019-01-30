package entry;

public class ManageRole {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manage_role.manage_role_id
     *
     * @mbggenerated
     */
    private Long manageRoleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manage_role.role_code
     *
     * @mbggenerated
     */
    private String roleCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manage_role.role_name
     *
     * @mbggenerated
     */
    private String roleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manage_role.is_delete
     *
     * @mbggenerated
     */
    private Integer isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manage_role.create_time
     *
     * @mbggenerated
     */
    private Long createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manage_role.modify_time
     *
     * @mbggenerated
     */
    private Long modifyTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manage_role.manage_role_id
     *
     * @return the value of manage_role.manage_role_id
     *
     * @mbggenerated
     */
    public Long getManageRoleId() {
        return manageRoleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manage_role.manage_role_id
     *
     * @param manageRoleId the value for manage_role.manage_role_id
     *
     * @mbggenerated
     */
    public void setManageRoleId(Long manageRoleId) {
        this.manageRoleId = manageRoleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manage_role.role_code
     *
     * @return the value of manage_role.role_code
     *
     * @mbggenerated
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manage_role.role_code
     *
     * @param roleCode the value for manage_role.role_code
     *
     * @mbggenerated
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manage_role.role_name
     *
     * @return the value of manage_role.role_name
     *
     * @mbggenerated
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manage_role.role_name
     *
     * @param roleName the value for manage_role.role_name
     *
     * @mbggenerated
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manage_role.is_delete
     *
     * @return the value of manage_role.is_delete
     *
     * @mbggenerated
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manage_role.is_delete
     *
     * @param isDelete the value for manage_role.is_delete
     *
     * @mbggenerated
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manage_role.create_time
     *
     * @return the value of manage_role.create_time
     *
     * @mbggenerated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manage_role.create_time
     *
     * @param createTime the value for manage_role.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column manage_role.modify_time
     *
     * @return the value of manage_role.modify_time
     *
     * @mbggenerated
     */
    public Long getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column manage_role.modify_time
     *
     * @param modifyTime the value for manage_role.modify_time
     *
     * @mbggenerated
     */
    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }
}