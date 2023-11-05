package com.remind.map.marker.dto.response;

import com.remind.map.marker.domain.Location;
import com.remind.map.marker.domain.Marker;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MarkerResponse {

    private Long id;
    private String title;
    private String memo;
    private Location location;
    private LocalDateTime wentDate;

    @Builder
    public MarkerResponse(Long id, String title, String memo, Location location, LocalDateTime wentDate) {
        this.id = id;
        this.title = title;
        this.memo = memo;
        this.location = location;
        this.wentDate = wentDate;
    }

    public static MarkerResponse fromEntity(Marker marker) {
        return MarkerResponse.builder()
                .id(marker.getId())
                .title(marker.getTitle())
                .memo(marker.getMemo())
                .wentDate(marker.getWentDate())
                .location(marker.getLocation())
                .build();
    }
}
