package com.xuelin.coke.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * <pre>获取相关的web信息</pre>
 */
public class WebUtil {

    private static volatile String inetAddress = null;

    private final static Object lock = new Object();

    private WebUtil() {
    }


    /**
     * 获得客户端IP
     *
     * @param request {@link HttpServletRequest}
     * @return 返回访问IP
     */
    public static String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("X-ClientIP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }


    /**
     * 正确的IP拿法，即优先拿site-local地址
     *
     * @return {@link InetAddress}
     */
    public static String getLocalHostLANAddress() {
        String inetAddress_temp = "0.0.0.0.0";
        try {
            if (inetAddress == null) {
                synchronized (lock) {
                    if (inetAddress == null) {
                        for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
                            NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                            // 在所有的接口下再遍历IP
                            for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                                InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                                if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                                    if (inetAddr.isSiteLocalAddress()) {
                                        // 如果是site-local地址，就是它了
                                        inetAddress_temp = inetAddr.getHostAddress();
                                    } else if (inetAddress_temp == null) {
                                        // site-local类型的地址未被发现，先记录候选地址
                                        inetAddress_temp = inetAddr.getHostAddress();
                                    }
                                }
                            }
                        }
                        if (StringUtils.isEmpty(inetAddress_temp)) {
                            return inetAddress = inetAddress_temp;
                        }
                        // 如果没有发现 non-loopback地址.只能用最次选的方案
                        inetAddress_temp = InetAddress.getLocalHost().getHostAddress();
                        if (inetAddress_temp == null) {
                            throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
                        }
                        return inetAddress = inetAddress_temp;
                    } else {
                        return inetAddress = inetAddress_temp;
                    }
                }
            } else {
                return inetAddress;
            }
        } catch (Throwable ex) {
            return inetAddress = inetAddress_temp;
        }
    }

    public static void main(String[] args) {

        System.err.println(getLocalHostLANAddress());
    }

}
