package org.lididimi.bookshopsystem._02UserSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.lididimi.bookshopsystem._02UserSystem.entities.annotations.emial.Email;
import org.lididimi.bookshopsystem._02UserSystem.entities.annotations.password.Password;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false)
    @Size(min = 4, max = 30)
    private String username;

    @Column(nullable = false)
    @Password(minLength = 8, maxLength = 20)
    private String password;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(name = "registered_on", columnDefinition = "DATETIME", nullable = false)
    private LocalDate registeredOn;

    @Column(name = "last_time_logged_in", columnDefinition = "DATETIME")
    private LocalDate lastTimeLoggedIn;

    @Column
    private Integer age;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    private Town bornInTown;

    @ManyToOne
    private Town currentlyLivingInTown;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String fullName;

    @ManyToMany
    @JoinTable(name = "users_friends", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<User> friends;

    @OneToMany
    @JoinTable(name = "users_albums", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "album_id"))
    private Set<Album> albums;

    public User() {
    }


    public User(String username, String password, String email, LocalDate registeredOn, LocalDate lastTimeLoggedIn, Integer age, Boolean isDeleted, Town bornInTown, Town currentlyLivingInTown) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registeredOn = registeredOn;
        this.lastTimeLoggedIn = lastTimeLoggedIn;
        this.age = age;
        this.isDeleted = isDeleted;
        this.bornInTown = bornInTown;
        this.currentlyLivingInTown = currentlyLivingInTown;
    }

    public @Size(min = 4, max = 30) String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 4, max = 30) String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public LocalDate getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDate lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Town getBornInTown() {
        return bornInTown;
    }

    public void setBornInTown(Town bornInTown) {
        this.bornInTown = bornInTown;
    }

    public Town getCurrentlyLivingInTown() {
        return currentlyLivingInTown;
    }

    public void setCurrentlyLivingInTown(Town currentlyLivingInTown) {
        this.currentlyLivingInTown = currentlyLivingInTown;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}