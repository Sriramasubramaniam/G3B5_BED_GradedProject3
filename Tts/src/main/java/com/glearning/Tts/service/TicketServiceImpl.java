package com.glearning.Tts.service;

import com.glearning.Tts.model.Ticket;
import com.glearning.Tts.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> searchTickets(String searchText) {
        return ticketRepository.findByTicketTitleOrShortDescription(searchText);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket ID"));
    }

    @Override
    public void createTicket(Ticket ticket) {
        ticket.setCreatedDate(LocalDate.now());
        ticketRepository.save(ticket);
    }

    @Override
    public void updateTicket(Long id, Ticket ticket) {
        Ticket existingTicket = getTicketById(id);
        existingTicket.setTicketTitle(ticket.getTicketTitle());
        existingTicket.setShortDescription(ticket.getShortDescription());
        existingTicket.setContent(ticket.getContent());
        ticketRepository.save(existingTicket);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}

