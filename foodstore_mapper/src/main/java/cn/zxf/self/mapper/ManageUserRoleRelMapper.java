package cn.zxf.self.mapper;

import cn.zxf.self.entry.ManageUserRoleRel;
import cn.zxf.self.entry.ManageUserRoleRelExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ManageUserRoleRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_user_role_rel
     *
     * @mbggenerated
     */
    int countByExample(ManageUserRoleRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_user_role_rel
     *
     * @mbggenerated
     */
    int deleteByExample(ManageUserRoleRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_user_role_rel
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long manageUserRoleRelId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_user_role_rel
     *
     * @mbggenerated
     */
    int insert(ManageUserRoleRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_user_role_rel
     *
     * @mbggenerated
     */
    int insertSelective(ManageUserRoleRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_user_role_rel
     *
     * @mbggenerated
     */
    List<ManageUserRoleRel> selectByExample(ManageUserRoleRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_user_role_rel
     *
     * @mbggenerated
     */
    ManageUserRoleRel selectByPrimaryKey(Long manageUserRoleRelId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_user_role_rel
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ManageUserRoleRel record, @Param("example") ManageUserRoleRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_user_role_rel
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ManageUserRoleRel record, @Param("example") ManageUserRoleRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_user_role_rel
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ManageUserRoleRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_user_role_rel
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ManageUserRoleRel record);
}