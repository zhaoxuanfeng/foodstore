package cn.zxf.self.bussiness;

import cn.zxf.self.entry.*;
import cn.zxf.self.enums.ManageMenuEnums;
import cn.zxf.self.mapper.ManageFuncMapper;
import cn.zxf.self.mapper.ManageUserRoleRelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ManageFunBusiness
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/22
 */
@Service
public class ManageFunBusiness extends BaseBussiness{


    public List<ManageFunc> getManageFuncByManageUserId(final Long userId) {
        ManageUserRoleRelExample manageUserRoleRelExample = new ManageUserRoleRelExample();
        manageUserRoleRelExample.createCriteria()
                                .andManageUserIdEqualTo(userId)
                                .andIsDeleteEqualTo(ManageMenuEnums.IS_DELETE.NORMAL.get());
        List<ManageUserRoleRel> manageUserRoleRelList = manageUserRoleRelMapper.selectByExample(manageUserRoleRelExample);
        if((null != manageUserRoleRelList)&& !manageUserRoleRelList.isEmpty() ){

            List<Long> manageRoleIDList = new ArrayList<>();
            manageUserRoleRelList.forEach(rel->manageRoleIDList.add(rel.getManageRoleId()));
            return getManageFuncByManageRoleId(manageRoleIDList);

        }
        return null;


    }

    public List<ManageFunc> getManageFuncByManageRoleId(final List<Long> manageRoleIDList) {
        ManageRoleFuncRelExample manageRoleFuncRelExample = new ManageRoleFuncRelExample();
        manageRoleFuncRelExample.createCriteria()
                                .andManageRoleIdIn(manageRoleIDList)
                                .andIsDeleteEqualTo(ManageMenuEnums.IS_DELETE.NORMAL.get());
        List<ManageRoleFuncRel> manageRoleFuncRelList = manageRoleFuncRelMapper.selectByExample(manageRoleFuncRelExample);
        if((null != manageRoleFuncRelList) && !manageRoleFuncRelList.isEmpty()){
            List<Long> manageFuncIDList = new ArrayList<>();
            manageRoleFuncRelList.forEach(rel->manageFuncIDList.add(rel.getManageFuncId()));
            ManageFuncExample manageFuncExample = new ManageFuncExample();
            manageFuncExample.createCriteria()
                             .andManageFuncIdIn(manageFuncIDList)
                            .andIsDeleteEqualTo(ManageMenuEnums.IS_DELETE.NORMAL.get());
            return manageFuncMapper.selectByExample(manageFuncExample);
        }
        return null;
    }
}
