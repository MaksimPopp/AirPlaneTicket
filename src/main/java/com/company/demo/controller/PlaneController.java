package com.company.demo.controller;


import com.company.demo.controller.request.CreatePlaneRequest;
import com.company.demo.controller.request.UpdatePlaneRequest;
import com.company.demo.models.Plane;
import com.company.demo.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping(value = "/planes")
public class PlaneController {
    @Autowired
    private PlaneService planeService;

    @GetMapping
    public Page<Plane> getPlanes(Pageable pageable) {
        return planeService.getPlanes(pageable);
    }


    @GetMapping(value = "/{id}")
    public Plane getPlaneById(@PathVariable("id") Long Id) {
        return planeService.getPlaneById(Id);
    }

    @PostMapping
    public Plane createPlane(@RequestBody @Valid CreatePlaneRequest newPlane) {
        Plane plane = new Plane();
        plane.setId(newPlane.getId());
        plane.setName(newPlane.getName());
        plane.setPlaces(newPlane.getPlaces());
        plane.setDepart(newPlane.getDepart());
        plane.setDuration(newPlane.getDuration());
        plane.setDepartFrom(newPlane.getDepartFrom());
        plane.setArriveTo(newPlane.getArriveTo());
        plane.setTickets(newPlane.getTickets());
        plane.setDeleted(newPlane.isDeleted());
        return planeService.savePlane(plane);
    }

    @PutMapping(value = "/{id}")
    public Plane updatePlane(@PathVariable("id") Long Id, @RequestBody UpdatePlaneRequest updatePlane) {
        Plane plane = planeService.getPlaneById(Id);
        plane.setName(updatePlane.getName());
        plane.setPlaces(updatePlane.getPlaces());
        plane.setDepart(updatePlane.getDepart());
        plane.setDuration(updatePlane.getDuration());
        plane.setDepartFrom(updatePlane.getDepartFrom());
        plane.setArriveTo(updatePlane.getArriveTo());
        plane.setTickets(updatePlane.getTickets());
        return planeService.savePlane(plane);

    }

    @PatchMapping(value = "/{id}")
    public void deletePlane(@PathVariable("id") @NotNull Long Id) {
        planeService.getPlaneById(Id).setDeleted(true);
        planeService.savePlane(planeService.getPlaneById(Id));
    }
}
