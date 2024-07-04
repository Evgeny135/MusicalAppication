package org.application.musicalappication.model;

import jakarta.persistence.*;

@Entity
@Table(name = "track_type")
public class TrackType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public TrackType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TrackType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
