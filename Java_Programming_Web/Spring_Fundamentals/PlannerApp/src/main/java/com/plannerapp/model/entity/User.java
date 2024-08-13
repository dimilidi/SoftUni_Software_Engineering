package com.plannerapp.model.entity;

import com.plannerapp.model.enums.PriorityName;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Length(min = 3, max = 20)
    @Column(unique=true, nullable=false)
    private String username;

    @Column( nullable=false)
    private String password;

    @Email
    @Column(unique=true, nullable=false)
    private String email;

    @OneToMany(mappedBy = "assignee")
    private Set<Task> tasks;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
