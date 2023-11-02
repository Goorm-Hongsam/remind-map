package com.remind.map.marker.presentation;

import com.remind.map.marker.application.MarkerService;
import com.remind.map.marker.dto.request.MarkerCreateRequest;
import com.remind.map.marker.dto.request.MarkerLocationRequest;
import com.remind.map.marker.dto.response.MarkerResponse;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.io.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/v1/api")
@RestController
@RequiredArgsConstructor
public class MarkerController {

    private final MarkerService markerService;

    @PostMapping("/marker")
    public ResponseEntity<MarkerResponse> save(
            @Valid @RequestBody final MarkerCreateRequest request
    ) throws ParseException {
        MarkerResponse response = markerService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/markers")
    public List<MarkerResponse> findMarkersByLocation(@ModelAttribute MarkerLocationRequest request) {
        return markerService.findMarkersByLocation(request);
    }

    @DeleteMapping("/marker/{markerId}")
    public ResponseEntity<Void> delete(@PathVariable final Long markerId) {
        markerService.delete(markerId);
        return ResponseEntity.noContent().build();
    }
}
