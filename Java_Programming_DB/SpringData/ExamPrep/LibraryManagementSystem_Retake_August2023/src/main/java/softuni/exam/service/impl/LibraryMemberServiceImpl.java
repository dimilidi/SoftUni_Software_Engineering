package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.libraryMember.LibraryMembersImportDTO;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.validationUtil.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {

    public static final Path FILE_PATH = Path.of("src/main/resources/files/json/library-members.json");
    public static final String INVALID_LIBRARY_MEMBER = "Invalid library member\n";
    private final StringBuilder sb;
    private final LibraryMemberRepository libraryMemberRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public LibraryMemberServiceImpl(LibraryMemberRepository libraryMemberRepository, @Qualifier("gson") Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.validationUtil = validationUtil;
        this.sb = new StringBuilder();
        this.libraryMemberRepository = libraryMemberRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return libraryMemberRepository.count() > 0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importLibraryMembers() throws IOException {
        LibraryMembersImportDTO[] libraryMembersImportDTOS = gson.fromJson(readLibraryMembersFileContent(), LibraryMembersImportDTO[].class);

        for (LibraryMembersImportDTO libraryMembersDTO : libraryMembersImportDTOS) {
            if(!validationUtil.isValid(libraryMembersDTO) ||  libraryMemberRepository.findByPhoneNumber(libraryMembersDTO.getPhoneNumber()).isPresent()) {
                sb.append(INVALID_LIBRARY_MEMBER);
                continue;
            }

            LibraryMember libraryMember = modelMapper.map(libraryMembersDTO, LibraryMember.class);
            libraryMemberRepository.saveAndFlush(libraryMember);

            sb.append(String.format("Successfully imported library member %s - %s%n",
                    libraryMembersDTO.getFirstName(), libraryMember.getLastName()));
        }

        return sb.toString().trim();
    }
}
