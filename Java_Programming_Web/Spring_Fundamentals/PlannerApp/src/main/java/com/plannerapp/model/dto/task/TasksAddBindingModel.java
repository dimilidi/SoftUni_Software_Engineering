package com.plannerapp.model.dto.task;

import com.plannerapp.model.enums.PriorityName;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class TasksAddBindingModel {
    @Size(min = 2, max = 50,  message = "Description length must be between 2 and 50 characters!")
    private String description;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Future(message = "Due date must be in the future!")
    @NotNull(message = "Due date cannot be null!")
    private LocalDate dueDate;

    @NotNull(message = "You must select a priority!")
    private PriorityName priority;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public PriorityName getPriority() {
        return priority;
    }

    public void setPriority(PriorityName priority) {
        this.priority = priority;
    }
}
