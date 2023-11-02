package com.remind.map.marker.domain;

import com.remind.map.marker.exception.NoSuchMarkerException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkerRepository extends JpaRepository<Marker, Long> {

    default Marker getById(final Long id) {
        return findById(id)
                .orElseThrow(NoSuchMarkerException::new);
    }
}
