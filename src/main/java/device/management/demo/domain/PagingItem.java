package device.management.demo.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PagingItem {
    public PagingItem() {
    };

    public PagingItem(int pageIndex, int pageSize, Long outRowsNumber) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.outRowsNumber = outRowsNumber;
    };

    private Direction directionSort = Sort.Direction.ASC;
    private int numberOfPage = 0;
    private String orderBy = "id";
    private Long outRowsNumber = 0L;
    private int pageIndex = 1;
    private int pageSize = 12;

    public Direction getDirectionSort() {
        return directionSort;
    }

    public void setDirectionSort(Direction directionSort) {
        this.directionSort = directionSort;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Long getOutRowsNumber() {
        return outRowsNumber;
    }

    public void setOutRowsNumber(Long outRowsNumber) {
        this.outRowsNumber = outRowsNumber;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
