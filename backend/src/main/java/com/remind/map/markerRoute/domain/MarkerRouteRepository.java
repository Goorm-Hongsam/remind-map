package com.remind.map.markerRoute.domain;

import com.remind.map.route.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkerRouteRepository extends JpaRepository<MarkerRoute, Long> {

    List<MarkerRoute> findByRoute(Route route);
}

