package cn.zxf.self.bussiness;

import cn.zxf.self.dto.StateInfo;
import cn.zxf.self.entry.OrderRecipesRel;
import cn.zxf.self.example.OrderRecipesRelExample;
import cn.zxf.self.example.RecipesExample;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderInfoBussiness
 * @Description TODO
 * @Author zxf
 * @DATE 2019/3/13
 */

@Service
public class OrderInfoBussiness extends BaseBussiness{

    private StateInfo stateInfo = new StateInfo();


    public StateInfo findRecipesByOrder(final Long userId,final Long orderId) {
        OrderRecipesRelExample orderRecipesRelExample = new OrderRecipesRelExample();
        RecipesExample recipesExample = new RecipesExample();

        orderRecipesRelExample.createCriteria()
                              .andCookIdEqualTo(userId)
                              .andOrderIdEqualTo(orderId);
        List<OrderRecipesRel>  orderRecipesRelList =  orderRecipesRelMapper.selectByExample(orderRecipesRelExample);
        List<Integer> recipesIds = new ArrayList<>();
        for (OrderRecipesRel orderRecipesRel:orderRecipesRelList){
            recipesIds.add(orderRecipesRel.getRecipesId());
        }
        recipesExample.createCriteria()
                      .andIdIn(recipesIds);




        return stateInfo;
    }
}
