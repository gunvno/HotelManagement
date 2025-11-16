package com.hotelmanagement.entity;

import com.hotelmanagement.enums.ServiceOrderDetailStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "service_order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceOrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @ManyToOne
    @JoinColumn(name = "service_id")
    Services services;

    @ManyToOne
    @JoinColumn(name = "room_booking_detail_id")
    RoomBookingDetails roomBookingDetails;
    @Column(name = "quantity", nullable = false)
    int quantity;
    @Column(name = "amount", nullable = false)
    int amount;
    @Column(name = "price", nullable = false)
    float price;
    @Column(name = "description")
    String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    ServiceOrderDetailStatus status;

    @Column(name = "created_time")
    LocalDateTime createdTime;

    @Column(name = "created_by", length = 100)
    String createdBy;

    @Column(name = "modified_time")
    LocalDateTime modifiedTime;

    @Column(name = "modified_by", length = 100)
    String modifiedBy;

    @Column(name = "deleted")
    Boolean deleted = false;

    @Column(name = "deleted_time")
    LocalDateTime deletedTime;

    @Column(name = "deleted_by", length = 100)
    String deletedBy;
}
