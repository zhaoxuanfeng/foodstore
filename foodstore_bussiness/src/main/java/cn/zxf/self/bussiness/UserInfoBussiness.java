package cn.zxf.self.bussiness;

import cn.zxf.self.entry.UserInfo;
import cn.zxf.self.entry.UserInfoExample;
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

    public UserInfo getUserInfoByAccountInfo(final String accountId) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria()
                        .andUserAccountEqualTo(accountId)
                        .andUserFlagEqualTo(1);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        if (userInfos == null || userInfos.isEmpty()) {
            return null;
        } else {
            return userInfos.get(0);
        }
    }


}
