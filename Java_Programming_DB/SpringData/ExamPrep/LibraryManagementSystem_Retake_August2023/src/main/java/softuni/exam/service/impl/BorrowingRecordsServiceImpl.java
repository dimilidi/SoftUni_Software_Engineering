package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.borrowingRecord.BorrowingRecordExportDTO;
import softuni.exam.models.dto.borrowingRecord.BorrowingRecordImportDTO;
import softuni.exam.models.dto.borrowingRecord.BorrowingRecordImportWrapperDTO;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.models.entity.enums.GenreTypeEnum;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.validationUtil.ValidationUtil;
import softuni.exam.util.xmlParser.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {

    public static final String URL = "src/main/resources/files/xml/borrowing-records.xml";
    public static final Path FILE_PATH = Path.of(URL);
    private final BookRepository bookRepository;
    private final LibraryMemberRepository libraryMemberRepository;
    private final ModelMapper modelMapper;
    private StringBuilder sb;
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository, XmlParser xmlParser, ValidationUtil validationUtil, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository, ModelMapper modelMapper) {
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.sb = new StringBuilder();
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {
        BorrowingRecordImportWrapperDTO borrowingRecordDTOS = xmlParser.fromFile(URL, BorrowingRecordImportWrapperDTO.class);

        List<BorrowingRecordImportDTO> borrowingRecords = borrowingRecordDTOS.getBorrowingRecords();

        for (BorrowingRecordImportDTO borrowingRecord : borrowingRecords) {
            if(!validationUtil.isValid(borrowingRecord)
                    || bookRepository.findByTitle(borrowingRecord.getBook().getTitle()).isEmpty()
                    || libraryMemberRepository.findById(borrowingRecord.getMember().getId()).isEmpty()
            ) {
                sb.append("Invalid borrowing record").append(System.lineSeparator());
                continue;
            }

            BorrowingRecord record = modelMapper.map(borrowingRecord, BorrowingRecord.class);
            Book book = bookRepository.findByTitle(record.getBook().getTitle()).get();
            record.setBook(book);
            LibraryMember member = libraryMemberRepository.findById(borrowingRecord.getMember().getId()).get();
            record.setMember(member);

            borrowingRecordRepository.saveAndFlush(record);

            sb.append(String.format("Successfully imported borrowing record %s - %s",
                    book.getTitle(),  record.getBorrowDate().toString())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String exportBorrowingRecords() {
        List<BorrowingRecord> byBookGenreAndBorrowDateBeforeOrderByBorrowDateDesc = borrowingRecordRepository
                .findByBook_GenreAndBorrowDateBeforeOrderByBorrowDateDesc(GenreTypeEnum.SCIENCE_FICTION, LocalDate.of(2021, 9, 10));

        byBookGenreAndBorrowDateBeforeOrderByBorrowDateDesc.stream()
                .map(this::mapBorrowingRecordToExportDTO)
                .forEach(recordDTO ->sb.append(recordDTO));
        return sb.toString().trim();
    }

    private BorrowingRecordExportDTO mapBorrowingRecordToExportDTO(BorrowingRecord record) {
        BorrowingRecordExportDTO exportDTO = modelMapper.map(record, BorrowingRecordExportDTO.class);
        exportDTO.setBookTitle(record.getBook().getTitle());
        exportDTO.setBookAuthor(record.getBook().getAuthor());
        exportDTO.setBorrowedBy(record.getMember().getFirstName() + " " + record.getMember().getLastName());

        return exportDTO;
    }
}
