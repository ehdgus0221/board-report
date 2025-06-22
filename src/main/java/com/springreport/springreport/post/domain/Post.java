package com.springreport.springreport.post.domain;

import com.springreport.springreport.post.enums.PostStatus;
import com.springreport.springreport.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @Setter
    private String title;
    @Setter
    private String description;
    @Setter
    private String writeUser;
    @Enumerated(EnumType.STRING)
    @Setter
    private PostStatus status;
    @UpdateTimestamp
    @Setter
    private LocalDateTime updDate;
    @UpdateTimestamp
    @Setter
    private LocalDateTime regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Setter
    private Member member;
    @Setter
    private String writeUserPassword;

    @Builder
    public Post(
            String title,
            String description,
            LocalDateTime updDate,
            String writeUser,
            String writeUserPassword
    ) {
        this.title = title;
        this.description = description;
        this.updDate = updDate;
        this.writeUser = writeUser;
        this.writeUserPassword = writeUserPassword;
    }

    public void opening() {
        this.status = PostStatus.OPEN;
    }
}
