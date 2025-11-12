package com.hotelmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "room_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String description;
    int maximumOccupancy;
    int quantity;
    Boolean status;


    LocalDateTime createdTime;
    String createdBy;
    LocalDateTime modifiedTime;
    String modifiedBy;
    Boolean deleted;
    LocalDateTime deletedTime;
    String deletedBy;
    @OneToMany(mappedBy = "room_types",cascade = CascadeType.ALL, orphanRemoval = true)
    List<Rooms> rooms;
    @OneToMany(mappedBy = "room_types",cascade = CascadeType.ALL, orphanRemoval = true)
    List<AmenityRooms> amenityRooms;
}
