package com.company.demo.service;

import com.company.demo.models.Ticket;
import com.company.demo.models.User;
import com.company.demo.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    public Page<Ticket> getTickets(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }
    public  Ticket getTicketById (Long Id){return ticketRepository.findById(Id).get();}
    public Ticket saveTicket(Ticket ticket){return ticketRepository.save(ticket);}

}
