package cn.zxf.self.enums;

/**
 * @ClassName SysResponse
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/22
 */
public class SysResponse {
    public  enum RECORD_CODE{
        RESPONSE_SUCCESS(200),                  //成功
        RESPONSE_FAIL(300),                     //失败
        PARAM_ISIMPTY(1000);                    //传入的参数不能为空

        private Integer type;

         RECORD_CODE(Integer type) {
            this.type = type;
        }

        public Integer get() {
            return this.type;
        }
    }

    public  enum RECORD_MESSAGE {

        RESPONSE_SUCCESS_MESSAGE("成功"),
        RESPONSE_FAIL_MESSAGE("失败"),
        PARAM_ISIMPTY_MESSAGE("参数不能为空");


        private String type;

         RECORD_MESSAGE(String type) {
            this.type = type;
        }

        public String get() {
            return this.type;
        }
    }
}
