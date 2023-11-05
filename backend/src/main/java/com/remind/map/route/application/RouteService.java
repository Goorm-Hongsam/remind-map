package com.remind.map.route.application;

import com.remind.map.marker.domain.Marker;
import com.remind.map.marker.dto.response.MarkerResponse;
import com.remind.map.markerRoute.domain.MarkerRoute;
import com.remind.map.markerRoute.domain.MarkerRouteRepository;
import com.remind.map.route.domain.Route;
import com.remind.map.route.domain.RouteRepository;
import com.remind.map.route.dto.request.RouteCreateRequest;
import com.remind.map.route.dto.response.RouteResponse;
import com.remind.map.route.exception.NoSuchRouteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final MarkerRouteRepository markerRouteRepository;

    public RouteResponse findRouteWithMarkers(Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new NoSuchRouteException());

        List<MarkerRoute> markerRoutes = markerRouteRepository.findByRoute(route);
        List<MarkerResponse> markers = markerRoutes.stream()
                .map(MarkerRoute::getMarker)
                .map(Marker::toResponse)
                .collect(Collectors.toList());



        return RouteResponse.fromEntity(route, markers);
    }
}
