package com.remind.map.route.domain;

import com.remind.map.route.exception.NoSuchRouteException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {

    default Route getById(final Long id) {
        return findById(id)
                .orElseThrow(NoSuchRouteException::new);
    }
}
