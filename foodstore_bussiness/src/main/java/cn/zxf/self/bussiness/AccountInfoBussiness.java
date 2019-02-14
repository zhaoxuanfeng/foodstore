package cn.zxf.self.bussiness;

import cn.zxf.self.entry.AccountInfo;
import cn.zxf.self.entry.AccountInfoExample;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @ClassName AccountInfoBussiness
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/13
 */
@Service
public class AccountInfoBussiness extends BaseBussiness {


    public AccountInfo getAccountInfoByAccountName(final String accountName) {
        AccountInfoExample  accountInfoExample = new AccountInfoExample();
        accountInfoExample.createCriteria()
                            .andAccountNameEqualTo(accountName)
                            .andUseFlagEqualTo(1);
        List<AccountInfo> accountInfoList = accountInfoMapper.selectByExample(accountInfoExample);
        if (accountInfoList == null || accountInfoList.isEmpty()) {
            return null;
        } else {
            return accountInfoList.get(0);
        }

    }
}
