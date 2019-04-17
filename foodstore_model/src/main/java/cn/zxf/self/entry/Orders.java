package cn.zxf.self.entry;

public class Orders {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.order_id
     *
     * @mbggenerated
     */
    private Long orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.order_date
     *
     * @mbggenerated
     */
    private Integer orderDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.order_recipes_id
     *
     * @mbggenerated
     */
    private Integer orderRecipesId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.count
     *
     * @mbggenerated
     */
    private Integer count;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.user_id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.order_status
     *
     * @mbggenerated
     */
    private String orderStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.real_price
     *
     * @mbggenerated
     */
    private Integer realPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.is_delete
     *
     * @mbggenerated
     */
    private Integer isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.notes
     *
     * @mbggenerated
     */
    private String notes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.create_time
     *
     * @mbggenerated
     */
    private Long createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.end_time
     *
     * @mbggenerated
     */
    private Long endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.order_address
     *
     * @mbggenerated
     */
    private String orderAddress;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.order_id
     *
     * @return the value of orders.order_id
     *
     * @mbggenerated
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.order_id
     *
     * @param orderId the value for orders.order_id
     *
     * @mbggenerated
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.order_date
     *
     * @return the value of orders.order_date
     *
     * @mbggenerated
     */
    public Integer getOrderDate() {
        return orderDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.order_date
     *
     * @param orderDate the value for orders.order_date
     *
     * @mbggenerated
     */
    public void setOrderDate(Integer orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.order_recipes_id
     *
     * @return the value of orders.order_recipes_id
     *
     * @mbggenerated
     */
    public Integer getOrderRecipesId() {
        return orderRecipesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.order_recipes_id
     *
     * @param orderRecipesId the value for orders.order_recipes_id
     *
     * @mbggenerated
     */
    public void setOrderRecipesId(Integer orderRecipesId) {
        this.orderRecipesId = orderRecipesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.count
     *
     * @return the value of orders.count
     *
     * @mbggenerated
     */
    public Integer getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.count
     *
     * @param count the value for orders.count
     *
     * @mbggenerated
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.user_id
     *
     * @return the value of orders.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.user_id
     *
     * @param userId the value for orders.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.order_status
     *
     * @return the value of orders.order_status
     *
     * @mbggenerated
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.order_status
     *
     * @param orderStatus the value for orders.order_status
     *
     * @mbggenerated
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.real_price
     *
     * @return the value of orders.real_price
     *
     * @mbggenerated
     */
    public Integer getRealPrice() {
        return realPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.real_price
     *
     * @param realPrice the value for orders.real_price
     *
     * @mbggenerated
     */
    public void setRealPrice(Integer realPrice) {
        this.realPrice = realPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.is_delete
     *
     * @return the value of orders.is_delete
     *
     * @mbggenerated
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.is_delete
     *
     * @param isDelete the value for orders.is_delete
     *
     * @mbggenerated
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.notes
     *
     * @return the value of orders.notes
     *
     * @mbggenerated
     */
    public String getNotes() {
        return notes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.notes
     *
     * @param notes the value for orders.notes
     *
     * @mbggenerated
     */
    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.create_time
     *
     * @return the value of orders.create_time
     *
     * @mbggenerated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.create_time
     *
     * @param createTime the value for orders.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.end_time
     *
     * @return the value of orders.end_time
     *
     * @mbggenerated
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.end_time
     *
     * @param endTime the value for orders.end_time
     *
     * @mbggenerated
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.order_address
     *
     * @return the value of orders.order_address
     *
     * @mbggenerated
     */
    public String getOrderAddress() {
        return orderAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.order_address
     *
     * @param orderAddress the value for orders.order_address
     *
     * @mbggenerated
     */
    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress == null ? null : orderAddress.trim();
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", orderRecipesId=" + orderRecipesId +
                ", count=" + count +
                ", userId=" + userId +
                ", orderStatus='" + orderStatus + '\'' +
                ", realPrice=" + realPrice +
                ", isDelete=" + isDelete +
                ", notes='" + notes + '\'' +
                ", createTime=" + createTime +
                ", endTime=" + endTime +
                ", orderAddress='" + orderAddress + '\'' +
                '}';
    }
}