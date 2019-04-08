package cn.zxf.self.bussiness;

import cn.zxf.self.entry.UserAddressRel;
import cn.zxf.self.example.UserAddressRelExample;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserDataBussiness
 * @Description TODO
 * @Author zxf
 * @DATE 2019/4/8
 */
@Service
public class UserDataBussiness extends BaseBussiness {


    public List<UserAddressRel> findAddressListById(Long id) {
        UserAddressRelExample userAddressRelExample = new UserAddressRelExample();
        userAddressRelExample.createCriteria()
                             .andUserIdEqualTo(id.intValue());
//        List<UserAddressRel>  userAddressRelList =  userAddressRelMapper.selectByExample(userAddressRelExample);
        return userAddressRelMapper.selectByExample(userAddressRelExample);
    }


}
