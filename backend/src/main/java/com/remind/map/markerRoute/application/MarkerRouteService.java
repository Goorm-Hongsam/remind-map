package com.remind.map.markerRoute.application;

import com.remind.map.marker.domain.Marker;
import com.remind.map.marker.domain.MarkerRepository;
import com.remind.map.marker.dto.response.MarkerResponse;
import com.remind.map.marker.exception.NoSuchMarkerException;
import com.remind.map.markerRoute.domain.MarkerRoute;
import com.remind.map.markerRoute.domain.MarkerRouteRepository;
import com.remind.map.markerRoute.dto.request.MarkerRouteCreateRequest;
import com.remind.map.markerRoute.dto.response.MarkerRouteCreateResponse;
import com.remind.map.route.domain.Route;
import com.remind.map.route.domain.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MarkerRouteService {

    private final MarkerRepository markerRepository;
    private final RouteRepository routeRepository;
    private final MarkerRouteRepository markerRouteRepository;

    @Transactional
    public List<MarkerRouteCreateResponse> save(final MarkerRouteCreateRequest request) {
        List<Long> markerIds = request.getMarkerIds();
        List<MarkerRouteCreateResponse> responses = new ArrayList<>();

        Route route = request.toRoute();
        routeRepository.save(route);

        List<Marker> markers = markerRepository.findAllById(markerIds);
        markerIds.forEach(id -> {
            if (markers.stream().noneMatch(marker -> marker.getId().equals(id))) {
                throw new NoSuchMarkerException();
            }
        });

        List<MarkerRoute> markerRoutes = markers.stream()
                .map(marker -> request.toMarkerRoute(marker, route))
                .collect(Collectors.toList());

        markerRouteRepository.saveAll(markerRoutes);

        markerRoutes.forEach(markerRoute -> responses.add(new MarkerRouteCreateResponse(markerRoute.getId())));

        return responses;
    }

    public List<MarkerResponse> findMarkersByRoute(Route route) {
        List<MarkerRoute> markerRoutes = markerRouteRepository.findByRoute(route);
        return markerRoutes.stream()
                .map(MarkerRoute::getMarker)
                .map(Marker::toResponse)
                .collect(Collectors.toList());
    }

}
