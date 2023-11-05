package com.remind.map.route.dto.request;

import com.remind.map.route.domain.Route;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class RouteCreateRequest {

    @NotBlank(message = "공백일 수는 없습니다.")
    private String title;

    @NotBlank(message = "공백일 수는 없습니다.")
    private String memo;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime wentDate;

    private boolean visiable;

    public Route toEntity() {
        return Route.builder()
                .title(title)
                .memo(memo)
                .visiable(visiable)
                .wentDate(wentDate)
                .build();
    }
}
