package com.springreport.springreport.security.service;

import com.springreport.springreport.member.domain.Member;
import com.springreport.springreport.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@Getter
public class JwtTokenService {
    private Key key;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    private final MemberRepository memberRepository;

    public JwtTokenService(@Value("${jwt.secret}") String secretKey, MemberRepository memberRepository) {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(bytes);

        this.memberRepository = memberRepository;
    }

    public static final String MEMBER_ID = "memberId";


    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14; // 14일


    /**
     * Refresh 토큰 생성
     */
    public String createRefreshToken() {

        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    /**
     * RefreshToken 헤더에 실어서 전송
     */
    public void sendRefreshToken(HttpServletResponse response,String refreshToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        setRefreshTokenHeader(response, refreshToken);

        log.info("refresh 토큰 : " + refreshToken);
    }

    /**
     * RefreshToken 헤더 설정
     */
    public void setRefreshTokenHeader(HttpServletResponse response, String refreshToken) {
        response.setHeader(refreshHeader, refreshToken);
    }

    /**
     * RefreshToken DB 저장(업데이트)
     */
    @Transactional
    public void updateRefreshToken(String userName, String refreshToken) {
        Optional<Member> memberOptional = memberRepository.findByUserName(userName);
        Member member = memberOptional.get();
        member.updateRefreshToken(refreshToken);
    }

}
