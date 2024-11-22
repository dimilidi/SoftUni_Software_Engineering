package softuni.exam.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import softuni.exam.models.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "borrowing_records")
public class BorrowingRecord extends BaseEntity {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "return_date" ,nullable = false)
    private LocalDate returnDate;

    private String remarks;

    @ManyToOne
    private Book book;

    @ManyToOne
    private LibraryMember member;
}
