package com.example.Picnic.model.entities;

import javax.persistence.*;

@Entity(name = "PicnicUser")
public class PicnicUser {
    @Id
    @SequenceGenerator(
            name = "picnicuser_sequence",
            sequenceName = "picnicuser_sequence",
            allocationSize =  1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "picnicuser_sequence"
    )
    @Column(name = "PicnicUserId")
    private Long picnicUserId;

    @Column(name = "Username", nullable = false)
    private String username;

    public PicnicUser() {
    }

    public PicnicUser(String username) {
        this.username = username;
    }

    public Long getPicnicUserId() {
        return picnicUserId;
    }

    public void setPicnicUserId(Long picnicUserId) {
        this.picnicUserId = picnicUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
