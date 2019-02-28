package cn.zxf.self.bussiness;

import cn.zxf.self.entry.Recipes;
import cn.zxf.self.entry.RecipesExample;
import cn.zxf.self.entry.dto.StateInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName FoodInfoBussiness
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/28
 */
@Service
public class FoodInfoBussiness extends BaseBussiness {

    private StateInfo stateInfo = new StateInfo();

    public StateInfo findFoodInfo(Map<String,Object> map) {
        RecipesExample recipesExample = new RecipesExample();
        RecipesExample.Criteria criteria = recipesExample.createCriteria();
        if(null != map.get("foodName")){
            criteria.andFoodNameLike((String) map.get("foodName"));
        }
        if(null != map.get("foodType")){
            criteria.andFoodTypeEqualTo((Integer) map.get("foodType"));
        }
        if(null != map.get("foodCuisine")){
            criteria.andFoodCuisineEqualTo((String) map.get("foodCuisine"));
        }
        if(null != map.get("foodKey")){
            criteria.andFoodNameLike((String) map.get("foodKey"));
        }
        List<Recipes>  recipesList =  recipesMapper.selectByExample(recipesExample);

        if(null != recipesList && !recipesList.isEmpty()){
            stateInfo.setData(recipesList);
            stateInfo.setMessage("成功");
            stateInfo.setState(true);
        }else {
            stateInfo.setMessage("失败");
            stateInfo.setState(false);
        }

        return stateInfo;
    }
}
