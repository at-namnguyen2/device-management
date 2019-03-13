package device.management.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "App_Param_Entity")
public class AppParamEntity extends BaseEntity {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "APP_PARAM_SEQ", sequenceName = "APP_PARAM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "APP_PARAM_SEQ")
    private Long id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE", columnDefinition = "VARCHAR(500)")
    private String value;

    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(500)")
    private String description;

    @Column(name = "ORDER_NO")
    private Integer orderNo;

//    @Column(name = "CREATED_BY")
//    private Long createdBy;
//
//    @Column(name = "CREATED_DATE")
//    private Date createdDate;
//
//    @Column(name = "UPDATED_BY")
//    private Long updatedBy;
//
//    @Column(name = "UPDATED_DATE")
//    private Date updatedDate;

    public AppParamEntity() {
        super();
    }

    public AppParamEntity(Long id, String type, String name, String value, String description,
            Integer orderNo) {
        super();
        this.id = id;
        this.type = type;
        this.name = name;
        this.value = value;
        this.description = description;
        this.orderNo = orderNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

//    @SuppressWarnings("deprecation")
//    public AppParam toDto() {
//        AppParam dto = new AppParam(id == null ? null : id.toString(), type, code, name,
//                value == null ? null : value.toString(), description, orderNo == null ? null : orderNo.toString(),
//                status == null ? null : status.toString(), createdBy == null ? null : createdBy.toString(),
//                createdDate == null ? null : DateTimeUtils.convertDateSQLOracleToyyyyMMddTHH(createdDate.toString()),
//                updatedBy == null ? null : updatedBy.toString(),
//                updatedDate == null ? null : DateTimeUtils.convertDateSQLOracleToyyyyMMddTHH(updatedDate.toString()));
//
//        return dto;
//    }
//
//    @SuppressWarnings("deprecation")
//    public PhotoAttachedByType toDtoPhotoAttached() {
//        PhotoAttachedByType dto = new PhotoAttachedByType(id == null ? null : id.toString(), type, code, name,
//                value == null ? null : value.toString(), description, orderNo == null ? null : orderNo.toString(),
//                status == null ? null : status.toString(), createdBy == null ? null : createdBy.toString(),
//                createdDate == null ? null : DateTimeUtils.convertDateSQLOracleToyyyyMMddTHH(createdDate.toString()),
//                updatedBy == null ? null : updatedBy.toString(),
//                updatedDate == null ? null : DateTimeUtils.convertDateSQLOracleToyyyyMMddTHH(updatedDate.toString()));
//        return dto;
//    }
//
//    @Override
//    public String toString() {
//        return "AppParam{" + "id=" + id + ", type='" + type + '\'' + ", code='" + code + '\'' + ", name='" + name + '\''
//                + ", value'=" + value + '\'' + ", description='" + description + '\'' + ", orderNo=" + orderNo
//                + ", status=" + status + ", createdBy='" + createdBy + '\'' + ", createdDate='" + createdDate + '\''
//                + ", updatedBy='" + updatedBy + '\'' + ", updatedDate='" + updatedDate + '\'' + '}';
//    }
//
//    @Override
//    protected BaseDomain toDomain() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    public String generateKeyCache() {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(this.type).append("_").append(this.value);
//        return stringBuilder.toString();
//    }
}