package device.management.demo.entity.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement(name = "ResponseData")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseData implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 4782809244984715668L;
    private Object data;
    private Object totalRows;
    private Object error;
    private String message;
    private Boolean otp = false;
    private Object totalPage;
    private String pagerInfo;
    private Integer returnValue;
    private Boolean success = true;
    private String url;
    private String username;

    public ResponseData() {
    }

    public ResponseData(Object data, Object totalRows, Object error, String message, Boolean otp, Object totalPage,
            String pagerInfo, Integer returnValue, Boolean success, String url, String username) {
        super();
        this.data = data;
        this.totalRows = totalRows;
        this.error = error;
        this.message = message;
        this.otp = otp;
        this.totalPage = totalPage;
        this.pagerInfo = pagerInfo;
        this.returnValue = returnValue;
        this.success = success;
        this.url = url;
        this.username = username;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Object totalRows) {
        this.totalRows = totalRows;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getOtp() {
        return otp;
    }

    public void setOtp(Boolean otp) {
        this.otp = otp;
    }

    public Object getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Object totalPage) {
        this.totalPage = totalPage;
    }

    public String getPagerInfo() {
        return pagerInfo;
    }

    public void setPagerInfo(String pagerInfo) {
        this.pagerInfo = pagerInfo;
    }

    public Integer getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Integer returnValue) {
        this.returnValue = returnValue;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
