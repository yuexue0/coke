package com.xuelin.coke.common.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import com.xuelin.coke.common.dto.License;
import org.apache.commons.lang3.StringUtils;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashSet;

import static org.bouncycastle.LICENSE.licenseText;

/**
 * License验证
 * <pre>
 *     验证license
 * </pre>
 */
public class LicenseUtil {

    /**
     * 验证license
     *
     * @param expectedAppName 当前使用license的应用名称
     */
    public static void verifyToken(String expectedAppName) {

        try {

            //读取license文件
            String licenseText = readLicense();

            //解析licenseText
            String[] arrs = paseLinceseText(licenseText);
            String publicKey = arrs[0];
            String encryptPayload = arrs[1];

            //通过公钥解密payload
            String payload = decryptPayload(encryptPayload, publicKey);

            //反序列化payload
            License license = JSON.parseObject(payload, License.class);

            //校验应用名称
            checkAppName(license.getAppName(), expectedAppName);

            //校验mac地址
            checkMacAddress(license.getMacAddress());

            //校验签发时间
            Date today = new Date();
            checkIatTime(license.getIat(), today);

            //校验有效时间
            checkExpTime(license.getExp(), today);

        } catch (Exception ex) {
            throw new RuntimeException("[校验license发生异常]", ex);
        }

    }

    /**
     * 从classpath下读取license文件<br>
     *
     * @return license字符串
     */
    private static String readLicense() {

        String license;

        try (InputStream inputStream = LicenseUtil.class.getResourceAsStream("/license")) {

            //从指定目录读取lincense
            if (inputStream == null) {
                throw new RuntimeException("[校验license发生异常][在classpath下未读取到license,请检查配置文件]");
            }

            //解析license成字符串
            StringBuilder sb = new StringBuilder();
            String line;

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            license = sb.toString();

            if (StringUtils.isEmpty(license)) {
                throw new RuntimeException("[校验license发生异常]:[读取到license为空]");
            }

        } catch (IOException ex) {
            throw new RuntimeException("[校验license发生异常]", ex);
        }

        return license;

    }

    /**
     * 解析license获取公钥和payload
     *
     * @param lincenseText license文本
     * @return arr[0]:公钥 arr[1]:padload
     */
    private static String[] paseLinceseText(String lincenseText) {

        //切割license
        String[] arrs = licenseText.split("\\.");
        if (arrs.length != 2) {
            throw new RuntimeException("[校验license发生异常]:[license格式非法]");
        }

        return arrs;

    }

    /**
     * 使用公钥解密payload
     *
     * @param encryptPayload 私钥加密的payload
     * @param publicKey      RSA公钥
     * @return 返回paylaod
     */
    private static String decryptPayload(String encryptPayload, String publicKey) {

        try {

            XRsaUtil xRsaUtil = new XRsaUtil(publicKey, null);

            return xRsaUtil.publicDecrypt(encryptPayload);

        } catch (Exception ex) {
            throw new RuntimeException("[校验license发生异常]:[解密license发生异常]", ex);
        }

    }

    /**
     * 校验应用名称
     *
     * @param licenseAppName  license中的应用名称
     * @param expectedAppName 期望的应用名称
     */
    private static void checkAppName(String licenseAppName, String expectedAppName) {

        if (!StringUtils.equalsIgnoreCase(expectedAppName, licenseAppName)) {
            throw new RuntimeException("[校验license发生异常]:[应用名称不匹配]");
        }

    }

    /**
     * 校验mac地址
     */
    private static void checkMacAddress(String licenseAddr) {

        if (StringUtils.isEmpty(licenseAddr)) {
            throw new RuntimeException("[校验license发生异常]:[mac地址为空]");
        }

        String[] macAddressArrs = licenseAddr.split(",");
        HashSet<String> macAddressSet = Sets.newHashSet(macAddressArrs);

        NetworkIF[] networkIFs = getMac();
        if (networkIFs == null || networkIFs.length == 0) {
            throw new RuntimeException("[校验license发生异常]:[未获取到服务器的mac地址]");
        }

        boolean macAddressMatch = false;

        for (NetworkIF net : networkIFs) {
            String macaddr = net.getMacaddr();
            if (macAddressSet.contains(macaddr)) {
                macAddressMatch = true;
                break;
            }
        }

        if (!macAddressMatch) {
            throw new RuntimeException("[校验license发生异常]:[mac地址不匹配]");
        }

    }

    /**
     * 获取本机mac地址
     */
    private static NetworkIF[] getMac() {

        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        return hal.getNetworkIFs();

    }

    /**
     * 校验license签发时间
     */
    private static void checkIatTime(Date iat, Date today) {

        if (!today.after(iat)) {
            throw new RuntimeException("[校验license发生异常]:[服务器时间小于license签发时间]");
        }

    }

    /**
     * 校验license有效时间
     */
    private static void checkExpTime(Date iat, Date today) {

        if (!today.before(iat)) {
            throw new RuntimeException("[校验license发生异常]:[license已过期]");
        }

    }

    public static void main(String[] args) {

        NetworkIF[] macs = getMac();
        for (NetworkIF networkIF : macs) {
            System.err.println(networkIF.getMacaddr());
        }
    }

}
