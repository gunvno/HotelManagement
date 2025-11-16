package com.hotelmanagement.entity;

import com.hotelmanagement.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "payment_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_booking_id", nullable = false)
    RoomBookings roomBookings;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    PaymentMethod paymentMethod;
    @Column(name = "amount", nullable = false)
    float amount;
    @Column(name = "payment_time", nullable = false)
    LocalDate paymentTime;
    @Column(name = "node", length = 500)
    String node;



}
