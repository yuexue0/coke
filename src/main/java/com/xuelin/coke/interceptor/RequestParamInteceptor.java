package com.xuelin.coke.interceptor;

import com.xuelin.coke.common.utils.TimeUtil;
import com.xuelin.coke.common.utils.WebUtil;
import com.xuelin.coke.common.constants.Constants;
import com.xuelin.coke.common.threadlocal.ThreadContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

/**
 * 打印请求参数拦截器
 */
public class RequestParamInteceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(Constants.ACCESS_LOGGER_NAME);

    private static final String SID_SUFFIX = ".0000";
    private static final String REQUEST_ID = "request_id";

    /**
     * 日志格式 "[{}:{}][{}]";
     * [sid:xxx][url:xxx][resp:xxx]
     */
    private static final String LOG_FORMAT = "[{}][{}:{}][{}]";
    private static final String REQ = "req";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            ThreadContext.setStart(System.currentTimeMillis());
            Class<?> aClass = ((HandlerMethod) handler).getBean().getClass();
            String className = aClass.getName();
            HandlerMethod h = (HandlerMethod) handler;
            String methodName = h.getMethod().getName();
            ThreadContext.setMethod(methodName);

            //打印方法访问参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            //所有的请求都加入sid.如果没有则自动创建
            String requestID = request.getParameter(REQUEST_ID);
            requestID = StringUtils.isEmpty(requestID) ? UUID.randomUUID().toString() + SID_SUFFIX : requestID;
            ThreadContext.setTranceId(requestID);

            String clientIp = WebUtil.getRemoteHost(request);
            ThreadContext.setSourceIp(clientIp);

            String params = getParams(parameterMap);

            logger.info(LOG_FORMAT, REQ, REQUEST_ID, ThreadContext.getTraceId(), className + "#" + methodName + ",thread:" + Thread.currentThread().getName() + ",client_ip=" + clientIp + ",params=" + params);

        }

        return super.preHandle(request, response, handler);
    }

    private String getParams(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String[]> e : map.entrySet()) {
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if (value != null && value.length == 1) {
                sb.append(value[0]).append(",");
            } else {
                sb.append(Arrays.toString(value)).append(",");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ThreadContext.setEnd(System.currentTimeMillis());

        logger.info("request_id:{},time:{},源IP:{},方法名:{},cost:{}ms", ThreadContext.getTraceId(), TimeUtil.getTimeStr(ThreadContext.getEnd()), ThreadContext.getSourceIp(), ThreadContext.getMethod(), ThreadContext.getCost());

    }


}
