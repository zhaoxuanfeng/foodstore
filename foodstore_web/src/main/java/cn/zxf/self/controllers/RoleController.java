package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.FuncBussiness;
import cn.zxf.self.bussiness.RoleBussiness;
import cn.zxf.self.entry.ManageFunc;
import cn.zxf.self.entry.ManageRole;
import cn.zxf.self.dto.RestModel;
import cn.zxf.self.dto.StateInfo;
import cn.zxf.self.vo.RoleFuncModel;
import cn.zxf.self.utils.DateUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName RoleController
 * @Description TODO
 * @Author zxf
 * @DATE 2019/3/5
 */
@Controller
public class RoleController extends BaseController{

    private static final Logger logger  = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleBussiness roleBussiness;

    @Autowired
    private FuncBussiness funcBussiness;

    private RestModel restModel = new RestModel();

    private StateInfo stateInfo = new StateInfo();

    /***
        *@Description  //TODO  跳转权限管理页面
        *@Param [request]
        *@Return  java.lang.String
     **/
    @RequestMapping(value = "/htm/roleMain.htm")
    public  String roleMain(HttpServletRequest request){
        logger.info("url:"+request.getRequestURI());
        return "backstage/manageRole";
    }
    
    /***
        *@Description  //TODO  管理或添加角色及其相关功能
        *@Param [addRole, request]
        *@Return  cn.zxf.self.dto.RestModel
     **/
    @RequestMapping(value="/htm/manageRoleAddMod.htm")
    @ResponseBody
    public RestModel manageRoleAdd(RoleFuncModel addRole, HttpServletRequest request){

        logger.info("url:"+request.getRequestURI());
        logger.info("params:"+addRole.toString());
        if(!ObjectUtils.allNotNull(addRole)){
            restModel.setMessage("传入参数错误！");
            restModel.setCode("400");
            return  restModel;
        }

        ManageRole manageRole = new ManageRole();
        manageRole.setIsDelete(0);
        manageRole.setRoleCode(addRole.getRoleCode());
        manageRole.setRoleName(addRole.getRoleName());
        Long currTime = DateUtils.getCurrMilli();
        manageRole.setModifyTime(currTime);


        if("add".equals(addRole.getAddmodify())) {
            manageRole.setCreateTime(currTime);
            stateInfo = roleBussiness.addRole(manageRole,addRole.getManageFuncIds());
        }else{
            stateInfo = roleBussiness.modifyRole(manageRole,addRole.getManageFuncIds(),1);

        }
        logger.info("message:"+stateInfo.getMessage());
        logger.info("return :"+stateInfo.isState());
        if(null == stateInfo || !stateInfo.isState()){
            restModel.setMessage("插入失败！");
            restModel.setCode("404");
            return  restModel;
        }
        restModel.setData(stateInfo.getData());
        restModel.setCode("200");
        restModel.setMessage(stateInfo.getMessage());
        return restModel;
    }

    /***
        *@Description  //TODO  添加角色功能列表
        *@Param [request, roleFuncModel]
        *@Return  cn.zxf.self.dto.RestModel
     **/
    @RequestMapping("/htm/manageRoleFunc.htm")
    @ResponseBody
    public RestModel manageRoleFunc(HttpServletRequest request,RoleFuncModel roleFuncModel){
        logger.info("url:"+request.getRequestURI());
        logger.info("params:"+roleFuncModel.toString());

        ManageRole manageRole = new ManageRole();
        manageRole.setManageRoleId(roleFuncModel.getManageRoleId());
        manageRole.setCreateTime(DateUtils.getCurrMilli());
        stateInfo =  roleBussiness.modifyRole(manageRole,roleFuncModel.getManageFuncIds(),0);

        restModel.setMessage(stateInfo.getMessage());
        if(stateInfo.isState()){
            restModel.setCode("200");
        }else {
            restModel.setCode("400");
        }
        logger.info("return status:"+stateInfo.isState());
        return restModel;
    }

    /***
        *@Description  //TODO  删除角色
        *@Param [request, roleId]
        *@Return  cn.zxf.self.dto.RestModel
     **/
    @RequestMapping("/htm/manageRoleDel.htm")
    @ResponseBody
    public RestModel  manageRoleDel(HttpServletRequest  request,Long  roleId){
        logger.info("url:"+ request.getRequestURI());
        logger.info("删除角色：id="+roleId);
        stateInfo = roleBussiness.removeRole(roleId);
        restModel.setCode(stateInfo.getCode());
        restModel.setMessage(stateInfo.getMessage());
        logger.info("删除角色：id"+roleId+",status:"+stateInfo.isState()+",return coude :" + stateInfo.getCode());
        logger.info("return"+stateInfo.isState());
        return  restModel;
    }
    
    
    /***
        *@Description  //TODO  获取角色所属功能列表
        *@Param [request, manageRoleId]
        *@Return  cn.zxf.self.dto.RestModel
     **/
    @RequestMapping("/htm/manageRoleFuncList.htm")
    @ResponseBody
    public RestModel getRoleFuncList(HttpServletRequest request,Long manageRoleId){
        logger.info("url:"+request.getRequestURI());
        logger.info("params:"+manageRoleId);

        stateInfo = roleBussiness.findFuncByRoleId(manageRoleId);
        if(null != stateInfo && stateInfo.isState()){
            restModel.setData(stateInfo.getData());
            restModel.setCode("200");
            restModel.setMessage("加载成功");
        }else {
            restModel.setMessage(stateInfo.getMessage());
        }

        logger.info("return :" +stateInfo.isState());
        return restModel;
    }

    /***
        *@Description  //TODO  获取所有角色列表
        *@Param [request]
        *@Return  cn.zxf.self.dto.RestModel
     **/
    @RequestMapping(value = "/htm/manageRoleList.htm")
    @ResponseBody
    public RestModel manageRoleList(HttpServletRequest request){
        logger.info("url:"+request.getRequestURI());
        List<ManageRole>  manageRoleList = roleBussiness.findAllRole();
        if (null != manageRoleList && !manageRoleList.isEmpty()){
            restModel.setData(manageRoleList);
            restModel.setMessage("获取所有权限");
            restModel.setCode("200");
        }
        return restModel;
    }
    
    /***
        *@Description  //TODO  获取所有功能列表
        *@Param [request]
        *@Return  cn.zxf.self.dto.RestModel
     **/
    @RequestMapping("/htm/manageFuncList.htm")
    @ResponseBody
    public RestModel manageFuncList(HttpServletRequest request){
        logger.info("url:"+request.getRequestURI());
        List<ManageFunc> manageFuncList = funcBussiness.findAllFuncList();
        if(null != manageFuncList && !manageFuncList.isEmpty()){
            restModel.setData(manageFuncList);
            restModel.setMessage("功能加载成功！");
            restModel.setCode("200");
        }
        return restModel;
    }
}
