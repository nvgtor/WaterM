package com.water.nvgtor.watermanegement.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2015/8/21.
 */
public class PatrolJson {
    private int pageNumber;
    private int pageSize;
    private int total;
    private int totalPage;
    private Map<String,Object> params;
    private Map<String, List<String>> paramLists;
    private List<PatrolRows> rows;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Map<String, List<String>> getParamLists() {
        return paramLists;
    }

    public void setParamLists(Map<String, List<String>> paramLists) {
        this.paramLists = paramLists;
    }

    public List<PatrolRows> getRows() {
        return rows;
    }

    public void setRows(List<PatrolRows> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PatrolJson{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", totalPage=" + totalPage +
                ", params=" + params +
                ", paramLists=" + paramLists +
                ", rows=" + rows +
                '}';
    }
}
