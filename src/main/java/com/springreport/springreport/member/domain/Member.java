package com.springreport.springreport.member.domain;

import com.springreport.springreport.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String userName;
    private String userId;
    private String userPassword;
    private String refreshToken;

    @Builder
    Member(
            String userName,
            String userId,
            String userPassword,
            String refreshToken
    ) {
        this.userName = userName;
        this.userId = userId;
        this.userPassword = userPassword;
        this.refreshToken = refreshToken;
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

}
