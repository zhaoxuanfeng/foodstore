package cn.zxf.self.controllers;

import cn.zxf.self.bussiness.FuncBussiness;
import cn.zxf.self.bussiness.RoleBussiness;
import cn.zxf.self.entry.ManageFunc;
import cn.zxf.self.entry.ManageRole;
import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.entry.dto.RestModel;
import cn.zxf.self.entry.dto.StateInfo;
import cn.zxf.self.entry.vo.PagerModel;
import cn.zxf.self.entry.vo.RoleFuncModel;
import cn.zxf.self.utils.DateUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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

    private PagerModel pagerModel = new PagerModel();

    private RestModel restModel = new RestModel();

    private StateInfo stateInfo = new StateInfo();


    @RequestMapping(value = "/htm/roleMain.htm")
    public  String roleMain(HttpServletRequest request){
        logger.info("url:"+request.getRequestURI());
        return "manageRole";
    }

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
           /* LocalDateTime date = LocalDateTime.now();
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = date.atZone(zone).toInstant();
            manageRole.setCreateTime(instant.toEpochMilli());*/

            manageRole.setCreateTime(currTime);
            stateInfo = roleBussiness.addRole(manageRole,addRole.getManageFuncIds());
        }else{
            stateInfo = roleBussiness.modifyRole(manageRole,addRole.getManageFuncIds());

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

    @RequestMapping("/htm/manageRoleFunc.htm")
    @ResponseBody
    public PagerModel manageRoleFunc(HttpServletRequest request,RoleFuncModel roleFuncModel){
        logger.info("url:"+request.getRequestURI());
        logger.info("params:"+roleFuncModel.toString());

        //待完成
        return pagerModel;
    }


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
