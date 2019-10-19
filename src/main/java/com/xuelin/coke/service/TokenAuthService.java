package com.xuelin.coke.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.xuelin.coke.common.constants.Constants;
import com.xuelin.coke.common.utils.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 */
@Service
public class TokenAuthService {

    @Autowired
    DbService dbService;

    private static final String issuer = "ORION_AUTH_SERVICE";

    public String issueAccessToken() throws Exception {

        Date issueAt = getIssuedAt();
        Long expires = 1558106184L;
        String appid = "app_test2";
        String secret = "app_secret";

        JWTCreator.Builder builder = JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(issueAt)
                .withExpiresAt(new Date(issueAt.getTime() + expires * 1000L))
                .withClaim(Constants.JWT_CLAIM_APP_ID, appid)
                .withClaim(Constants.JWT_CLAIM_VERSION, Constants.JWT_CLAIM_VERSION_VALUE)
                .withClaim(Constants.JWT_CLAIM_TYPE, Constants.JWT_CLAIM_TYPE_ACCESS_TOKEN);

        String appSecert = secret;
        //对appSecert进行AES加密,防止使用者伪造 token
        appSecert = AESUtil.aesEncode(appSecert);

        return builder.sign(Algorithm.HMAC256(appSecert));
    }


    /**
     * token颁发时间
     **/
    private static Date getIssuedAt() {
        // issue时间提前，为避免多台服务器时间不一致导致时间较慢的服务器无法验证通过时间较快服务器分配的token
        return new Date(System.currentTimeMillis() - 60000);
    }


    public void identifyToken(String token, String appID) {

        try {
            //检验token
            String appSecert = "app_secret";
            appSecert = AESUtil.aesEncode(appSecert);
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(appSecert)).build();
            verifier.verify(token);
        } catch (Exception ex) {
            throw new RuntimeException(" verify  token is failed , reason: " + ex.getMessage() + "token : " + token);
        }
    }


}
