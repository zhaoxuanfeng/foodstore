package cn.zxf.self.bussiness;

import cn.zxf.self.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BaseBussiness
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/13
 */
@Service
public class BaseBussiness {


    @Autowired
    protected ManageGroupMapper manageGroupMapper;

    @Autowired
    protected ManageRoleFuncRelMapper manageRoleFuncRelMapper;

    @Autowired
    protected ManageRoleMapper manageRoleMapper;

    @Autowired
    protected ManageSystemMapper manageSystemMapper;

    @Autowired
    protected  ManageUserMapper manageUserMapper;

    @Autowired
    protected ManageUserRoleRelMapper manageUserRoleRelMapper;

    @Autowired
    protected UserInfoMapper userInfoMapper;

    @Autowired
    protected ManageFuncMapper manageFuncMapper;

}
