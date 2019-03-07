package cn.zxf.self.bussiness;

import cn.zxf.self.entry.ManageFunc;
import cn.zxf.self.entry.ManageFuncExample;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName FuncBussiness
 * @Description TODO
 * @Author zxf
 * @DATE 2019/3/5
 */
@Service
public class FuncBussiness extends BaseBussiness{


    public List<ManageFunc> findAllFuncList() {
        ManageFuncExample funcExample = new ManageFuncExample();
        funcExample.createCriteria()
                    .andManageFuncIdIsNotNull();
        List<ManageFunc> manageFuncList = manageFuncMapper.selectByExample(funcExample);
        return manageFuncList;
    }
}
