package com.hotelmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "room_bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomBookings {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    User staff;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    User customer;

    String bookingType;
    boolean status;
    float totalPrice;
    float totalRoomPrice;
    float totalServicePrice;
    float totalExtraPrice;
    String type;


    LocalDateTime createdTime;
    String createdBy;
    LocalDateTime modifiedTime;
    String modifiedBy;
    Boolean deleted;
    LocalDateTime deletedTime;
    String deletedBy;

    @OneToMany(mappedBy = "roomBookings", cascade = CascadeType.ALL, orphanRemoval = true)
    List<PaymentHistory> paymentHistories;
    @OneToMany(mappedBy = "roomBookings", cascade = CascadeType.ALL, orphanRemoval = true)
    List<RoomBookingDetails> roomBookingDetails;
}
