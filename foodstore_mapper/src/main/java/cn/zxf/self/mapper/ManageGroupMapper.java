package cn.zxf.self.mapper;

import java.util.List;

import cn.zxf.self.entry.ManageGroup;
import cn.zxf.self.entry.ManageGroupExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ManageGroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_group
     *
     * @mbggenerated
     */
    int countByExample(ManageGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_group
     *
     * @mbggenerated
     */
    int deleteByExample(ManageGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_group
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long manageGroupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_group
     *
     * @mbggenerated
     */
    int insert(ManageGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_group
     *
     * @mbggenerated
     */
    int insertSelective(ManageGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_group
     *
     * @mbggenerated
     */
    List<ManageGroup> selectByExample(ManageGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_group
     *
     * @mbggenerated
     */
    ManageGroup selectByPrimaryKey(Long manageGroupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_group
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ManageGroup record, @Param("example") ManageGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_group
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ManageGroup record, @Param("example") ManageGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ManageGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ManageGroup record);
}