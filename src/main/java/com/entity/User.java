package com.entity;



import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bohdan on 15.09.16.
 */
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name= "name")
    private String name;
    @Column(name= "surname")
    private String surname;
    @Column(name= "info")
    private String info;
    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @Column(name = "avatar")
    private byte[] avatar;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserRole> userRole = new HashSet<>(0);
    @OneToMany(mappedBy = "colleagues", cascade=CascadeType.ALL)
    private Set<User> colleagues = new HashSet<>();
    @OneToMany(mappedBy = "teammates", cascade=CascadeType.ALL)
    private Set<User> teammates = new HashSet<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(String av) {
        setAvatar(Img(av));
    }
    public void setAvatar(byte[] av) {
        this.avatar = av;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    public byte[] Img(String av)
    {
        File file = new File("/home/bohdan/Картинки/" + av);
        byte[] bFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bFile;
    }
    public String getImgAsBase64() {
        String encoded = Base64.getEncoder().encodeToString(avatar);
        return encoded;
    }

    public Set<User> getColleagues() {
        return colleagues;
    }

    public void setColleagues(Set<User> colleagues) {
        this.colleagues = colleagues;
    }

    public Set<User> getTeammates() {
        return teammates;
    }

    public void setTeammates(Set<User> teammates) {
        this.teammates = teammates;
    }
}
