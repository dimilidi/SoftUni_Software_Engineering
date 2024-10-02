package org.lididimi.bookshopsystem._02UserSystem.entities;

import jakarta.persistence.*;
import org.lididimi.bookshopsystem._02UserSystem.entities.enums.BackgroundColorType;

import java.util.Set;

@Entity
@Table(name = "albums")
public class Album extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "background_color", nullable = false)
    private BackgroundColorType colorType;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;

    @ManyToMany
    @JoinTable(name = "albums_pictures", joinColumns = @JoinColumn(name = "album_id"), inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private Set<Picture> pictures;

    public Album() {
    }

    public Album(String name, BackgroundColorType colorType, Boolean isPublic, Set<Picture> pictures) {
        this.name = name;
        this.colorType = colorType;
        this.isPublic = isPublic;
        this.pictures = pictures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BackgroundColorType getColorType() {
        return colorType;
    }

    public void setColorType(BackgroundColorType colorType) {
        this.colorType = colorType;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }
}
