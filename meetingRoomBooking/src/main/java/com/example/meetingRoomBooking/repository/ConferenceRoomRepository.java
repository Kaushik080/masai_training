package com.example.meetingRoomBooking.repository;

import com.example.meetingRoomBooking.model.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, String> {
}
