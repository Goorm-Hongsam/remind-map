package com.remind.map.route.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "routes")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column
    private Memo memo;

    @Column(nullable = false)
    private boolean visiable;

    @Column
    @ColumnDefault("0")
    private int view;

    @Column(name = "went_date", nullable = false)
    private LocalDateTime wentDate;

    @Builder
    public Route(String title, Memo memo, boolean visiable, int view, LocalDateTime wentDate) {
        this.title = title;
        this.memo = memo;
        this.visiable = visiable;
        this.view = view;
        this.wentDate = wentDate;
    }
}
