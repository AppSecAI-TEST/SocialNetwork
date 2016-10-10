package com.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bohdan on 08.10.16.
 */
@Entity
@Table(name = "user_friend")
public class UserFriend implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_friend_id", nullable = false)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User firsUser;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend_id")
    private User secondUser;
    @Column(name = "activated")
    private int accept;

    public int getAccept() {
        return accept;
    }

    public void setAccept(int accept) {
        this.accept = accept;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
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
}
