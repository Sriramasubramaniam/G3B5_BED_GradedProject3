package com.glearning.Tts.controller;

import com.glearning.Tts.model.Ticket;
import com.glearning.Tts.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/tickets")
    public String viewAllTickets(Model model, @RequestParam(name = "searchText", required = false) String searchText) {
        List<Ticket> tickets;

        if (searchText != null && !searchText.isEmpty()) {
            tickets = ticketService.searchTickets(searchText);
        } else {
            tickets = ticketService.getAllTickets();
        }
        model.addAttribute("tickets", tickets);
        return "ticket-list";
    }

    @GetMapping("/tickets/create")
    public String showCreateTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "ticket-create-form";
    }

    @PostMapping("/tickets/create")
    public String createTicket(@ModelAttribute Ticket ticket) {
        ticketService.createTicket(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/edit/{id}")
    public String showEditTicketForm(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.getTicketById(id);
        model.addAttribute("ticket", ticket);
        return "ticket-edit-form";
    }

    @PostMapping("/tickets/edit/{id}")
    public String editTicket(@PathVariable Long id, @ModelAttribute Ticket ticket) {
        ticketService.updateTicket(id, ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/delete/{id}")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }
}

