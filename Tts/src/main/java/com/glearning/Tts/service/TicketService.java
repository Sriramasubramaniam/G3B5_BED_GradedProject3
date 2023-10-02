package com.glearning.Tts.service;

import com.glearning.Tts.model.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getAllTickets();
    List<Ticket> searchTickets(String searchText);
    Ticket getTicketById(Long id);
    void createTicket(Ticket ticket);
    void updateTicket(Long id, Ticket ticket);
    void deleteTicket(Long id);
}
