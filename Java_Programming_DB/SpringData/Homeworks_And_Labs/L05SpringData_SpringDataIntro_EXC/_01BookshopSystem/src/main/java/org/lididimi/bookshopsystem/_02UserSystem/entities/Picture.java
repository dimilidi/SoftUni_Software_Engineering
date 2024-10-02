package org.lididimi.bookshopsystem._02UserSystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    @Size(max = 1000)
    private String caption;

    @Column(columnDefinition = "TEXT")
    @Size(max = 1000)
    private String path;

    public Picture() {}

    public Picture(String title, String caption, String path) {
        this.title = title;
        this.caption = caption;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public @Size(max = 1000) String getCaption() {
        return caption;
    }

    public void setCaption(@Size(max = 1000) String caption) {
        this.caption = caption;
    }

    public @Size(max = 1000) String getPath() {
        return path;
    }

    public void setPath(@Size(max = 1000) String path) {
        this.path = path;
    }
}
