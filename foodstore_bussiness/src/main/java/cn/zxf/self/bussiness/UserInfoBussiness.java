package cn.zxf.self.bussiness;

import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.entry.UserInfoExample;
import cn.zxf.self.entry.dto.StateInfo;
import cn.zxf.self.entry.vo.UserCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserInfoBussiness
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/13
 */
@Service
public class UserInfoBussiness extends BaseBussiness {

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


    public UserInfo getUserInfoByAccountName(final String accountName, final String password) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria()
                        .andAccountNameEqualTo(accountName)
                        .andAccountPasswordEqualTo(password)
                        .andUserFlagEqualTo(1);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        if (userInfos == null || userInfos.isEmpty()) {
            return null;
        } else {
            return userInfos.get(0);
        }
    }

    public StateInfo setUserInfo(UserInfo userInfo) {
        StateInfo stateInfo = new StateInfo();
        int count = userInfoMapper.insertSelective(userInfo);
        if(count == 1){
            stateInfo.setState(true);
            stateInfo.setMessage("注册成功");

        }else{
            stateInfo.setState(false);
            stateInfo.setMessage("帐号相关信息注册失败！");
        }
        return stateInfo;
    }

    public List<UserInfo> getUserInfoByCondition(UserCondition userCondition) {

        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        if (userCondition.getUserName() != null && StringUtils.isNotBlank(userCondition.getUserName())) {
            criteria.andUserNameLike(userCondition.getUserName());
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
             criteria.andAccountNameLike(userCondition.getAccount());
        }
        if(userCondition.getFlag() != null && StringUtils.isNotBlank(userCondition.getFlag().toString())){
             criteria.andUserFlagEqualTo(userCondition.getFlag());
        }
        if(userCondition.getRealname() != null && StringUtils.isNotBlank(userCondition.getRealname())){
             criteria.andUserRealnameLike(userCondition.getRealname());
        }
        if(userCondition.getAddress() != null && StringUtils.isNotBlank(userCondition.getAddress())){
             criteria.andUserAddressLike(userCondition.getAddress());
        }
        if(userCondition.getPhone() != null && StringUtils.isNotBlank(userCondition.getPhone())){
             criteria.andUserPhoneLike(userCondition.getPhone());
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
            criteria.andUserEmailLike(userCondition.getEmail());
        }

        return userInfoMapper.selectByExample(userInfoExample);
    }
}
