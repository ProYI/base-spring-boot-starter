package vip.poryi.base.model;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@JsonIgnoreProperties({"current", "size", "total", "pages", "records", "optimizeCountSql", "isSearchCount", "hitCount", "searchCount"})
public class PageResult<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>  {
    private Long pageIndex;
    private Long pageSize;
    private Long totalCount;
    private Long totalPage;
    private List<T> pageData;

    public PageResult() {
        this(1L, 10L);
    }

    public PageResult(Long pageIndex) {
        this(pageIndex, 10L);
    }

    public PageResult(Long pageIndex, Long pageSize) {
        this.pageData = Collections.emptyList();
        this.pageIndex = this.calcPageIndex(pageIndex);
        this.pageSize = this.calcPageSize(pageSize);
    }

    public Long getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(Long pageIndex) {
        this.pageIndex = this.calcPageIndex(pageIndex);
        super.setCurrent(this.pageIndex);
    }

    public Long getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = this.calcPageSize(pageSize);
        super.setSize(this.pageSize);
    }

    public Long getTotalCount() {
        return this.totalCount;
    }

    public Long getTotalPage() {
        return this.totalPage;
    }

    public List<T> getPageData() {
        return this.pageData;
    }

    private Long calcPageIndex(Long pageIndex) {
        return pageIndex != null && pageIndex >= 1L ? pageIndex : 1L;
    }

    private Long calcPageSize(Long pageSize) {
        return pageSize != null && pageSize >= 10L ? pageSize : 10L;
    }

    private void calcTotalPage() {
        this.totalPage = this.totalCount != null && this.totalCount != 0L ? (this.totalCount + this.pageSize - 1L) / this.pageSize : 0L;
    }

    @Override
    public com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> setRecords(List<T> records) {
        this.pageData = records;
        return super.setRecords(records);
    }

    @Override
    public com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> setTotal(long total) {
        this.totalCount = total;
        this.calcTotalPage();
        return super.setTotal(total);
    }

    @Override
    public com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> setSize(long size) {
        this.pageSize = size;
        return super.setSize(size);
    }

    @Override
    public com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> setCurrent(long current) {
        this.pageIndex = current;
        return super.setCurrent(current);
    }

    @JsonIgnore
    @Override
    public List<OrderItem> getOrders() {
        return super.getOrders();
    }

    @Override
    public <R> PageResult<R> convert(Function<? super T, ? extends R> mapper) {
        PageResult<R> pageResult = new PageResult(this.pageIndex, this.pageSize);
        if (this.pageData.isEmpty()) {
            pageResult.totalCount = 0L;
            return pageResult;
        } else {
            List<R> collect = (List)this.pageData.stream().map(mapper).collect(Collectors.toList());
            pageResult.totalCount = this.totalCount;
            pageResult.pageData = collect;
            pageResult.totalPage = this.totalPage;
            return pageResult;
        }
    }
}
