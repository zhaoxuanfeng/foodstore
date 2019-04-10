package cn.zxf.self.vo;

/**
 * @ClassName UserOrder
 * @Description TODO
 * @Author zxf
 * @DATE 2019/3/13
 */
public class UserOrder {

    private Long orderId;
    private Integer userId;
    private Integer count;
    private Long  recipesId;
    private String recipesName;
    private String orderAddress;
    private String orderNote;
    private Integer orderPrice;

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getRecipesId() {
        return recipesId;
    }

    public void setRecipesId(Long recipesId) {
        this.recipesId = recipesId;
    }

    public String getRecipesName() {
        return recipesName;
    }

    public void setRecipesName(String recipesName) {
        this.recipesName = recipesName;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", count=" + count +
                ", recipesId=" + recipesId +
                ", recipesName='" + recipesName + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderNote='" + orderNote + '\'' +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
