package com.jhj.common.auth;

import cn.hutool.jwt.JWTHeader;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jhj.common.enums.AudienceType;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtil {

    private final static String SECRET = "1wqeqweqeq";
    private final static String ISSUSER = "auth.jhj.com";
    private final static String SUBJECT = "this is  token";
    private static final String JWT_CLAIM = "empId";

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(JWT_CLAIM, getEmpId(token))
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    /**
     * 验证token
     *
     * @param rightToken
     * @param secretKey
     * @return
     */
    public static boolean validateToken(String rightToken, String secretKey) {
        // 密钥
        byte[] key = secretKey.getBytes();
        // 默认验证HS256的算法
        try {
            return cn.hutool.jwt.JWT.of(rightToken).setSigner("HS256",key).verify();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 创建token
     *
     * @param subject    developer或api
     * @param uniqueName appId或username
     * @param secretKey  密钥
     * @return
     */
    public static String createToken(String subject, String uniqueName, String secretKey) {
        // 密钥
        byte[] keys = secretKey.getBytes();
        return cn.hutool.jwt.JWT.create()
                .setPayload("sub", subject)
                .setPayload("name", uniqueName)
                .setKey(keys)
                .sign();
    }

    /**
     * 解析token
     *
     * @param rightToken
     */
    public static cn.hutool.jwt.JWT analyzeToken(String rightToken) {
        cn.hutool.jwt.JWT jwt = cn.hutool.jwt.JWT.of(rightToken);
        // JWT
        jwt.getHeader(JWTHeader.TYPE);
        // HS256
        jwt.getHeader(JWTHeader.ALGORITHM);
        // 1234567890
        jwt.getPayload("sub");
        // looly
        String name = (String) jwt.getPayload("name");
        // true
        jwt.getPayload("admin");
        return jwt;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static Long getEmpId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(JWT_CLAIM).asLong();
        } catch (JWTDecodeException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * @param autherId
     * @param adType
     * @return 加密的token
     */
    public static String sign(Long autherId, AudienceType adType, Date expireDate) {
        Date nowDate = new Date();
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        Map <String, Object> map = new HashMap <>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create()
                // 设置头部信息 Header
                .withHeader(map)
                // 设置 载荷 Payload
                .withClaim(JWT_CLAIM, autherId)
                .withIssuer(ISSUSER)
                .withSubject(SUBJECT)
                .withAudience(adType.toString())
                // 生成签名的时间
                .withIssuedAt(nowDate)
                // 签名过期的时间
                .withExpiresAt(expireDate)
                // 签名 Signature
                .sign(algorithm);
    }

}
