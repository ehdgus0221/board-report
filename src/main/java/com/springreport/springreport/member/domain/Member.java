package com.springreport.springreport.member.domain;

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

    @Builder
    Member(
            String userName,
            String userId,
            String userPassword
    ) {
        this.userName = userName;
        this.userId = userId;
        this.userPassword = userPassword;
    }

}
