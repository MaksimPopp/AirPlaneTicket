package com.company.demo.controller;


import com.company.demo.controller.request.CreatePlaneRequest;
import com.company.demo.controller.request.UpdatePlaneRequest;
import com.company.demo.controller.response.PlaneResponse;
import com.company.demo.models.Plane;
import com.company.demo.service.PlaneService;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.glasnost.orika.Converter;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Validated
@RestController
@RequestMapping(value = "/planes")
public class PlaneController {
    @Autowired
    private PlaneService planeService;

    @Autowired
    private MapperFacade mapper;

    @GetMapping
    public Page<PlaneResponse> getPlanes(Pageable pageable) {
        return planeService.getPlanes(pageable).map(new Function<Plane, PlaneResponse>() {
            @Override
            public PlaneResponse apply(Plane plane) {
                return mapper.map(plane, PlaneResponse.class);
            }
        });
    }


    @GetMapping(value = "/{id}")
    public PlaneResponse getPlaneById(@PathVariable("id") Long Id) {
        return mapper.map(planeService.getPlaneById(Id), PlaneResponse.class);
    }

    @PostMapping
    public PlaneResponse createPlane(@RequestBody @Valid CreatePlaneRequest newPlane) {
        Plane plane = planeService.createPlane(newPlane);
        return mapper.map(plane, PlaneResponse.class);
    }

    @PutMapping(value = "/{id}")
    public PlaneResponse updatePlane(@PathVariable("id") Long Id, @RequestBody UpdatePlaneRequest updatePlane) {
        return mapper.map(planeService.updatePlane(Id, updatePlane), PlaneResponse.class);

    }

    @PatchMapping(value = "/{id}")
    public void deletePlane(@PathVariable("id") @NotNull Long Id) {
        planeService.getPlaneById(Id).setDeleted(true);
        planeService.savePlane(planeService.getPlaneById(Id));
    }
}
