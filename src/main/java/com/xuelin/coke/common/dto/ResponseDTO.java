package com.xuelin.coke.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xuelin.coke.common.utils.WebUtil;
import com.xuelin.coke.common.threadlocal.ThreadContext;

/**
 * Response Info
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    @JsonProperty("code")
    //状态码
    private Integer code = 200;

    @JsonProperty("desc")
    //请求描述
    private String desc = "success";

    @JsonProperty("data")
    private Object data;

    @JsonProperty("ip")
    private String ip = WebUtil.getLocalHostLANAddress();

    @JsonProperty("request_id")
    private String requestId = ThreadContext.getTraceId();

    public ResponseDTO() {

    }

    public ResponseDTO(Object data) {
        this.data = data;
    }

    public ResponseDTO(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
