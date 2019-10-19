package com.xuelin.coke.common.threadlocal;

/**
 * <pre>线程上下文</pre>
 */
public class ThreadContext {

    private static final ThreadLocal<TraceInfo> LOCAL = new ThreadLocal<>();

    public static void setTranceId(String tranceId) {
        get().setTraceId(tranceId);
    }

    public static void setMethod(String method) {
        get().setMethod(method);
    }

    public static void setSourceIp(String sourceIp) {
        get().setSourceIp(sourceIp);
    }

    public static void setStart(long start) {
        get().setStart(start);
    }

    public static void setEnd(long end) {
        get().setEnd(end);
    }

    public static void setCost(String cost) {
        get().setCost(cost);
    }

    public static String getTraceId() {
        return get().getTraceId();
    }

    public static String getMethod() {
        return get().getMethod();
    }

    public static String getSourceIp() {
        return get().getSourceIp();
    }

    public static long getStart() {
        return get().getStart();
    }

    public static long getEnd() {
        return get().getEnd();
    }

    public static long getCost() {
        TraceInfo traceInfo = get();
        return traceInfo.getEnd() - traceInfo.getStart();
    }

    private static TraceInfo get() {
        TraceInfo traceInfo = LOCAL.get();
        if (traceInfo == null) {
            traceInfo = new TraceInfo();
            LOCAL.set(traceInfo);
        }
        return traceInfo;
    }
}
