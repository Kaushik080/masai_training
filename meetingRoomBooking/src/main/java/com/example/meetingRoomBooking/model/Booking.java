package com.example.meetingRoomBooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    private String bookingId;
    private User user;
    private ConferenceRoom conferenceRoom;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
