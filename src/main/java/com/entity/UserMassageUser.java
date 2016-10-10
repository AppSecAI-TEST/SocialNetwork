package com.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bohdan on 10.10.16.
 */
@Entity
@Table(name = "user_massage_user")
public class UserMassageUser implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "massage_id", nullable = false)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User firsUser;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend_id")
    private User secondUser;
    @Column(name = "massage")
    private String massage;

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getFirsUser() {
        return firsUser;
    }

    public void setFirsUser(User firsUser) {
        this.firsUser = firsUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }
}
