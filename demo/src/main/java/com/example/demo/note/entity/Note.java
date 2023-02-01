package com.example.demo.note.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "note")
@NoArgsConstructor
@AllArgsConstructor
public class Note implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid2")
    @GenericGenerator(name = "system-uuid2", strategy = "uuid2")
    private String id;

    @Basic(optional = false)
    private String userId;

    @Basic(optional = false)
    @CreatedDate
    private ZonedDateTime created;

    @Basic(optional = false)
    private String content;

    @PrePersist
    protected void onCreate() {
        created = ZonedDateTime.now().withNano(0);
    }
}