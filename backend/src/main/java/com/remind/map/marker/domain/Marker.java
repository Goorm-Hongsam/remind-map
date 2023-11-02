package com.remind.map.marker.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "markers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Marker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Embedded
    private Location location;

    @Column
    private Point point;

    @Column(nullable = false)
    private boolean visiable;

    @Column
    @ColumnDefault("0")
    private int view;

    @Column(name = "went_date", nullable = false)
    private LocalDateTime wentDate;

    @Builder
    public Marker(Long id, String title, Location location, Point point, LocalDateTime wentDate) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.point = point;
        this.wentDate = wentDate;
    }
}
