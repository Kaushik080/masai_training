package com.example.meetingRoomBooking.service;

import com.example.meetingRoomBooking.model.Booking;
import com.example.meetingRoomBooking.model.ConferenceRoom;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    List<ConferenceRoom> searchAvailableRooms(LocalDateTime startTime, LocalDateTime endTime, int capacity, List<String> equipment);

    String bookRoom(String userId, String roomId, LocalDateTime startTime, LocalDateTime endTime);

    boolean cancelBooking(String bookingId);

    List<Booking> getBookingsForUser(String userId);
}
