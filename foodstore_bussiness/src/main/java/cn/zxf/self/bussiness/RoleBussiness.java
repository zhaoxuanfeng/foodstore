package cn.zxf.self.bussiness;

import cn.zxf.self.entry.*;
import cn.zxf.self.dto.StateInfo;
import cn.zxf.self.example.ManageFuncExample;
import cn.zxf.self.example.ManageRoleExample;
import cn.zxf.self.example.ManageRoleFuncRelExample;
import cn.zxf.self.example.ManageUserRoleRelExample;
import cn.zxf.self.utils.DateUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RoleBussiness
 * @Description TODO
 * @Author zxf
 * @DATE 2019/3/5
 */
@Service
@Transactional
public class RoleBussiness extends BaseBussiness {

    private  static StateInfo stateInfo = new StateInfo();

    public List<ManageRole> findAllRole() {
        ManageRoleExample manageRoleExample = new ManageRoleExample();
        manageRoleExample.createCriteria().andManageRoleIdIsNotNull();
        List<ManageRole>  manageRoleList = manageRoleMapper.selectByExample(manageRoleExample);
        return manageRoleList;
    }

    @Transactional
    public StateInfo findFuncByRoleId(final Long manageRoleId) {
        List<Long>  funcIdList  = new ArrayList<>();
        ManageRoleFuncRelExample manageRoleFuncRelExample = new ManageRoleFuncRelExample();

        manageRoleFuncRelExample.createCriteria()
                                .andManageRoleIdEqualTo(manageRoleId)
                                .andIsDeleteEqualTo(0);
        List<ManageRoleFuncRel>  roleFuncRelList = manageRoleFuncRelMapper.selectByExample(manageRoleFuncRelExample);
        stateInfo.setState(true);
        if(!ObjectUtils.allNotNull(roleFuncRelList) || roleFuncRelList.size() == 0){
            stateInfo.setMessage("未绑定菜单");
            return stateInfo;
        }
        for(ManageRoleFuncRel manageRoleFuncRel :roleFuncRelList){
            funcIdList.add(manageRoleFuncRel.getManageFuncId());
        }
        ManageFuncExample manageFuncExample = new ManageFuncExample();
        manageFuncExample.createCriteria()
                                 .andIsDeleteEqualTo(0)
                                 .andManageFuncIdIn(funcIdList);
        List<ManageFunc>  funcList = manageFuncMapper.selectByExample(manageFuncExample);

        if(ObjectUtils.allNotNull(funcIdList) && funcIdList.size() == 0){
            stateInfo.setMessage("未绑定菜单");
            return stateInfo;
        }
        stateInfo.setState(true);
        stateInfo.setData(funcList);
        stateInfo.setMessage("查询成功");
        return stateInfo;
    }

    @Transactional
    public StateInfo addRole(final ManageRole addRole, final List<Long> manageFuncIds) {
        int  count = manageRoleMapper.insertSelective(addRole);
        stateInfo.setData(count);
        if(count == 0){
            stateInfo.setCode("404");
            stateInfo.setMessage("插入数据失败");
            return stateInfo;
        }
        stateInfo.setCode("200");
        stateInfo.setMessage("添加成功。");
        return stateInfo;
    }

    @Transactional
    public StateInfo modifyRole(final ManageRole manageRole,final  List<Long> funcIdsList,final int  modifyRoleFlag) {
        ManageRoleExample manageRoleExample = new ManageRoleExample();
        ManageRoleFuncRelExample selectExample = new ManageRoleFuncRelExample();
        Long currTime = DateUtils.getCurrMilli();
        if(1 == modifyRoleFlag){
            manageRoleExample.createCriteria()
                    .andManageRoleIdEqualTo(manageRole.getManageRoleId());
            manageRole.setModifyTime(currTime);
            int count = manageRoleMapper.updateByExampleSelective(manageRole,manageRoleExample);
            if(count == 0){
                stateInfo.setCode("403");
                stateInfo.setMessage("更新角色信息失败");
                stateInfo.setState(false);
                return stateInfo;
            }
        }

        //是否有功能属于角色
        if(null != funcIdsList && funcIdsList.size()>0){
            ManageRoleFuncRelExample.Criteria selectCriteria = selectExample.createCriteria();
                        selectCriteria.andManageRoleIdEqualTo(manageRole.getManageRoleId());
            List<ManageRoleFuncRel>  manageRoleFuncRelList = manageRoleFuncRelMapper.selectByExample(selectExample);
            //原属于角色的功能是否为空
            if(null != manageRoleFuncRelList && manageRoleFuncRelList.size() > 0) {
                for(Long funcId:funcIdsList){
                    boolean flag = true;
                     for (ManageRoleFuncRel manageRoleFuncRel : manageRoleFuncRelList) {

                        if(manageRoleFuncRel.getManageFuncId().equals(funcId)){

                            //原有的所属关系是否有效
                            if(manageRoleFuncRel.getIsDelete().equals(1)) {
                                manageRoleFuncRel.setIsDelete(0);
                                manageRoleFuncRel.setModifyTime(currTime);
                                ManageRoleFuncRelExample updateExample = new ManageRoleFuncRelExample();
                                updateExample.createCriteria()
                                        .andManageRoleIdEqualTo(manageRole.getManageRoleId())
                                        .andManageFuncIdEqualTo(funcId);
                                int j = manageRoleFuncRelMapper.updateByExample(manageRoleFuncRel, updateExample);
                                if(j == 0 ){
                                    stateInfo.setMessage("更新关系失败！");
                                    stateInfo.setCode("404");
                                    return stateInfo;
                                }
                                flag = false;
                            }
                        }
                   }
                   if(flag){
                         //原有的关系里没有，新增关系
                        int k = insertRoleRel(manageRole.getManageRoleId(),funcId);
                        if(k==0){
                            stateInfo.setMessage("插入新关系失败！");
                            stateInfo.setCode("404");
                            return stateInfo;
                        }
                   }
                }
            }else{
                //没有角色，重新插入角色
                for(Long id :funcIdsList){
                    int l = insertRoleRel(manageRole.getManageRoleId(),id);
                    if(l==0){
                        stateInfo.setMessage("插入新关系失败！");
                        stateInfo.setCode("404");
                        return stateInfo;
                    }
                }
            }
        }
        stateInfo.setMessage("更新角色信息成功！");
        stateInfo.setState(true);
        return stateInfo;
    }
    /**
        *@Description  //TODO  插入角色与菜单关系
        *@Param [manageRoleId, funcId]
        *@Return  int
     **/
    private int insertRoleRel(Long manageRoleId,Long funcId){
        ManageRoleFuncRel newRoleFuncRel = new ManageRoleFuncRel();
        newRoleFuncRel.setManageFuncId(funcId);
        newRoleFuncRel.setManageRoleId(manageRoleId);
        newRoleFuncRel.setIsDelete(0);
        newRoleFuncRel.setCreateTime(DateUtils.getCurrMilli());
        newRoleFuncRel.setModifyTime(DateUtils.getCurrMilli());
        int l = manageRoleFuncRelMapper.insertSelective(newRoleFuncRel);
       return l;
    }

    @Transactional
    public StateInfo removeRole(Long roleId) {
        ManageUserRoleRelExample manageUserRoleRelExample = new ManageUserRoleRelExample();
        manageUserRoleRelExample.createCriteria()
                                .andManageRoleIdEqualTo(roleId)
                                .andIsDeleteEqualTo(0);
        List<ManageUserRoleRel>  manageUserRoleRelList =   manageUserRoleRelMapper.selectByExample(manageUserRoleRelExample);

        if(null != manageUserRoleRelList && manageUserRoleRelList.size() > 0){
            stateInfo.setState(false);
            stateInfo.setMessage("有用户关联该角色！");
            stateInfo.setCode("400");
        }


        ManageRole manageRole = manageRoleMapper.selectByPrimaryKey(roleId);
        manageRole.setIsDelete(1);
        manageRole.setModifyTime(DateUtils.getCurrMilli());
        int count = manageRoleMapper.updateByPrimaryKeySelective(manageRole);
        if(count == 0){
            stateInfo.setCode("400");
            stateInfo.setMessage("删除角色失败");
            stateInfo.setState(false);
        }
        stateInfo.setState(true);
        stateInfo.setMessage("删除角色成功");
        stateInfo.setCode("200");
        return stateInfo;
    }
}
