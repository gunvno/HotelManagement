package com.hotelmanagement.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(name = "module")
    String module;
    @Column(name = "action")
    String action;
    @Column(name = "description")
    String description;

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
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Roles> roles;
}
