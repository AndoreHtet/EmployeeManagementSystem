package com.htet.employeemanagementapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class BaseField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @CreatedDate
    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BaseField baseField = (BaseField) obj;
        return Objects.equals(id, baseField.id) && Objects.equals(createdAt, baseField.createdAt) && Objects.equals(updatedAt, baseField.updatedAt);
    }
}
