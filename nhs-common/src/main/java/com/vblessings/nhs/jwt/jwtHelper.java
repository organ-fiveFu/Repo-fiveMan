package com.vblessings.nhs.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.vblessings.nhs.consts.CommonConstants;

import java.util.Date;

public class jwtHelper {

    public static final long REDIS_TIMEOUT_DAY = 86400;

    /**
     * 创建秘钥
     */
    private static final byte[] SECRET = "6MNSobBRCHGIO0fS6MNSobBRCHGIO0fS".getBytes();

    /**
     * 生成Token
     * @param ijwtInfo
     * @return
     */
    public static String createToken(IJWTInfo ijwtInfo) {
        try {
            /**
             * 1.创建一个32-byte的密匙
             */
            MACSigner macSigner = new MACSigner(SECRET);
            /**
             * 2. 建立payload 载体
             */
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .expirationTime(new Date(System.currentTimeMillis() + REDIS_TIMEOUT_DAY))
                    .claim(CommonConstants.JWT_KEY_USER_ID,ijwtInfo.getId())
                    .claim(CommonConstants.JWT_KEY_NAME,ijwtInfo.getName())
                    .claim(CommonConstants.JWT_KEY_EMPLOYEE_CODE,ijwtInfo.getUniqueName())
                    .build();

            /**
             * 3. 建立签名
             */
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(macSigner);

            /**
             * 4. 生成token
             */
            String token = signedJWT.serialize();
            return token;
        } catch (KeyLengthException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }


}
