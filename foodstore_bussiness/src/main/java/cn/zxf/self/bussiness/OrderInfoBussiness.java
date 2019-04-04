package cn.zxf.self.bussiness;

import cn.zxf.self.dto.StateInfo;
import cn.zxf.self.entry.OrderRecipesRel;
import cn.zxf.self.entry.Recipes;
import cn.zxf.self.example.OrderRecipesRelExample;
import cn.zxf.self.example.RecipesExample;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

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

    public StateInfo findRecipesByUser(final  Long userId,final Long orderId,final Long cookId) {
        List<Map<String, Object>> dataList = new ArrayList<>();

        List<Recipes> recipesList;
        if (ObjectUtils.allNotNull(orderId)) {
            Map<String,Object> map = new HashMap<>();
            map.put("userId",userId);
            recipesList = findRecipesByOrder(orderId,cookId);
            map.put("orderId",orderId);
            map.put("recipes",recipesList);
            dataList.add(map);
        } else {
            OrderRecipesRelExample orderRecipesRelExample = new OrderRecipesRelExample();
            List<String> statusList = new ArrayList<>();
            statusList.add("2");
            statusList.add("3");
            statusList.add("5");
            orderRecipesRelExample.createCriteria()
                    .andCookIdEqualTo(userId)
                    .andRelStatusNotIn(statusList);
            List<OrderRecipesRel> orderRecipesRelList = orderRecipesRelMapper.selectByExample(orderRecipesRelExample);

            for (OrderRecipesRel orderRecipesRel : orderRecipesRelList) {
                Map<String, Object> map = new HashMap<>();
                map.put("userId", userId);
                map.put("orderId", orderRecipesRel.getOrderId());
                recipesList = findRecipesByOrder(orderRecipesRel.getOrderId(),cookId);
                map.put("recipes", recipesList);
                dataList.add(map);
            }
        }
        stateInfo.setData(dataList);
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
}
