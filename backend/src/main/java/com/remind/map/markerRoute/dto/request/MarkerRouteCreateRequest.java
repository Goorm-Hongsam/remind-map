package com.remind.map.markerRoute.dto.request;

import com.remind.map.marker.domain.Marker;
import com.remind.map.markerRoute.domain.MarkerRoute;
import com.remind.map.route.domain.Memo;
import com.remind.map.route.domain.Route;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MarkerRouteCreateRequest {

    @NotBlank(message = "제목이 공백일 수 없습니다.")
    private String title;

    @NotBlank(message = "내용이 공백일 수 없습니다.")
    private String memo;

    private boolean visiable;

    private int view;

    private LocalDateTime wentDate;

    @Builder
    public MarkerRouteCreateRequest(String title, String memo, boolean visiable, int view, LocalDateTime wentDate) {
        this.title = title;
        this.memo = memo;
        this.visiable = visiable;
        this.view = view;
        this.wentDate = wentDate;
    }

    public MarkerRoute toMarkerRoute(Marker marker, Route route) {
        return MarkerRoute.builder()
                .marker(marker)
                .route(route)
                .build();
    }

    public Route toRoute() {
        return Route.builder()
                .title(title)
                .memo(new Memo(memo))
                .visiable(visiable)
                .view(view)
                .wentDate(wentDate)
                .build();
    }
}
