package com.hotelmanagement.entity;

import com.hotelmanagement.enums.BuildingStatus;
import com.hotelmanagement.enums.VoucherStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "vouchers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vouchers {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "description", length = 500)
    String description;
    @Column(name = "discount_type", nullable = false)
    String discountType;
    @Column(name = "discount_value", nullable = false)
    float discountValue;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    VoucherStatus status;

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
    @OneToMany(mappedBy = "vouchers", cascade = CascadeType.ALL, orphanRemoval = true)
    List<VoucherDetails> voucherDetails;
}
