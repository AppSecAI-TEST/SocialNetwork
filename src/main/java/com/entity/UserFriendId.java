package com.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by bohdan on 08.10.16.
 */
@Embeddable
public class UserFriendId implements Serializable{
    @ManyToOne
    private User firstUser;
    @ManyToOne
    private User secondUser;

    public User getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(User firstUser) {
        this.firstUser = firstUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

}
