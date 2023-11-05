package com.remind.map.route.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.remind.map.marker.dto.response.MarkerResponse;
import com.remind.map.route.domain.Route;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RouteResponse {
    private Long id;
    private String title;
    private LocalDateTime wentDate;
    private String memo;
    private List<MarkerResponse> markers;

    @Builder
    public RouteResponse(Long id, String title, String memo, LocalDateTime wentDate, List<MarkerResponse> markers) {
        this.id = id;
        this.title = title;
        this.memo = memo;
        this.wentDate = wentDate;
        this.markers = markers;
    }

    public static RouteResponse fromEntity(Route route, List<MarkerResponse> markers) {
        return RouteResponse.builder()
                .id(route.getId())
                .title(route.getTitle())
                .wentDate(route.getWentDate())
                .memo(route.getMemo())
                .markers(markers)
                .build();
    }

    // ... 기존 코드

    public static RouteResponse fromEntityWithoutMarkers(Route route) {
        return RouteResponse.builder()
                .id(route.getId())
                .title(route.getTitle())
                .wentDate(route.getWentDate())
                .memo(route.getMemo())
                .build();
    }

}
