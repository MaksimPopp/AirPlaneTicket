package com.company.demo.controller;

import com.company.demo.controller.request.UpdateTicketRequest;
import com.company.demo.models.Ticket;
import com.company.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/planes")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping(value = "/{planeid}/tickets")
    public Page<Ticket> getTickets(Pageable pageable) {
        return ticketService.getTickets(pageable);
    }

    @GetMapping(value = "/{planeid}/tickets/{id}")
    public Ticket getTicketById(@PathVariable("id") Long Id) {
        return ticketService.getTicketById(Id);
    }

    @PutMapping(value = "/{planeid}/tickets/{id}")
    public Ticket updateTicket(@PathVariable("id") Long Id, @RequestBody UpdateTicketRequest updateTicket) {
        Ticket ticket = ticketService.getTicketById(Id);
        ticket.setPlane(updateTicket.getPlane());
        ticket.setUser(updateTicket.getUser());
        ticket.setPrice(updateTicket.getPrice());
        return ticketService.saveTicket(ticket);
    }

    @PatchMapping(value = "/{planeid}/tickets/{id}")
    public void deleteTicket(@PathVariable("id") Long Id) {
        ticketService.getTicketById(Id).setDeleted(true);
        ticketService.saveTicket(ticketService.getTicketById(Id));

    }
}
