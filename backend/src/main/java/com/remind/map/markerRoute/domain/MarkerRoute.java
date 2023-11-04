package com.remind.map.markerRoute.domain;

import com.remind.map.marker.domain.Marker;
import com.remind.map.route.domain.Route;
import lombok.*;

import javax.persistence.*;

@Getter
@Table(name = "markerRoute")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MarkerRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marker_id")
    private Marker marker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;

    @Builder
    public MarkerRoute(Marker marker, Route route) {
        this.marker = marker;
        this.route = route;
    }
}
