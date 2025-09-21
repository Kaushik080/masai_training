package com.example.meetingRoomBooking.controller;

import com.example.meetingRoomBooking.model.Booking;
import com.example.meetingRoomBooking.model.ConferenceRoom;
import com.example.meetingRoomBooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // Search available rooms
    @GetMapping("/rooms/search")
    public ResponseEntity<List<ConferenceRoom>> searchRooms(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
            @RequestParam int capacity,
            @RequestParam(required = false) List<String> equipment
    ) {
        List<ConferenceRoom> rooms = bookingService.searchAvailableRooms(startTime, endTime, capacity, equipment);
        return ResponseEntity.ok(rooms);
    }

    // Book room
    @PostMapping("/bookings")
    public ResponseEntity<String> bookRoom(
            @RequestParam String userId,
            @RequestParam String roomId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime
    ) {
        String booking = bookingService.bookRoom(userId, roomId, startTime, endTime);
        return ResponseEntity.ok(booking);
    }

    // Cancel booking
    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable String bookingId) {
        boolean success = bookingService.cancelBooking(bookingId);
        if (success) {
            return ResponseEntity.ok("Booking cancelled successfully");
        }
        return ResponseEntity.status(404).body("Booking not found");
    }

    // Get user bookings
    @GetMapping("/users/{userId}/bookings")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable String userId) {
        List<Booking> bookings = bookingService.getBookingsForUser(userId);
        return ResponseEntity.ok(bookings);
    }
}
