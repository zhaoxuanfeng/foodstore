package cn.zxf.self.bussiness;

import cn.zxf.self.dto.StateInfo;
import cn.zxf.self.entry.OrderRecipesRel;
import cn.zxf.self.entry.Orders;
import cn.zxf.self.entry.Recipes;
import cn.zxf.self.example.OrderRecipesRelExample;
import cn.zxf.self.example.OrdersExample;
import cn.zxf.self.example.RecipesExample;
import cn.zxf.self.mapper.OrderRecipesRelMapper;
import cn.zxf.self.mapper.OrdersMapper;
import cn.zxf.self.vo.UserOrder;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderInfoBussiness
 * @Description TODO
 * @Author zxf
 * @DATE 2019/3/13
 */

@Service
public class OrderInfoBussiness extends BaseBussiness{

    private final static Logger logger = LoggerFactory.getLogger(OrderInfoBussiness.class);
    private StateInfo stateInfo = new StateInfo();


    public List<Recipes>  findRecipesByOrder(final Long orderId,final Long cookid) {
        OrderRecipesRelExample orderRecipesRelExample = new OrderRecipesRelExample();
        RecipesExample recipesExample = new RecipesExample();

        OrderRecipesRelExample.Criteria criteria = orderRecipesRelExample.createCriteria();
         if(ObjectUtils.allNotNull(cookid)){
                criteria.andCookIdEqualTo(cookid);
         }
         if(ObjectUtils.allNotNull(orderId)){
             criteria.andOrderIdEqualTo(orderId);
         }
        List<OrderRecipesRel>  orderRecipesRelList =  orderRecipesRelMapper.selectByExample(orderRecipesRelExample);
        List<Integer> recipesIds = new ArrayList<>();
        for (OrderRecipesRel orderRecipesRel:orderRecipesRelList){
            recipesIds.add(orderRecipesRel.getRecipesId());
        }
        recipesExample.createCriteria()
                      .andIdIn(recipesIds);
        List<Recipes> recipesList = recipesMapper.selectByExample(recipesExample);
        return recipesList;
    }

    public StateInfo findRecipesByUser(final  Long userId,final Long orderId,final Long cookId,final List<String> statusList) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        if (ObjectUtils.allNotNull(orderId)) {
            List<Recipes> recipesList;
            Map<String,Object> map = new HashMap<>();
            map.put("userId",userId);
            recipesList = findRecipesByOrder(orderId,cookId);
            map.put("orderId",orderId);
            map.put("recipes",recipesList);
            dataList.add(map);
        } else {
            Recipes recipes;
            OrderRecipesRelExample orderRecipesRelExample = new OrderRecipesRelExample();
            /*List<String> statusList = new ArrayList<>();
            statusList.add("2");
            statusList.add("3");
            statusList.add("5");*/
            OrderRecipesRelExample.Criteria criteria = orderRecipesRelExample.createCriteria();

            if(ObjectUtils.allNotNull(cookId)){
                     criteria.andCookIdEqualTo(cookId);
            }
            if(ObjectUtils.allNotNull(statusList) && statusList.size() > 0){
                criteria.andRelStatusIn(statusList);
            }

            List<OrderRecipesRel> orderRecipesRelList = orderRecipesRelMapper.selectByExample(orderRecipesRelExample);
            System.out.println("orderRecipesRelist:"+orderRecipesRelList.toString());
            for (OrderRecipesRel orderRecipesRel : orderRecipesRelList) {
                Map<String, Object> map = new HashMap<>();
                map.put("userId", userId);
                map.put("orderId", orderRecipesRel.getOrderId());
//                map.put("payFlag",);
                recipes = recipesMapper.selectByPrimaryKey(orderRecipesRel.getRecipesId());
//                recipesList = findRecipesByRecipesId(orderRecipesRel.getRecipesId());
//                map.put("recipes", recipesList);
                map.put("recipes",recipes);
                dataList.add(map);
            }
        }
        stateInfo.setData(dataList);
        stateInfo.setState(true);
        return stateInfo;
    }



    public List<Recipes> findHotRecipesIds(final Long startTime,final  Long endTime) {

        List<Long> receipesIds = orderRecipesRelMapper.selectMaxCountRecipes(startTime,endTime);
        List<Recipes> recipesList = new ArrayList<>();
        for(Long id : receipesIds){
            Recipes recipes = recipesMapper.selectByPrimaryKey(id.intValue());
            recipesList.add(recipes);
        }
        return recipesList;
    }

    @Transactional
    public StateInfo addNewOrder(final Orders orders) {
        Integer recordCount = ordersMapper.insertSelective(orders);
        if(!ObjectUtils.allNotNull(recordCount) || recordCount <= 0){
            stateInfo.setData(null);
            stateInfo.setState(false);
            stateInfo.setMessage("插入订单失败！");
            return stateInfo;
        }
        OrdersExample ordersExample = new OrdersExample();
        ordersExample.createCriteria()
                .andUserIdEqualTo(orders.getUserId())
                .andOrderStatusEqualTo(orders.getOrderStatus())
                .andIsDeleteEqualTo(orders.getIsDelete())
                .andCountEqualTo(orders.getCount())
                //订单包含关系数量
                .andOrderAddressEqualTo(orders.getOrderAddress())
                .andCreateTimeEqualTo(orders.getCreateTime());
        List<Orders> ordersList = ordersMapper.selectByExample(ordersExample);
        if(!ObjectUtils.allNotNull(ordersList) || ordersList.size() == 0){
            stateInfo.setMessage("查询订单失败！");
            stateInfo.setState(false);
            stateInfo.setData(null);
            return stateInfo;
        }
        logger.info(ordersList.toString());
        stateInfo.setMessage("插入订单成功！");
        stateInfo.setState(true);
        stateInfo.setData(ordersList.get(0));
        return stateInfo;
    }

    @Transactional
    public StateInfo addNewOrderRecipesRel(final OrderRecipesRel orderRecipesRel) {

        Integer recordCount  = orderRecipesRelMapper.insertSelective(orderRecipesRel);
        if(ObjectUtils.allNotNull(recordCount) || recordCount == 0){
            stateInfo.setData(recordCount);
            stateInfo.setMessage("插入记录失败");
            stateInfo.setState(false);
            stateInfo.setCode("502");
            return stateInfo;
        }
        //
        stateInfo.setMessage("插入订单成功");
        OrderRecipesRelExample orderRecipesRelExample = new OrderRecipesRelExample();
        OrderRecipesRelExample.Criteria  criteria = orderRecipesRelExample.createCriteria();
        if(ObjectUtils.allNotNull(orderRecipesRel.getOrderId())){
            criteria.andOrderIdEqualTo(orderRecipesRel.getOrderId());
        }
        if(ObjectUtils.allNotNull(orderRecipesRel.getRecipesId())){
            criteria.andRecipesIdEqualTo(orderRecipesRel.getRecipesId());
        }
        if(ObjectUtils.allNotNull(orderRecipesRel.getStartTime())){
            criteria.andStartTimeEqualTo(orderRecipesRel.getStartTime());
        }
        if(ObjectUtils.allNotNull(orderRecipesRel.getRelStatus())){
            criteria.andRelStatusEqualTo(orderRecipesRel.getRelStatus());
        }
        List<OrderRecipesRel>  recipesRels = orderRecipesRelMapper.selectByExample(orderRecipesRelExample);
        stateInfo.setData(recipesRels);
        stateInfo.setMessage("插入订单成功！");
        stateInfo.setState(true);
        stateInfo.setCode("200");
        return stateInfo;
    }

    public List<Orders> findOrderByList(String year, String month, Long customerId) {
        OrdersExample ordersExample = new OrdersExample();
        ordersExample.createCriteria()
                     .andUserIdEqualTo(customerId.intValue());
        return  ordersMapper.selectByExample(ordersExample);
    }

    @Transactional
    public Boolean updateOrderFlag(Long order_id,String flag) {

        Orders orders = ordersMapper.selectByPrimaryKey(order_id);
        orders.setOrderStatus(flag);
        System.out.println(orders);
        Integer count = ordersMapper.updateByPrimaryKeySelective(orders);
        if(ObjectUtils.allNotNull(count) && count > 0){
            return true;
        }
        return false;
    }

    @Transactional
    public StateInfo updatePayOrder(List<UserOrder> userOrdersList, String staus) {
        Orders orders = new Orders();
        OrdersExample ordersExample = new OrdersExample();
        List<Long> idList = new ArrayList<>();


        for (UserOrder userOrder:userOrdersList){
            idList.add(userOrder.getOrderId());
        }

        orders.setOrderStatus(staus);
        ordersExample.createCriteria()
                .andOrderStatusEqualTo("未支付")
                .andOrderIdIn(idList);
        int count = ordersMapper.updateByExampleSelective(orders,ordersExample);

        if(count <= 0 ){
            stateInfo.setMessage("更新订单信息失败");
            stateInfo.setState(false);
            return stateInfo;
        }

        List<OrderRecipesRel> orderRecipesRelList = new ArrayList<>();
//        List<String> relStatusList = new ArrayList<>();
//        relStatusList.add("7");
        //根据用户订单和菜品id来查所需要做的菜品的状态
        for(UserOrder userOrder :userOrdersList){
            OrderRecipesRel orderRecipesRel = new OrderRecipesRel();
//            orderRecipesRel.setOrderId(userOrder.getOrderId());
//            orderRecipesRel.setRecipesId(userOrder.getRecipesId().intValue());
            orderRecipesRel.setRelStatus("4");
            OrderRecipesRelExample orderRecipesRelExample = new OrderRecipesRelExample();
            OrderRecipesRelExample.Criteria criteria = orderRecipesRelExample.createCriteria()
                                  .andRecipesIdEqualTo(userOrder.getRecipesId().intValue())
                                  .andOrderIdEqualTo(userOrder.getOrderId())
                                  .andRelStatusEqualTo("7");
            int count_1 = orderRecipesRelMapper.updateByExampleSelective(orderRecipesRel,orderRecipesRelExample);

            if(count_1 <= 0){
                stateInfo.setMessage("更新关系信息失败");
                stateInfo.setState(false);
                return stateInfo;
            }
            criteria.andRelStatusEqualTo("4");
            List<OrderRecipesRel> rowData = orderRecipesRelMapper.selectByExample(orderRecipesRelExample);
            orderRecipesRelList.addAll(rowData);
        }


        if(!ObjectUtils.allNotNull(orderRecipesRelList) || orderRecipesRelList.size() <= 0){
            stateInfo.setMessage("获取关系信息失败");
            stateInfo.setState(false);
            return stateInfo;
        }
        stateInfo.setMessage("更新支付订单成功");
        stateInfo.setState(true);
        stateInfo.setData(orderRecipesRelList);
        return stateInfo;
    }
}
