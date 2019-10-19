package com.xuelin.coke.common.threadlocal;

/**
 * <pre>存入{@link ThreadLocal}用于追踪请求信息</pre>
 */
public class TraceInfo {

    private String traceId;//请求唯一标识

    private String method;//请求方法

    private String sourceIp;//请求源ip

    private long start;//请求开始时间

    private long end;//请求结束时间

    private String cost; //请求消耗时间

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        if (this.end == 0L) {
            setEnd(System.currentTimeMillis());
        }
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "TraceInfo{" +
                "traceId='" + traceId + '\'' +
                ", method='" + method + '\'' +
                ", sourceIp='" + sourceIp + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", cost='" + cost + '\'' +
                '}';
    }
}
