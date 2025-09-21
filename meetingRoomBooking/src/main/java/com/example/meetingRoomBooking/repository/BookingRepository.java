package com.example.meetingRoomBooking.repository;

import com.example.meetingRoomBooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {

    List<Booking> findOverLappingBookings(String roomId, LocalDateTime startTime, LocalDateTime endTime);
    List<Booking> findUserById(String userId);
}
