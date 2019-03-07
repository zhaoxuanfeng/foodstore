package cn.zxf.self.bussiness;

import cn.zxf.self.entry.*;
import cn.zxf.self.entry.dto.StateInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
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


    public List<ManageRole> findAllRole() {
        ManageRoleExample manageRoleExample = new ManageRoleExample();
        manageRoleExample.createCriteria().andManageRoleIdIsNotNull();
        List<ManageRole>  manageRoleList = manageRoleMapper.selectByExample(manageRoleExample);
        return manageRoleList;
    }

    @Transactional
    public StateInfo findFuncByRoleId(Long manageRoleId) {
        List<Long>  funcIdList  = new ArrayList<>();
        StateInfo stateInfo = new StateInfo();
        ManageRoleFuncRelExample manageRoleFuncRelExample = new ManageRoleFuncRelExample();

        manageRoleFuncRelExample.createCriteria()
                                .andManageRoleIdEqualTo(manageRoleId)
                                .andIsDeleteEqualTo(0);
        List<ManageRoleFuncRel>  roleFuncRelList = manageRoleFuncRelMapper.selectByExample(manageRoleFuncRelExample);
        stateInfo.setState(true);
        if(!ObjectUtils.allNotNull(roleFuncRelList) && roleFuncRelList.size() == 0){
            stateInfo.setMessage("未绑定菜单");

            return stateInfo;
        }
        for(ManageRoleFuncRel manageRoleFuncRel :roleFuncRelList){
            funcIdList.add(manageRoleFuncRel.getManageFuncId());
        }
        ManageFuncExample  manageFuncExample = new ManageFuncExample();
        manageFuncExample.createCriteria()
                                 .andIsDeleteEqualTo(0)
                                 .andManageFuncIdIn(funcIdList);
        List<ManageFunc>  funcList = manageFuncMapper.selectByExample(manageFuncExample);

        if(ObjectUtils.allNotNull(funcIdList) && funcIdList.size() == 0){
            stateInfo.setMessage("未绑定菜单");
            return stateInfo;
        }
        stateInfo.setData(funcList);
        stateInfo.setMessage("查询成功");
        return stateInfo;
    }
}
