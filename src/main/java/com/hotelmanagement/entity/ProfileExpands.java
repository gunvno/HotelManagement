package com.hotelmanagement.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "profile_expands")
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileExpands {
    @Id
    String id;

    @Column(name = "gender")
    String gender;
    @Column(name = "date_of_birth")
    String dateOfBirth;
    @Column(name = "address")
    String address;
    @Column(name = "identity_number")
    String identityNumber;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    User user;

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
