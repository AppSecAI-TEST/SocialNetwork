package com.entity;

import javax.persistence.*;

/**
 * Created by bohdan on 08.10.16.
 */
@Entity
@Table(name = "user_friend")
@AssociationOverrides({
        @AssociationOverride(name = "colleagues",
                joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "teammates",
                joinColumns = @JoinColumn(name = "friend_id")) })
public class UserFriend {
    @EmbeddedId
    private UserFriendId userFriendId;
    @Column(name = "accept")
    private boolean accept;

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public UserFriendId getUserFriendId() {
        return userFriendId;
    }

    public void setUserFriendId(UserFriendId userFriendId) {
        this.userFriendId = userFriendId;
    }

    @Transient
    public User getFirstUser() {
        return getUserFriendId().getFirstUser();
    }

    public void setFirstUser(User firstUser) {
        getUserFriendId().setFirstUser(firstUser);
    }
    @Transient
    public User getSecondUser() {
        return getUserFriendId().getSecondUser();
    }

    public void setSecondUser(User secondUser) {
        getUserFriendId().setSecondUser(secondUser);
    }
}
