package device.management.demo.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryParam<T> {
    @JsonProperty("Query")
    @NotNull
    @Valid
    private T query;
    @JsonProperty("PagingItem")
    @NotNull
    @Valid
    private PagingItem pagingItem;

    public QueryParam() {
        super();
    }

    public QueryParam(T query, PagingItem pagingItem) {
        super();
        this.query = query;
        this.pagingItem = pagingItem;
    }

    public T getQuery() {
        return query;
    }

    public void setQuery(T query) {
        this.query = query;
    }

    public PagingItem getPagingItem() {
        return this.pagingItem;
    }

    public void setPagingItem(PagingItem pagingItem) {
        this.pagingItem = pagingItem;
    }

}

