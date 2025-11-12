package com.hotelmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "room_booking_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomBookingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @ManyToOne
    @JoinColumn(name = "roomid")
    Rooms rooms;
    @ManyToOne
    @JoinColumn(name = "roombookingid")
    RoomBookings roomBookings;

    float price;
    LocalDateTime checkin;
    LocalDateTime checkout;
    LocalDateTime checkinReality;
    LocalDateTime checkoutReality;
    float deposit;


    LocalDateTime createdTime;
    String createdBy;
    LocalDateTime modifiedTime;
    String modifiedBy;
    Boolean deleted;
    LocalDateTime deletedTime;
    String deletedBy;
}
