package com.remind.map.markerRoute.presentation;

import com.remind.map.marker.dto.request.MarkerCreateRequest;
import com.remind.map.marker.dto.response.MarkerResponse;
import com.remind.map.markerRoute.application.MarkerRouteService;
import com.remind.map.markerRoute.dto.request.MarkerRouteCreateRequest;
import com.remind.map.markerRoute.dto.response.IntegrativeMarkerRouteCreateResponse;
import com.remind.map.markerRoute.dto.response.MarkerRouteCreateResponse;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.io.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/v1/api")
@RestController
@RequiredArgsConstructor
public class MarkerRouteController {

    private final MarkerRouteService markerRouteService;

    @PostMapping("/marker-route")
    public ResponseEntity<IntegrativeMarkerRouteCreateResponse> save(
            @Valid @RequestBody final MarkerRouteCreateRequest request
    ) {
        IntegrativeMarkerRouteCreateResponse response = markerRouteService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
