package softuni.exam.models.dto.borrowingRecord;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.dto.book.BookInfoDTO;
import softuni.exam.models.dto.libraryMember.LibraryMemberInfoDTO;
import softuni.exam.util.xmlLocalDateAdapter.XmlLocalDateAdapter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "borrowing_record")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordImportDTO {

    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @NotNull
    @XmlElement(name = "borrow_date")
    private LocalDate borrowDate;

    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @NotNull
    @XmlElement(name = "return_date")
    private LocalDate returnDate;

    @Size(min = 3, max = 100)
    @XmlElement
    private String remarks;

    @XmlElement
    private BookInfoDTO book;

    @XmlElement
    private LibraryMemberInfoDTO member;
}


