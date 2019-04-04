package cn.zxf.self.example;

import java.util.ArrayList;
import java.util.List;

public class RecipesExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table recipes
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table recipes
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table recipes
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recipes
     *
     * @mbggenerated
     */
    public RecipesExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recipes
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recipes
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recipes
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recipes
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recipes
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recipes
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recipes
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recipes
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recipes
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recipes
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table recipes
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFoodNameIsNull() {
            addCriterion("food_name is null");
            return (Criteria) this;
        }

        public Criteria andFoodNameIsNotNull() {
            addCriterion("food_name is not null");
            return (Criteria) this;
        }

        public Criteria andFoodNameEqualTo(String value) {
            addCriterion("food_name =", value, "foodName");
            return (Criteria) this;
        }

        public Criteria andFoodNameNotEqualTo(String value) {
            addCriterion("food_name <>", value, "foodName");
            return (Criteria) this;
        }

        public Criteria andFoodNameGreaterThan(String value) {
            addCriterion("food_name >", value, "foodName");
            return (Criteria) this;
        }

        public Criteria andFoodNameGreaterThanOrEqualTo(String value) {
            addCriterion("food_name >=", value, "foodName");
            return (Criteria) this;
        }

        public Criteria andFoodNameLessThan(String value) {
            addCriterion("food_name <", value, "foodName");
            return (Criteria) this;
        }

        public Criteria andFoodNameLessThanOrEqualTo(String value) {
            addCriterion("food_name <=", value, "foodName");
            return (Criteria) this;
        }

        public Criteria andFoodNameLike(String value) {
            addCriterion("food_name like", value, "foodName");
            return (Criteria) this;
        }

        public Criteria andFoodNameNotLike(String value) {
            addCriterion("food_name not like", value, "foodName");
            return (Criteria) this;
        }

        public Criteria andFoodNameIn(List<String> values) {
            addCriterion("food_name in", values, "foodName");
            return (Criteria) this;
        }

        public Criteria andFoodNameNotIn(List<String> values) {
            addCriterion("food_name not in", values, "foodName");
            return (Criteria) this;
        }

        public Criteria andFoodNameBetween(String value1, String value2) {
            addCriterion("food_name between", value1, value2, "foodName");
            return (Criteria) this;
        }

        public Criteria andFoodNameNotBetween(String value1, String value2) {
            addCriterion("food_name not between", value1, value2, "foodName");
            return (Criteria) this;
        }

        public Criteria andFoodTypeIsNull() {
            addCriterion("food_type is null");
            return (Criteria) this;
        }

        public Criteria andFoodTypeIsNotNull() {
            addCriterion("food_type is not null");
            return (Criteria) this;
        }

        public Criteria andFoodTypeEqualTo(Integer value) {
            addCriterion("food_type =", value, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeNotEqualTo(Integer value) {
            addCriterion("food_type <>", value, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeGreaterThan(Integer value) {
            addCriterion("food_type >", value, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("food_type >=", value, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeLessThan(Integer value) {
            addCriterion("food_type <", value, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeLessThanOrEqualTo(Integer value) {
            addCriterion("food_type <=", value, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeIn(List<Integer> values) {
            addCriterion("food_type in", values, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeNotIn(List<Integer> values) {
            addCriterion("food_type not in", values, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeBetween(Integer value1, Integer value2) {
            addCriterion("food_type between", value1, value2, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("food_type not between", value1, value2, "foodType");
            return (Criteria) this;
        }

        public Criteria andFoodPriceIsNull() {
            addCriterion("food_price is null");
            return (Criteria) this;
        }

        public Criteria andFoodPriceIsNotNull() {
            addCriterion("food_price is not null");
            return (Criteria) this;
        }

        public Criteria andFoodPriceEqualTo(Integer value) {
            addCriterion("food_price =", value, "foodPrice");
            return (Criteria) this;
        }

        public Criteria andFoodPriceNotEqualTo(Integer value) {
            addCriterion("food_price <>", value, "foodPrice");
            return (Criteria) this;
        }

        public Criteria andFoodPriceGreaterThan(Integer value) {
            addCriterion("food_price >", value, "foodPrice");
            return (Criteria) this;
        }

        public Criteria andFoodPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("food_price >=", value, "foodPrice");
            return (Criteria) this;
        }

        public Criteria andFoodPriceLessThan(Integer value) {
            addCriterion("food_price <", value, "foodPrice");
            return (Criteria) this;
        }

        public Criteria andFoodPriceLessThanOrEqualTo(Integer value) {
            addCriterion("food_price <=", value, "foodPrice");
            return (Criteria) this;
        }

        public Criteria andFoodPriceIn(List<Integer> values) {
            addCriterion("food_price in", values, "foodPrice");
            return (Criteria) this;
        }

        public Criteria andFoodPriceNotIn(List<Integer> values) {
            addCriterion("food_price not in", values, "foodPrice");
            return (Criteria) this;
        }

        public Criteria andFoodPriceBetween(Integer value1, Integer value2) {
            addCriterion("food_price between", value1, value2, "foodPrice");
            return (Criteria) this;
        }

        public Criteria andFoodPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("food_price not between", value1, value2, "foodPrice");
            return (Criteria) this;
        }

        public Criteria andFoodStatusIsNull() {
            addCriterion("food_status is null");
            return (Criteria) this;
        }

        public Criteria andFoodStatusIsNotNull() {
            addCriterion("food_status is not null");
            return (Criteria) this;
        }

        public Criteria andFoodStatusEqualTo(Integer value) {
            addCriterion("food_status =", value, "foodStatus");
            return (Criteria) this;
        }

        public Criteria andFoodStatusNotEqualTo(Integer value) {
            addCriterion("food_status <>", value, "foodStatus");
            return (Criteria) this;
        }

        public Criteria andFoodStatusGreaterThan(Integer value) {
            addCriterion("food_status >", value, "foodStatus");
            return (Criteria) this;
        }

        public Criteria andFoodStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("food_status >=", value, "foodStatus");
            return (Criteria) this;
        }

        public Criteria andFoodStatusLessThan(Integer value) {
            addCriterion("food_status <", value, "foodStatus");
            return (Criteria) this;
        }

        public Criteria andFoodStatusLessThanOrEqualTo(Integer value) {
            addCriterion("food_status <=", value, "foodStatus");
            return (Criteria) this;
        }

        public Criteria andFoodStatusIn(List<Integer> values) {
            addCriterion("food_status in", values, "foodStatus");
            return (Criteria) this;
        }

        public Criteria andFoodStatusNotIn(List<Integer> values) {
            addCriterion("food_status not in", values, "foodStatus");
            return (Criteria) this;
        }

        public Criteria andFoodStatusBetween(Integer value1, Integer value2) {
            addCriterion("food_status between", value1, value2, "foodStatus");
            return (Criteria) this;
        }

        public Criteria andFoodStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("food_status not between", value1, value2, "foodStatus");
            return (Criteria) this;
        }

        public Criteria andFoodCuisineIsNull() {
            addCriterion("food_cuisine is null");
            return (Criteria) this;
        }

        public Criteria andFoodCuisineIsNotNull() {
            addCriterion("food_cuisine is not null");
            return (Criteria) this;
        }

        public Criteria andFoodCuisineEqualTo(String value) {
            addCriterion("food_cuisine =", value, "foodCuisine");
            return (Criteria) this;
        }

        public Criteria andFoodCuisineNotEqualTo(String value) {
            addCriterion("food_cuisine <>", value, "foodCuisine");
            return (Criteria) this;
        }

        public Criteria andFoodCuisineGreaterThan(String value) {
            addCriterion("food_cuisine >", value, "foodCuisine");
            return (Criteria) this;
        }

        public Criteria andFoodCuisineGreaterThanOrEqualTo(String value) {
            addCriterion("food_cuisine >=", value, "foodCuisine");
            return (Criteria) this;
        }

        public Criteria andFoodCuisineLessThan(String value) {
            addCriterion("food_cuisine <", value, "foodCuisine");
            return (Criteria) this;
        }

        public Criteria andFoodCuisineLessThanOrEqualTo(String value) {
            addCriterion("food_cuisine <=", value, "foodCuisine");
            return (Criteria) this;
        }

        public Criteria andFoodCuisineLike(String value) {
            addCriterion("food_cuisine like", value, "foodCuisine");
            return (Criteria) this;
        }

        public Criteria andFoodCuisineNotLike(String value) {
            addCriterion("food_cuisine not like", value, "foodCuisine");
            return (Criteria) this;
        }

        public Criteria andFoodCuisineIn(List<String> values) {
            addCriterion("food_cuisine in", values, "foodCuisine");
            return (Criteria) this;
        }

        public Criteria andFoodCuisineNotIn(List<String> values) {
            addCriterion("food_cuisine not in", values, "foodCuisine");
            return (Criteria) this;
        }

        public Criteria andFoodCuisineBetween(String value1, String value2) {
            addCriterion("food_cuisine between", value1, value2, "foodCuisine");
            return (Criteria) this;
        }

        public Criteria andFoodCuisineNotBetween(String value1, String value2) {
            addCriterion("food_cuisine not between", value1, value2, "foodCuisine");
            return (Criteria) this;
        }

        public Criteria andFoodKeyIsNull() {
            addCriterion("food_key is null");
            return (Criteria) this;
        }

        public Criteria andFoodKeyIsNotNull() {
            addCriterion("food_key is not null");
            return (Criteria) this;
        }

        public Criteria andFoodKeyEqualTo(String value) {
            addCriterion("food_key =", value, "foodKey");
            return (Criteria) this;
        }

        public Criteria andFoodKeyNotEqualTo(String value) {
            addCriterion("food_key <>", value, "foodKey");
            return (Criteria) this;
        }

        public Criteria andFoodKeyGreaterThan(String value) {
            addCriterion("food_key >", value, "foodKey");
            return (Criteria) this;
        }

        public Criteria andFoodKeyGreaterThanOrEqualTo(String value) {
            addCriterion("food_key >=", value, "foodKey");
            return (Criteria) this;
        }

        public Criteria andFoodKeyLessThan(String value) {
            addCriterion("food_key <", value, "foodKey");
            return (Criteria) this;
        }

        public Criteria andFoodKeyLessThanOrEqualTo(String value) {
            addCriterion("food_key <=", value, "foodKey");
            return (Criteria) this;
        }

        public Criteria andFoodKeyLike(String value) {
            addCriterion("food_key like", value, "foodKey");
            return (Criteria) this;
        }

        public Criteria andFoodKeyNotLike(String value) {
            addCriterion("food_key not like", value, "foodKey");
            return (Criteria) this;
        }

        public Criteria andFoodKeyIn(List<String> values) {
            addCriterion("food_key in", values, "foodKey");
            return (Criteria) this;
        }

        public Criteria andFoodKeyNotIn(List<String> values) {
            addCriterion("food_key not in", values, "foodKey");
            return (Criteria) this;
        }

        public Criteria andFoodKeyBetween(String value1, String value2) {
            addCriterion("food_key between", value1, value2, "foodKey");
            return (Criteria) this;
        }

        public Criteria andFoodKeyNotBetween(String value1, String value2) {
            addCriterion("food_key not between", value1, value2, "foodKey");
            return (Criteria) this;
        }

        public Criteria andFoodIntroduceIsNull() {
            addCriterion("food_introduce is null");
            return (Criteria) this;
        }

        public Criteria andFoodIntroduceIsNotNull() {
            addCriterion("food_introduce is not null");
            return (Criteria) this;
        }

        public Criteria andFoodIntroduceEqualTo(String value) {
            addCriterion("food_introduce =", value, "foodIntroduce");
            return (Criteria) this;
        }

        public Criteria andFoodIntroduceNotEqualTo(String value) {
            addCriterion("food_introduce <>", value, "foodIntroduce");
            return (Criteria) this;
        }

        public Criteria andFoodIntroduceGreaterThan(String value) {
            addCriterion("food_introduce >", value, "foodIntroduce");
            return (Criteria) this;
        }

        public Criteria andFoodIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("food_introduce >=", value, "foodIntroduce");
            return (Criteria) this;
        }

        public Criteria andFoodIntroduceLessThan(String value) {
            addCriterion("food_introduce <", value, "foodIntroduce");
            return (Criteria) this;
        }

        public Criteria andFoodIntroduceLessThanOrEqualTo(String value) {
            addCriterion("food_introduce <=", value, "foodIntroduce");
            return (Criteria) this;
        }

        public Criteria andFoodIntroduceLike(String value) {
            addCriterion("food_introduce like", value, "foodIntroduce");
            return (Criteria) this;
        }

        public Criteria andFoodIntroduceNotLike(String value) {
            addCriterion("food_introduce not like", value, "foodIntroduce");
            return (Criteria) this;
        }

        public Criteria andFoodIntroduceIn(List<String> values) {
            addCriterion("food_introduce in", values, "foodIntroduce");
            return (Criteria) this;
        }

        public Criteria andFoodIntroduceNotIn(List<String> values) {
            addCriterion("food_introduce not in", values, "foodIntroduce");
            return (Criteria) this;
        }

        public Criteria andFoodIntroduceBetween(String value1, String value2) {
            addCriterion("food_introduce between", value1, value2, "foodIntroduce");
            return (Criteria) this;
        }

        public Criteria andFoodIntroduceNotBetween(String value1, String value2) {
            addCriterion("food_introduce not between", value1, value2, "foodIntroduce");
            return (Criteria) this;
        }

        public Criteria andFoodImgUrlIsNull() {
            addCriterion("food_img_url is null");
            return (Criteria) this;
        }

        public Criteria andFoodImgUrlIsNotNull() {
            addCriterion("food_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andFoodImgUrlEqualTo(String value) {
            addCriterion("food_img_url =", value, "foodImgUrl");
            return (Criteria) this;
        }

        public Criteria andFoodImgUrlNotEqualTo(String value) {
            addCriterion("food_img_url <>", value, "foodImgUrl");
            return (Criteria) this;
        }

        public Criteria andFoodImgUrlGreaterThan(String value) {
            addCriterion("food_img_url >", value, "foodImgUrl");
            return (Criteria) this;
        }

        public Criteria andFoodImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("food_img_url >=", value, "foodImgUrl");
            return (Criteria) this;
        }

        public Criteria andFoodImgUrlLessThan(String value) {
            addCriterion("food_img_url <", value, "foodImgUrl");
            return (Criteria) this;
        }

        public Criteria andFoodImgUrlLessThanOrEqualTo(String value) {
            addCriterion("food_img_url <=", value, "foodImgUrl");
            return (Criteria) this;
        }

        public Criteria andFoodImgUrlLike(String value) {
            addCriterion("food_img_url like", value, "foodImgUrl");
            return (Criteria) this;
        }

        public Criteria andFoodImgUrlNotLike(String value) {
            addCriterion("food_img_url not like", value, "foodImgUrl");
            return (Criteria) this;
        }

        public Criteria andFoodImgUrlIn(List<String> values) {
            addCriterion("food_img_url in", values, "foodImgUrl");
            return (Criteria) this;
        }

        public Criteria andFoodImgUrlNotIn(List<String> values) {
            addCriterion("food_img_url not in", values, "foodImgUrl");
            return (Criteria) this;
        }

        public Criteria andFoodImgUrlBetween(String value1, String value2) {
            addCriterion("food_img_url between", value1, value2, "foodImgUrl");
            return (Criteria) this;
        }

        public Criteria andFoodImgUrlNotBetween(String value1, String value2) {
            addCriterion("food_img_url not between", value1, value2, "foodImgUrl");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table recipes
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table recipes
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}