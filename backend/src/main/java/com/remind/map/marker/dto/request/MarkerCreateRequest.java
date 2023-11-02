package com.remind.map.marker.dto.request;

import com.remind.map.marker.domain.Location;
import com.remind.map.marker.domain.Marker;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class MarkerCreateRequest {

    @NotBlank(message = "공백일 수는 없습니다.")
    private String title;

    private Location location;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime wentDate;

    public Marker toEntity(Point point) {
        return Marker.builder()
                .title(title)
                .location(location)
                .wentDate(wentDate)
                .point(point)
                .build();
    }
}
