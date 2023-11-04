package com.remind.map.route.application;

import com.remind.map.route.domain.Route;
import com.remind.map.route.domain.RouteRepository;
import com.remind.map.route.dto.request.RouteCreateRequest;
import com.remind.map.route.dto.response.RouteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Transactional
    public RouteResponse save(final RouteCreateRequest request)  {
        Route route = request.toEntity();

        Route savedRoute = routeRepository.save(route);
        return RouteResponse.fromEntity(savedRoute);
    }
}
