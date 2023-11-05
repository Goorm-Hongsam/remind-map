package com.remind.map.route.application;

import com.remind.map.marker.dto.response.MarkerResponse;
import com.remind.map.markerRoute.application.MarkerRouteService;
import com.remind.map.route.domain.Route;
import com.remind.map.route.domain.RouteRepository;
import com.remind.map.route.dto.response.RouteResponse;
import com.remind.map.route.dto.response.RoutesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final MarkerRouteService markerRouteService;

    public RouteResponse findRouteWithMarkers(Long routeId) {
        Route route = routeRepository.getById(routeId);
        List<MarkerResponse> markers = markerRouteService.findMarkersByRoute(route);

        return RouteResponse.fromEntity(route, markers);
    }

    public RoutesResponse findAllRoutes() {
        List<Route> routes = routeRepository.findAll();

        return RoutesResponse.fromEntity(routes);
    }

}
