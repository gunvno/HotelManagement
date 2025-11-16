package com.hotelmanagement.entity;

import com.hotelmanagement.enums.ServiceTypeStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "service_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "description")
    String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    ServiceTypeStatus status;


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
    @OneToMany(mappedBy = "serviceTypes", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Services> services;
}

