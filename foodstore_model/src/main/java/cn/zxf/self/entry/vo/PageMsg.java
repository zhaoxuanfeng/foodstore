package cn.zxf.self.entry.vo;

import java.util.List;

/**
 * @ClassName PageMsg
 * @Description TODO
 * @Author zxf
 * @DATE 2019/2/27
 */
public class PageMsg {

        private long total;
        private List<?> rows;
        private List<?> footer;
        private Integer pageNumber;
        private Integer pageSize;

        public PageMsg() {
        }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public List<?> getFooter() {
        return footer;
    }

    public void setFooter(List<?> footer) {
        this.footer = footer;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
