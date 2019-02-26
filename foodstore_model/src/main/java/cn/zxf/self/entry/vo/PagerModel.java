package cn.zxf.self.entry.vo;

import java.io.Serializable;

/**
 * @ClassName PagerModel
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/26
 */
public class PagerModel<T> implements Serializable {
    private static final long serialVersionUID =  154848123456L;

    private Integer pageSize;
    private Long total;
    private T pageData;

    public PagerModel() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public T getPageData() {
        return pageData;
    }

    public void setPageData(T pageData) {
        this.pageData = pageData;
    }
}
