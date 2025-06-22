package com.springreport.springreport.post.domain;

import com.springreport.springreport.post.enums.PostStatus;
import com.springreport.springreport.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE post SET status = off WHERE post_id = ?")
@Where(clause = "is_deleted = false")

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String subject;

    @Lob
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Setter
    private PostStatus status;

    private String password;

    @LastModifiedDate
    @Setter
    private LocalDateTime updDate;
    @CreatedDate
    @Setter
    private LocalDateTime regDate;

    @Builder
    public Post(
            String subject,
            String contents,
            String password,
            PostStatus status,
            Member member
    ) {
        this.subject = subject;
        this.contents = contents;
        this.password = password;
        this.status = status;
        this.member = member;
    }

    public static Post of(String contents, String subject, String password, PostStatus status, Member member) {
        Post post = Post.builder()
                .subject(subject)
                .contents(contents)
                .password(password)
                .status(status)
                .member(member)
                .build();
        return post;
    }

    public void opening() {
        this.status = PostStatus.OPEN;
    }
}
