package cn.zxf.self.bussiness;

import cn.zxf.self.entry.AccountInfo;
import cn.zxf.self.entry.AccountInfoExample;
import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.entry.dto.StateInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @ClassName AccountInfoBussiness
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/13
 */
@Service
public class AccountInfoBussiness extends BaseBussiness {


    public AccountInfo getAccountInfoByAccountName(final String accountName,final String accountPassword) {
        AccountInfoExample accountInfoExample = new AccountInfoExample();
        accountInfoExample.createCriteria()
                            .andAccountNameEqualTo(accountName)
                            .andAccountPasswordEqualTo(accountPassword)
                            .andUseFlagEqualTo(1);
        List<AccountInfo> accountInfoList = accountInfoMapper.selectByExample(accountInfoExample);
        if (accountInfoList == null || accountInfoList.isEmpty()) {
            return null;
        } else {
            return accountInfoList.get(0);
        }

    }

    @Transactional
    public StateInfo setAccountInfoAndUserInfo(final AccountInfo accountInfo, final UserInfo userInfo) {
        StateInfo stateInfo = new StateInfo();

        AccountInfoExample accountInfoExample = new AccountInfoExample();
        accountInfoExample.createCriteria().andAccountNameEqualTo(accountInfo.getAccountName());
        List<AccountInfo>  accounts = accountInfoMapper.selectByExample(accountInfoExample);
        if(0 != accounts.size()){
            stateInfo.setState(false);
            stateInfo.setMessage("已注册该帐号。");
            return stateInfo;
        }
        int acc_count = accountInfoMapper.insert(accountInfo);
        if(1 != acc_count ){
            stateInfo.setState(false);
            stateInfo.setMessage("账号信息注册失败！");
            return stateInfo;
        }
        int user_count = userInfoMapper.insertSelective(userInfo);

        if(1 == acc_count && 1 == user_count){
            stateInfo.setState(true);
            stateInfo.setMessage("注册成功");
            stateInfo.setData(accountInfo);
            return stateInfo;
        }else {
            stateInfo.setState(false);
            stateInfo.setMessage("帐号相关信息注册失败！");
            return stateInfo;
        }

    }
}
