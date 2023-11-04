package com.remind.map.markerRoute.application;

import com.remind.map.marker.domain.Marker;
import com.remind.map.marker.domain.MarkerRepository;
import com.remind.map.markerRoute.domain.MarkerRoute;
import com.remind.map.markerRoute.domain.MarkerRouteRepository;
import com.remind.map.markerRoute.dto.request.MarkerRouteCreateRequest;
import com.remind.map.markerRoute.dto.response.MarkerRouteCreateResponse;
import com.remind.map.route.domain.Route;
import com.remind.map.route.domain.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MarkerRouteService {

    private final MarkerRepository markerRepository;
    private final RouteRepository routeRepository;
    private final MarkerRouteRepository markerRouteRepository;

    @Transactional
    public MarkerRouteCreateResponse save(final Long markerId, MarkerRouteCreateRequest request){
        Marker marker = markerRepository.getById(markerId);
        Route route = request.toRoute();
        MarkerRoute markerRoute = request.toMarkerRoute(marker, route);

        markerRepository.save(marker);
        routeRepository.save(route);
        Long markerRouteId = markerRouteRepository.save(markerRoute).getId();

        return new MarkerRouteCreateResponse(markerRouteId);
    }
}
