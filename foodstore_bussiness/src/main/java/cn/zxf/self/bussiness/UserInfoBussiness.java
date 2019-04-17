package cn.zxf.self.bussiness;

import cn.zxf.self.entry.ManageUserRoleRel;
import cn.zxf.self.example.ManageUserRoleRelExample;
import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.example.UserInfoExample;
import cn.zxf.self.dto.StateInfo;
import cn.zxf.self.utils.DateUtils;
import cn.zxf.self.vo.UserCondition;
import cn.zxf.self.utils.DataUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserInfoBussiness
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/13
 */
@Service
public class UserInfoBussiness extends BaseBussiness {

    private StateInfo stateInfo  = new StateInfo();

    public UserInfo getUserInfoByAccountInfo(final String accountName, final String accountPassword) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria()
                        .andAccountNameEqualTo(accountName)
                        .andAccountPasswordEqualTo(accountPassword)
                        .andUserFlagEqualTo(1);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        if (userInfos == null || userInfos.isEmpty()) {
            return null;
        } else {
            return userInfos.get(0);
        }
    }


    public UserInfo getUserInfoByAccountName(final String accountName) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria()
                        .andAccountNameEqualTo(accountName);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        if (userInfos == null || userInfos.isEmpty()) {
            return null;
        } else {
            return userInfos.get(0);
        }
    }

    public StateInfo setUserInfo(final  UserInfo userInfo) {
        int count = userInfoMapper.insertSelective(userInfo);
        if(count == 1){
            stateInfo.setState(true);
            stateInfo.setMessage("注册成功");
            UserInfoExample userInfoExample = new UserInfoExample();
            userInfoExample.createCriteria()
                           .andAccountNameEqualTo(userInfo.getAccountName())
                           .andAccountPasswordEqualTo(userInfo.getAccountPassword());
            List<UserInfo>  userInfo_insert = userInfoMapper.selectByExample(userInfoExample);
            if(ObjectUtils.allNotNull(userInfo) && userInfo_insert.size() > 0){
                stateInfo.setData(userInfo_insert.get(0).getUserId());
            }

        }else{
            stateInfo.setState(false);
            stateInfo.setMessage("帐号相关信息注册失败！");
        }
        return stateInfo;
    }

    public StateInfo getUserInfoByCondition(final UserCondition userCondition) {

        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        if (userCondition.getUserName() != null && StringUtils.isNotBlank(userCondition.getUserName())) {
            criteria.andUserNameLike(DataUtils.likeAdd(userCondition.getUserName()));
        }
        if(userCondition.getId() != null && StringUtils.isNotBlank(userCondition.getId().toString())) {
             criteria.andUserIdEqualTo(userCondition.getId());
        }
        if(userCondition.getStartTime() != null){
             criteria.andUserBirthdayGreaterThanOrEqualTo(userCondition.getStartTime());
        }
        if(userCondition.getEndTime() != null ){
             criteria.andUserBirthdayLessThanOrEqualTo(userCondition.getEndTime());
        }
        if(userCondition.getSex() != null && StringUtils.isNotBlank(userCondition.getSex())){
             criteria.andUserSexEqualTo(userCondition.getSex());
        }
        if(userCondition.getAccount() != null && StringUtils.isNotBlank(userCondition.getAccount())){
             criteria.andAccountNameLike(DataUtils.likeAdd(userCondition.getAccount()));
        }
        if(userCondition.getFlag() != null && StringUtils.isNotBlank(userCondition.getFlag().toString())){
             criteria.andUserFlagEqualTo(userCondition.getFlag());
        }
        if(userCondition.getRealname() != null && StringUtils.isNotBlank(userCondition.getRealname())){
             criteria.andUserRealnameLike(DataUtils.likeAdd(userCondition.getRealname()));
        }
        if(userCondition.getAddress() != null && StringUtils.isNotBlank(userCondition.getAddress())){
             criteria.andUserAddressLike(DataUtils.likeAdd(userCondition.getAddress()));
        }
        if(userCondition.getPhone() != null && StringUtils.isNotBlank(userCondition.getPhone())){
             criteria.andUserPhoneLike(DataUtils.likeAdd(userCondition.getPhone()));
        }
        if(userCondition.getCreate_startTime() != null  ){
            criteria.andCreateTimeGreaterThanOrEqualTo(userCondition.getCreate_startTime());
        }
        if(userCondition.getCreate_endTime() != null  ){
            criteria.andCreateTimeLessThanOrEqualTo(userCondition.getCreate_endTime());
        }
        if(userCondition.getModify_startTime() != null){
            criteria.andModifyTimeGreaterThanOrEqualTo(userCondition.getModify_startTime());
        }
        if(userCondition.getModify_endTime() != null){
            criteria.andModifyTimeLessThanOrEqualTo(userCondition.getModify_endTime());
        }
        if(userCondition.getEmail() != null && StringUtils.isNotBlank(userCondition.getEmail())){
            criteria.andUserEmailLike(DataUtils.likeAdd(userCondition.getEmail()));
        }


        List<UserInfo> userInfoList = userInfoMapper.selectByExample(userInfoExample);
        StateInfo stateInfo = new StateInfo();
        if(null == userInfoList || userInfoList.isEmpty()){
            stateInfo.setMessage("获取数据失败");
            stateInfo.setState(false);
        }else{
            stateInfo.setMessage("获取数据成功");
            stateInfo.setState(true);
            stateInfo.setData(userInfoList);
        }

        return stateInfo;
    }

    public StateInfo modifyUser(final UserInfo requestUserInfo) {

      /*  UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserIdEqualTo(requestUserInfo.getUserId());
        int count = userInfoMapper.updateByExampleSelective(requestUserInfo,userInfoExample);*/
        requestUserInfo.setModifyTime(new Date());
        int count = userInfoMapper.updateByPrimaryKeySelective(requestUserInfo);
        if(count == 0){
            stateInfo.setState(false);
            stateInfo.setMessage("失败");
        }else{
            stateInfo.setState(true);
            stateInfo.setMessage("成功");
        }
        stateInfo.setData(count);

        return  stateInfo;

    }

    @Transactional
    public List<UserInfo> findUserInfoListByRoleId(final Long mangeRoleId) {
        ManageUserRoleRelExample manageUserRoleRelExample = new ManageUserRoleRelExample();
        manageUserRoleRelExample.createCriteria().andManageRoleIdEqualTo(mangeRoleId);
        List<ManageUserRoleRel> manageUserRoleRelList = manageUserRoleRelMapper.selectByExample(manageUserRoleRelExample);
        List<Long> userIdList = new  ArrayList<>();
        for (ManageUserRoleRel manageUserRoleRel:manageUserRoleRelList    ) {
            userIdList.add(manageUserRoleRel.getManageUserId());
        }
        List<UserInfo> userInfoList = new ArrayList<>();
        if(ObjectUtils.allNotNull(userIdList)  &&  userIdList.size() != 0){
            UserInfoExample userInfoExample = new UserInfoExample();
            userInfoExample.createCriteria().andUserIdIn(userIdList);
            userInfoList = userInfoMapper.selectByExample(userInfoExample);
        }else{
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName("无");
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }

    @Transactional
    public StateInfo initUserPassword(final List<Long> userIdList,final  String initPassword) {

        UserInfo  userInfo = new UserInfo();
        userInfo.setAccountPassword(initPassword);
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria()
                       .andUserIdIn(userIdList);
        int count  = userInfoMapper.updateByExampleSelective(userInfo,userInfoExample);
        if( 0 ==  count){
            stateInfo.setCode("400");
            stateInfo.setState(false);
            stateInfo.setMessage("修改密码失败！");
        }else {
            stateInfo.setState(true);
            stateInfo.setMessage("修改密码成功");
            stateInfo.setCode("200");
        }
        return stateInfo;
    }

    public Boolean updateUserStatusFlag(final Long userId) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        userInfo.setUserFlag(1);
        int count = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (count > 0) return true;
        else return false;
    }
}
