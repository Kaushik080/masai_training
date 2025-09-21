package com.example.meetingRoomBooking.repository;

import com.example.meetingRoomBooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
