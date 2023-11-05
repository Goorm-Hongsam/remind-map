package com.remind.map.marker.application;

import com.remind.map.global.utils.GeometryUtil;
import com.remind.map.marker.domain.Direction;
import com.remind.map.marker.domain.Location;
import com.remind.map.marker.domain.Marker;
import com.remind.map.marker.domain.MarkerRepository;
import com.remind.map.marker.dto.request.MarkerCreateRequest;
import com.remind.map.marker.dto.request.MarkerLocationRequest;
import com.remind.map.marker.dto.response.MarkerResponse;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKTReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;
import org.locationtech.jts.io.ParseException;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MarkerService {

    private final MarkerRepository markerRepository;
    private final EntityManager em;

    private static final Double SEARCH_MAX_DISTANCE = 10.0;

    @Transactional
    public MarkerResponse save(final MarkerCreateRequest request) throws ParseException {
        Point point = convertRequestToPoint(request);
        Marker marker = request.toEntity(point);

        Marker savedMarker = markerRepository.save(marker);
        return MarkerResponse.fromEntity(savedMarker);
    }

    public List<MarkerResponse> findMarkersByLocation(MarkerLocationRequest request) {
        Location northEast = GeometryUtil.calculate(request.getLatitude(), request.getLongitude(), SEARCH_MAX_DISTANCE, Direction.NORTHEAST.getBearing());
        Location southWest = GeometryUtil.calculate(request.getLatitude(), request.getLongitude(), SEARCH_MAX_DISTANCE, Direction.SOUTHWEST.getBearing());

        List<Marker> markers = searchMarkersWithinBounds(northEast, southWest);

        return convertMarkersToDTOs(markers);
    }

    public MarkerResponse findMarker(final Long markerId) {
        Marker marker = markerRepository.getById(markerId);

        return marker.toResponse();
    }

    public List<Marker> searchMarkersWithinBounds(Location northEast, Location southWest) {
        String pointFormat = String.format(
                "'LINESTRING(%f %f, %f %f)'",
                northEast.getLatitude(), northEast.getLongitude(), southWest.getLatitude(), southWest.getLongitude()
        );

        Query query = em.createNativeQuery(
                "" +
                        "SELECT * FROM markers AS m WHERE MBRContains(ST_LINESTRINGFROMTEXT(" + pointFormat + "), m.point)",
                Marker.class
        ).setMaxResults(10);

        return query.getResultList();
    }

    public List<MarkerResponse> convertMarkersToDTOs(List<Marker> markers) {
        return markers.stream()
                .map(MarkerResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public Point convertRequestToPoint(MarkerCreateRequest request) throws ParseException {
        return convertLocationToPoint(request.getLocation());
    }

    public Point convertLocationToPoint(Location location) throws ParseException {
        if (location != null) {
            return createPointFromCoordinates(location.getLatitude(), location.getLongitude());
        }
        return null;
    }

    public Point createPointFromCoordinates(Double latitude, Double longitude) throws org.locationtech.jts.io.ParseException {
        String pointWKT = String.format("POINT(%s %s)", latitude, longitude);
        return (Point) new WKTReader().read(pointWKT);
    }

    @Transactional
    public void delete(Long id) {
        Marker marker = markerRepository.getById(id);
        markerRepository.delete(marker);
    }


}