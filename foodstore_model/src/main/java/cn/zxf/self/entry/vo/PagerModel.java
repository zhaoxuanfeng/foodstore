package cn.zxf.self.entry.vo;

import java.io.Serializable;

/**
 * @ClassName PagerModel
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/26
 */
public class PagerModel implements Serializable {
    private static final long serialVersionUID =  154848123456L;

    private String message ;

    private boolean status;

    private Object Data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    @Override
    public String toString() {
        return "PagerModel{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", Data=" + Data +
                '}';
    }
}
