package com.example.meetingRoomBooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "conference_rooms")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConferenceRoom {

    @Id
    private String roomId;
    private String name;
    private int capacity;
    private String location;
    @ElementCollection
    @CollectionTable(
            name = "conference_room_equipment",
            joinColumns = @JoinColumn(name = "room_id")
    )
    @Column(name = "equipment")
    private List<String> equipmentList;

}
