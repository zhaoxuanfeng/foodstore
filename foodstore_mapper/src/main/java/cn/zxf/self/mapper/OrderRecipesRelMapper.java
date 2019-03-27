package cn.zxf.self.mapper;

import cn.zxf.self.entry.OrderRecipesRel;
import cn.zxf.self.example.OrderRecipesRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderRecipesRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_recipes_rel
     *
     * @mbggenerated
     */
    int countByExample(OrderRecipesRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_recipes_rel
     *
     * @mbggenerated
     */
    int deleteByExample(OrderRecipesRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_recipes_rel
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_recipes_rel
     *
     * @mbggenerated
     */
    int insert(OrderRecipesRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_recipes_rel
     *
     * @mbggenerated
     */
    int insertSelective(OrderRecipesRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_recipes_rel
     *
     * @mbggenerated
     */
    List<OrderRecipesRel> selectByExample(OrderRecipesRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_recipes_rel
     *
     * @mbggenerated
     */
    OrderRecipesRel selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_recipes_rel
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") OrderRecipesRel record, @Param("example") OrderRecipesRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_recipes_rel
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") OrderRecipesRel record, @Param("example") OrderRecipesRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_recipes_rel
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(OrderRecipesRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_recipes_rel
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(OrderRecipesRel record);

    List<Long>  selectMaxCountRecipes(Long startTime,Long endTime);

}