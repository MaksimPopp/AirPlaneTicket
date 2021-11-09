package com.company.demo.service;

import com.company.demo.models.Plane;
import com.company.demo.models.User;
import com.company.demo.repository.PlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaneService {
    private final PlaneRepository planeRepository;

    public Page<Plane> getPlanes(Pageable pageable) {
        return planeRepository.findAll(pageable);
    }
    public Plane getPlaneById(Long id){ return planeRepository.findById(id).get(); }
    public Plane savePlane(Plane plane){return planeRepository.save(plane);}


}
