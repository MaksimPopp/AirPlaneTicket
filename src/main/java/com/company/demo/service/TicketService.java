package com.company.demo.service;

import com.company.demo.controller.request.UpdateTicketRequest;
import com.company.demo.controller.response.PlaneResponse;
import com.company.demo.controller.response.TicketResponse;
import com.company.demo.models.Plane;
import com.company.demo.models.Ticket;
import com.company.demo.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final PlaneService planeService;
    private final UserService userService;

    @Transactional(readOnly = true)
    public Page<Ticket> getTickets(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Ticket getTicketById(Long Id) {
        return ticketRepository.findById(Id).get();
    }

    @Transactional
    public Ticket updateTickets(Long Id, UpdateTicketRequest updateTicket) {
        Ticket ticketForUpdate = getTicketById(Id);
        ticketForUpdate.setPlane(planeService.getPlaneById(updateTicket.getPlaneId()));
        ticketForUpdate.setUser(userService.getUserById(updateTicket.getUserId()));
        ticketForUpdate.setPrice(updateTicket.getPrice());
        return ticketRepository.save(ticketForUpdate);
    }

    @Transactional
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

}
