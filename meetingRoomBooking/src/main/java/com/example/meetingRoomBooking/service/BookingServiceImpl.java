package com.example.meetingRoomBooking.service;

import com.example.meetingRoomBooking.model.Booking;
import com.example.meetingRoomBooking.model.ConferenceRoom;
import com.example.meetingRoomBooking.model.User;
import com.example.meetingRoomBooking.repository.BookingRepository;
import com.example.meetingRoomBooking.repository.ConferenceRoomRepository;
import com.example.meetingRoomBooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ConferenceRoomRepository roomRepository;
    private final UserRepository userRepository;

    @Override
    public List<ConferenceRoom> searchAvailableRooms(LocalDateTime startTime, LocalDateTime endTime, int capacity, List<String> equipment) {
        List<ConferenceRoom> rooms = roomRepository.findAll();

        rooms = rooms.stream()
                .filter(r -> r.getCapacity() >= capacity)
                .collect(Collectors.toList());

        if (equipment != null && !equipment.isEmpty()) {
            rooms = rooms.stream()
                    .filter(r -> r.getEquipmentList() != null && r.getEquipmentList().containsAll(equipment))
                    .collect(Collectors.toList());
        }

        List<ConferenceRoom> availableRooms = new ArrayList<>();
        for (ConferenceRoom room : rooms) {
            List<Booking> overlapping = bookingRepository.findOverlappingBookings(room.getRoomId(), startTime, endTime);
            if (overlapping.isEmpty()) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    @Override
    public String bookRoom(String userId, String roomId, LocalDateTime startTime, LocalDateTime endTime) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid userId");
        }

        Optional<ConferenceRoom> roomOpt = roomRepository.findById(roomId);
        if (roomOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid roomId");
        }

        // Check if room is free for given time slot
        List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(roomId, startTime, endTime);
        if (!overlappingBookings.isEmpty()) {
            throw new IllegalStateException("Room is already booked for the given time slot");
        }

        // Create booking ID (UUID)
        String bookingId = UUID.randomUUID().toString();

        Booking booking = Booking.builder()
                .bookingId(bookingId)
                .user(userOpt.get())
                .conferenceRoom(roomOpt.get())
                .startTime(startTime)
                .endTime(endTime)
                .build();

        bookingRepository.save(booking);

        return bookingId;
    }

    @Override
    public boolean cancelBooking(String bookingId) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (bookingOpt.isEmpty()) {
            return false;
        }
        bookingRepository.deleteById(bookingId);
        return true;
    }

    @Override
    public List<Booking> getBookingsForUser(String userId) {
        return bookingRepository.findByUserUserId(userId);
    }
}
