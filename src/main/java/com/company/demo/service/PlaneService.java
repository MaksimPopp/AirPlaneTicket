package com.company.demo.service;

import com.company.demo.controller.request.CreatePlaneRequest;
import com.company.demo.controller.request.UpdatePlaneRequest;
import com.company.demo.controller.response.PlaneResponse;
import com.company.demo.models.Plane;
import com.company.demo.models.Ticket;
import com.company.demo.models.User;
import com.company.demo.repository.PlaneRepository;
import com.company.demo.repository.TicketRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class PlaneService {
    private final PlaneRepository planeRepository;
    private final TicketRepository ticketRepository;

    @Transactional(readOnly = true)
    public Page<Plane> getPlanes(Pageable pageable) {
        return planeRepository.findAll(pageable);
    }


    @Transactional(readOnly = true)
    public Plane getPlaneById(Long id) {
        return planeRepository.findById(id).get();
    }

    @Transactional
    public Plane createPlane(CreatePlaneRequest planeRequest) {
        Plane plane = new Plane();
        plane.setName(planeRequest.getName());
        plane.setPlaces(planeRequest.getPlaces());
        plane.setDepart(planeRequest.getDepart());
        plane.setDuration(planeRequest.getDuration());
        plane.setDepartFrom(planeRequest.getDepartFrom());
        plane.setArriveTo(planeRequest.getArriveTo());
        List<Ticket> tickets = createTicketForPlane(plane);
        plane.setTickets(tickets);
        return savePlane(plane);
    }

    @Transactional
    private List<Ticket> createTicketForPlane(Plane plane) {
        return IntStream.range(0, 10)
                .mapToObj(it -> createTicket(plane))
                .collect(Collectors.toList());
    }

    @Transactional
    public Ticket createTicket(Plane plane) {
        Ticket ticket = new Ticket();
        ticket.setPlane(plane);
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Plane updatePlane(Long Id, UpdatePlaneRequest plane) {
        Plane updatePlane = getPlaneById(Id);
        updatePlane.setName(plane.getName());
        updatePlane.setPlaces(plane.getPlaces());
        updatePlane.setDepart(plane.getDepart());
        updatePlane.setDuration(plane.getDuration());
        updatePlane.setDepartFrom(plane.getDepartFrom());
        updatePlane.setArriveTo(plane.getArriveTo());
        return savePlane(updatePlane);
    }

    @Transactional
    public Plane savePlane(Plane plane) {
        return planeRepository.save(plane);
    }


}
