package com.remind.map.route.presentation;

import com.remind.map.route.application.RouteService;
import com.remind.map.route.dto.response.RouteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/api")
@RestController
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @GetMapping("/route/{routeId}")
    public ResponseEntity<RouteResponse> findRouteWithMarkers(@PathVariable final Long routeId) {
        RouteResponse routeWithMarkers = routeService.findRouteWithMarkers(routeId);
        return ResponseEntity.ok().body(routeWithMarkers);
    }

}
