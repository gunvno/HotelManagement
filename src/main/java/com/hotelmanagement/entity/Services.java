package com.hotelmanagement.entity;

import com.hotelmanagement.enums.ServiceStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "services")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @ManyToOne
    @JoinColumn(name = "service_type_id")
    ServiceTypes serviceTypes;
    @Column(name = "name")
    String name;
    @Column(name = "description", length = 1000)
    String description;
    @Column(name = "price")
    float price;
    @ManyToOne
    @JoinColumn(name = "unit_id")
    Unit unit;
    @Column(name = "image")
    String image;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    ServiceStatus status;

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

    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ServiceOrderDetails> serviceOrderDetails;
    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL, orphanRemoval = true)
    List<RoomTypeServices> roomTypeServices;
}
