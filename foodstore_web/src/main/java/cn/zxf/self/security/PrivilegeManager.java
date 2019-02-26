package cn.zxf.self.security;

import cn.zxf.self.entry.ManageFunc;

import java.util.List;

/**
 * @ClassName PrivilegeManager
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/25
 */
public class PrivilegeManager {

    public static boolean permit(String uri , List<ManageFunc> funcList){
        for (ManageFunc func:funcList) {
            if(uri.equals(func.getUrl())){
                return true;
            }
        }

        return false;
    }

}
