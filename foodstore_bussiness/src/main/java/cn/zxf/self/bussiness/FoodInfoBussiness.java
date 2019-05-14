package cn.zxf.self.bussiness;

import cn.zxf.self.entry.Recipes;
import cn.zxf.self.dto.StateInfo;
import cn.zxf.self.example.RecipesExample;
import cn.zxf.self.utils.DataUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public StateInfo findFoodInfo(final Map<String,Object> map) {
        RecipesExample recipesExample = new RecipesExample();
        RecipesExample.Criteria criteria = recipesExample.createCriteria();
        if(null != map.get("foodName")){
            criteria.andFoodNameLike(DataUtils.likeAdd((String) map.get("foodName")));
        }
        if(null != map.get("foodType")){
            criteria.andFoodTypeEqualTo((Integer) map.get("foodType"));
        }
        if(null != map.get("foodCuisine")){
            criteria.andFoodCuisineEqualTo((String) map.get("foodCuisine"));
        }
        if(null != map.get("foodKey")){
            criteria.andFoodNameLike(DataUtils.likeAdd((String) map.get("foodKey")));
        }
        if(null != map.get("lowPrice")){
            criteria.andFoodPriceGreaterThanOrEqualTo((Integer) map.get("lowPrice"));
        }
        if(null != map.get("highPrice")){
            criteria.andFoodPriceLessThanOrEqualTo((Integer) map.get("highPrice"));
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

    @Transactional
    public StateInfo addFoodInfo(final Recipes recipes) {

        int count = recipesMapper.insertSelective(recipes);
        if(count == 0){
            stateInfo.setState(false);
            stateInfo.setMessage("插入失败");
        }else{
            stateInfo.setState(true);
            stateInfo.setMessage("插入成功");
        }
        return stateInfo;
    }

    @Transactional
    public StateInfo modifyFoodInfo(final  Recipes recipes) {
        RecipesExample recipesExample = new RecipesExample();
        recipesExample.createCriteria().andIdEqualTo(recipes.getId());
        int count = recipesMapper.updateByExampleSelective(recipes,recipesExample);
        stateInfo.setData(count);
        if (count==0){
            stateInfo.setState(false);
        }else {
            stateInfo.setState(true);
        }
        return stateInfo;
    }

    @Transactional
    public StateInfo modifyFoodStatus(final List<Integer> ids,final Integer flag) {
        RecipesExample recipesExample = new RecipesExample();
        recipesExample.createCriteria()
                      .andIdIn(ids);
        Recipes recipes = new Recipes();
        recipes.setFoodStatus(flag);
        int count = recipesMapper.updateByExampleSelective(recipes,recipesExample);
        if(count == 0){
            stateInfo.setMessage("更新状态失败！");
            stateInfo.setState(false);
            stateInfo.setCode("400");
        }else{
            stateInfo.setMessage("更新！"+count+"条信息！");
            stateInfo.setState(true);
            stateInfo.setCode("200");
        }
        return stateInfo;
    }

    public Recipes findFoodInfoById(Long id) {
        return  recipesMapper.selectByPrimaryKey(id.intValue());
    }

    public StateInfo findFoodInfoByRecepies(final Recipes recipes) {
        if(ObjectUtils.allNotNull(recipes)){
            RecipesExample recipesExample = new RecipesExample();
            RecipesExample.Criteria criteria = recipesExample.createCriteria();
            if(ObjectUtils.allNotNull(recipes.getFoodCuisine())){
                criteria.andFoodCuisineLike(DataUtils.likeAdd(recipes.getFoodCuisine()));
            }

            if (ObjectUtils.allNotNull(recipes.getFoodKey())){
                criteria.andFoodKeyLike(DataUtils.likeAdd(recipes.getFoodKey()));
            }
            List<Recipes> recipesList = recipesMapper.selectByExample(recipesExample);
            if(ObjectUtils.allNotNull(recipesList) && recipesList.size() > 0){
                stateInfo.setData(recipesList);
                stateInfo.setMessage("查询成功");
                stateInfo.setState(true);
            }else {
                stateInfo.setState(false);
                stateInfo.setMessage("查询失败");
                stateInfo.setData(null);
            }

        }else {
            stateInfo.setState(false);
            stateInfo.setMessage("参数错误。");
        }
        return stateInfo;
    }
}
