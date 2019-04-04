package cn.zxf.self.mapper;

import cn.zxf.self.entry.UserAddressRel;
import cn.zxf.self.example.UserAddressRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserAddressRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address_rel
     *
     * @mbggenerated
     */
    int countByExample(UserAddressRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address_rel
     *
     * @mbggenerated
     */
    int deleteByExample(UserAddressRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address_rel
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long addressId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address_rel
     *
     * @mbggenerated
     */
    int insert(UserAddressRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address_rel
     *
     * @mbggenerated
     */
    int insertSelective(UserAddressRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address_rel
     *
     * @mbggenerated
     */
    List<UserAddressRel> selectByExample(UserAddressRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address_rel
     *
     * @mbggenerated
     */
    UserAddressRel selectByPrimaryKey(Long addressId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address_rel
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") UserAddressRel record, @Param("example") UserAddressRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address_rel
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") UserAddressRel record, @Param("example") UserAddressRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address_rel
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserAddressRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address_rel
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserAddressRel record);
}