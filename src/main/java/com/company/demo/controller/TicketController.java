package com.company.demo.controller;

import com.company.demo.controller.request.UpdateTicketRequest;
import com.company.demo.controller.response.TicketResponse;
import com.company.demo.models.Ticket;
import com.company.demo.service.TicketService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

@RestController
@RequestMapping(value = "/planes")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private MapperFacade mapper;

    @GetMapping(value = "/{planeid}/tickets")
    public Page<TicketResponse> getTickets(Pageable pageable) {
        return ticketService.getTickets(pageable).map(new Function<Ticket, TicketResponse>() {
            @Override
            public TicketResponse apply(Ticket ticket) {
                return mapper.map(ticket, TicketResponse.class);
            }
        });
    }

    @GetMapping(value = "/{planeid}/tickets/{id}")
    public TicketResponse getTicketById(@PathVariable("id") Long Id) {
        return mapper.map(ticketService.getTicketById(Id), TicketResponse.class);
    }

    @PutMapping(value = "/{planeid}/tickets/{id}")
    public TicketResponse updateTicket(@PathVariable("id") Long Id, @RequestBody UpdateTicketRequest updateTicket) {
        return mapper.map(ticketService.updateTickets(Id, updateTicket), TicketResponse.class);
    }

    @PatchMapping(value = "/{planeid}/tickets/{id}")
    public void deleteTicket(@PathVariable("id") Long Id) {
        ticketService.getTicketById(Id).setDeleted(true);
        ticketService.saveTicket(ticketService.getTicketById(Id));

    }
}
