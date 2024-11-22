package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.book.BookImportDTO;
import softuni.exam.models.entity.Book;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;
import softuni.exam.util.validationUtil.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class BookServiceImpl implements BookService {

    public static final Path FILE_PATH = Path.of("src/main/resources/files/json/books.json");
    public static final String INVALID_BOOK = "Invalid book\n";
    private final StringBuilder sb;
    private final BookRepository bookRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.validationUtil = validationUtil;
        this.sb = new StringBuilder();
        this.bookRepository = bookRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return bookRepository.count() > 0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importBooks() throws IOException {
        BookImportDTO[] bookImportDTOS = gson.fromJson(readBooksFromFile(), BookImportDTO[].class);

        for (BookImportDTO bookDTO : bookImportDTOS) {
            if (!validationUtil.isValid(bookDTO) || bookRepository.findByTitle(bookDTO.getTitle()).isPresent()) {
                sb.append(INVALID_BOOK);
                continue;
            }

            Book book = modelMapper.map(bookDTO, Book.class);
            bookRepository.saveAndFlush(book);

            sb.append(String.format("Successfully imported book %s - %s%n", bookDTO.getAuthor(), bookDTO.getTitle()));
        }

        return sb.toString().trim();
    }
}
