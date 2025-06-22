package com.springreport.springreport.post.domain;

import com.springreport.springreport.post.enums.PostStatus;
import com.springreport.springreport.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE post SET status = 'CLOSE' WHERE post_id = ?")
@Where(clause = "status = 'OPEN'")
@EntityListeners(AuditingEntityListener.class)
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

    private String writer;
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
            String writer,
            String password,
            Member member
    ) {
        this.subject = subject;
        this.contents = contents;
        this.writer = writer;
        this.password = password;
        this.member = member;
    }

    public static Post of(String contents, String subject, String writer, String password, Member member) {
        Post post = Post.builder()
                .subject(subject)
                .contents(contents)
                .writer(writer)
                .password(password)
                .member(member)
                .build();
        return post;
    }

    public void update(String newSubject, String newContents, String newWriter) {
        this.subject = newSubject;
        this.contents = newContents;
        this.writer = newWriter;
    }

    public void create() {
        this.status = PostStatus.OPEN;
    }
    public void delete() {this.status = PostStatus.CLOSE;}
}
