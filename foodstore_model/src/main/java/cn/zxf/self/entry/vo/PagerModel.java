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
    private Integer pageNumber;
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

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
