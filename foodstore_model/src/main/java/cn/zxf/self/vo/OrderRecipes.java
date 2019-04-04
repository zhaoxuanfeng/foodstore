package cn.zxf.self.vo;

import cn.zxf.self.entry.Recipes;

import java.util.List;

/**
 * @ClassName OrderRegister
 * @Description TODO
 * @Author zxf
 * @DATE 2019/3/13
 */
public class OrderRecipes {

    private Long id;

    private Long userId;

    private Long orderId;

    private Integer recipesId;

    private String foodName;

    private String orderRecipesStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getRecipesId() {
        return recipesId;
    }

    public void setRecipesId(Integer recipesId) {
        this.recipesId = recipesId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getOrderRecipesStatus() {
        return orderRecipesStatus;
    }

    public void setOrderRecipesStatus(String orderRecipesStatus) {
        this.orderRecipesStatus = orderRecipesStatus;
    }


    @Override
    public String toString() {
        return "OrderRecipes{" +
                "id = "+id+
                "userId=" + userId +
                ", orderId=" + orderId +
                ", recipesId=" + recipesId +
                ", foodName='" + foodName + '\'' +
                ", orderRecipesStatus='" + orderRecipesStatus + '\'' +
                '}';
    }
}
