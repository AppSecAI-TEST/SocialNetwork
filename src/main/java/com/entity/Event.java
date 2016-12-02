package com.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bohdan on 14.10.16.
 */
@Entity
@Table(name = "events")
public class Event implements Serializable {

    private static final long serialVersionUID = -834212703825352154L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id", nullable = false)
    private long id;
    @Column(name="head")
    private String head;
    @Column(name = "body")
    private String body;
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event() {
    }
}
