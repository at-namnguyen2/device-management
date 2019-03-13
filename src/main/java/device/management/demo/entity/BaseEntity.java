package device.management.demo.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import device.management.demo.service.RequestContext;
import device.management.demo.util.UserUtils;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Column(name = "CREATED_BY")
    protected Long createdBy;

    @Column(name = "CREATED_DATE")
    protected Date createdDate;

    @Column(name = "UPDATED_BY")
    protected Long updatedBy;

    @Column(name = "UPDATED_DATE")
    protected Date updatedDate;

    @Column(name = "IS_DELETED")
    protected Boolean isDeleted;

    public BaseEntity(Long createdBy, Date createdDate, Long updatedBy, Date updatedDate, Boolean isDeleted) {
        super();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.isDeleted = isDeleted;
    }
    
    public BaseEntity() {
        super();
    }
    
    @PrePersist
    protected void onCreate() {
        if (UserUtils.getCurrentUserId(RequestContext.getCurrentHttpRequest()) != null) {
            this.createdBy = UserUtils.getCurrentUserId(RequestContext.getCurrentHttpRequest());
            this.updatedBy = UserUtils.getCurrentUserId(RequestContext.getCurrentHttpRequest());
        }
        this.createdDate = new Date();
        this.updatedDate = new Date();
        this.isDeleted = false;
    }

    @PreUpdate
    protected void onUpdate() {
        if (UserUtils.getCurrentUserId(RequestContext.getCurrentHttpRequest()) != null) {
            this.updatedBy = UserUtils.getCurrentUserId(RequestContext.getCurrentHttpRequest());
        }
        this.updatedDate = new Date();
    }
    
    /**
     * @return the createdBy
     */
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate
     *            the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the updatedBy
     */
    public Long getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy
     *            the updatedBy to set
     */
    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return the updatedDate
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * @param updatedDate
     *            the updatedDate to set
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * @return the isDeleted
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted
     *            the isDeleted to set
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}

