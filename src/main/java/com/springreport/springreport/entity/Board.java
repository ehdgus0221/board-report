package com.springreport.springreport.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springreport.springreport.enums.BoardStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    @Setter
    private String title;
    @Setter
    private String description;
    @Setter
    private String writeUser;
    @Enumerated(EnumType.STRING)
    @Setter
    private BoardStatus status;
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

    @Builder
    public Board(
            String title,
            String description,
            LocalDateTime updDate,
            String writeUser
    ) {
        this.title = title;
        this.description = description;
        this.updDate = updDate;
        this.writeUser = writeUser;
    }

    public void opening() {
        this.status = BoardStatus.OPEN;
    }
}
