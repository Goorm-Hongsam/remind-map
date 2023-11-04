package com.remind.map.route.dto.response;

import com.remind.map.marker.domain.Location;
import com.remind.map.marker.domain.Marker;
import com.remind.map.marker.dto.response.MarkerResponse;
import com.remind.map.route.domain.Route;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RouteResponse {
    private Long id;
    private String title;
    private Location location;
    private LocalDateTime wentDate;
    private String memo;

    @Builder
    public RouteResponse(Long id, String title, Location location, String memo, LocalDateTime wentDate) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.memo = memo;
        this.wentDate = wentDate;
    }

    public static RouteResponse fromEntity(Route route) {
        return RouteResponse.builder()
                .id(route.getId())
                .title(route.getTitle())
                .wentDate(route.getWentDate())
                .memo(route.getMemo().getValue())
                .build();
    }
}
