package com.glearning.Tts.repository;

import com.glearning.Tts.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    @Query("SELECT t FROM Ticket t WHERE LOWER(t.ticketTitle) LIKE %:searchText% OR LOWER(t.shortDescription) LIKE %:searchText%")
    List<Ticket> findByTicketTitleOrShortDescription(String searchText);
}
