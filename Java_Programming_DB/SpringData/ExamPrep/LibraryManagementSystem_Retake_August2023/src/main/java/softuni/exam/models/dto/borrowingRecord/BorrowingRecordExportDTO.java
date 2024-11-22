package softuni.exam.models.dto.borrowingRecord;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingRecordExportDTO {

    private String bookTitle;
    private String bookAuthor;
    private LocalDate borrowDate;
    private String borrowedBy;

    @Override
    public String toString() {
        return
                "Book title: " + bookTitle + '\n' +
                "*Book author: " + bookAuthor + '\n' +
                "**Date borrowed: " + borrowDate + '\n' +
                "***Borrowed by: " + borrowedBy + "\n";
    }
}
