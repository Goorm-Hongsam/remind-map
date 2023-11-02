package com.remind.map.marker.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class MarkerLocationRequest {
    @NotBlank(message = "공백일 수는 없습니다.")
    private Double latitude;

    @NotBlank(message = "공백일 수는 없습니다.")
    private Double longitude;
}
