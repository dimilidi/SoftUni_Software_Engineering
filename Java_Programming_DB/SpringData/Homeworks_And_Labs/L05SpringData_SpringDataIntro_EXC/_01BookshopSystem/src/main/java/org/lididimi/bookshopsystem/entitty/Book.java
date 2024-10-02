package org.lididimi.bookshopsystem.entitty;

import jakarta.persistence.*;
import org.lididimi.bookshopsystem.entitty.base.BaseEntity;
import org.lididimi.bookshopsystem.entitty.enums.AgeRestrictionType;
import org.lididimi.bookshopsystem.entitty.enums.BookEditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", length = 1000)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "edition_type", nullable = false)
    private BookEditionType bookEditionType;

    @Column(nullable = false, columnDefinition = "DECIMAL(19,2)")
    private BigDecimal price;

    @Column(nullable = false)
    private Integer copies;

    @Column(name = "release_date", columnDefinition = "DATETIME")
    private LocalDate releaseDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "age_restriction", nullable = false)
    private AgeRestrictionType ageRestriction;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    public Book() {
    }

    public Book(
            String title,
            BookEditionType bookEditionType,
            BigDecimal price,
            Integer copies,
            LocalDate releaseDate,
            AgeRestrictionType ageRestriction,
            Author author,
            Set<Category> categories
    ) {
        this.title = title;
        this.bookEditionType = bookEditionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookEditionType getBookEditionType() {
        return bookEditionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getCopies() {
        return copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AgeRestrictionType getAgeRestriction() {
        return ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }

    public Set<Category> getCategories() {
        return categories;
    }
}
