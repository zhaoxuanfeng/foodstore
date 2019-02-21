package cn.zxf.self.entry.dto;

import java.io.Serializable;

/**
 * @ClassName StateInfo
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/21
 */
public class StateInfo  implements Serializable {



    private boolean state;

    private String message;

    private Object data;

    private  String code;



    public StateInfo() {
    }

    public StateInfo(boolean state, String message) {
        this.state = state;
        this.message = message;
    }

    public StateInfo(boolean state, String message, String code) {
        this.state = state;
        this.message = message;
        this.code = code;
    }

    public StateInfo(boolean state, String message, Object data, String code) {
        this.state = state;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public StateInfo(boolean state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public boolean isState() {
        return state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
