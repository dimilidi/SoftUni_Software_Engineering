package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.offer.OfferImportDTO;
import softuni.exam.models.dto.offer.OfferImportWrapperDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.enums.ApartmentTypeEnum;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.validation.ValidationUtils;
import softuni.exam.util.xmlParser.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static softuni.exam.constants.Messages.*;

@Service
public class OfferServiceImpl implements OfferService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/xml/offers.xml");
    private final StringBuilder sb;
    private final OfferRepository offerRepository;
    private final XmlParser xmlParser;
    private final ValidationUtils validationUtils;
    private final AgentRepository agentRepository;
    private final ModelMapper modelMapper;
    private final ApartmentRepository apartmentRepository;

    public OfferServiceImpl(
            OfferRepository offerRepository,
            XmlParser xmlParser,
            ValidationUtils validationUtils,
            AgentRepository agentRepository,
            ModelMapper modelMapper,
            ApartmentRepository apartmentRepository
    ) {
        this.sb = new StringBuilder();
        this.offerRepository = offerRepository;
        this.xmlParser = xmlParser;
        this.validationUtils = validationUtils;
        this.agentRepository = agentRepository;
        this.modelMapper = modelMapper;
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        OfferImportWrapperDTO offerImportWrapperDTO = xmlParser.fromFile(FILE_PATH.toFile(), OfferImportWrapperDTO.class);
        List<OfferImportDTO> offerImportWrapperDTOs = offerImportWrapperDTO.getOffers();

        for (OfferImportDTO dto : offerImportWrapperDTOs) {
            Optional<Agent> agentOptional = agentRepository.findFirstByFirstName(dto.getAgent().getFirstName());
            if (!validationUtils.isValid(dto) || agentOptional.isEmpty()
            ) {
                sb.append(String.format(INVALID_ENTITY, OFFER)).append(System.lineSeparator());
                continue;
            }

            Offer offer = modelMapper.map(dto, Offer.class);
            offer.setAgent(agentOptional.get());
            Optional<Apartment> apartmentOptional = apartmentRepository.findById(dto.getApartment().getId());
            apartmentOptional.ifPresent(offer::setApartment);

            offerRepository.saveAndFlush(offer);

            sb.append(String.format(SUCCESSFUL_IMPORT_OFFER, dto.getPrice())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String exportOffers() {
        List<Offer> offersToExport = offerRepository.findAllByApartment_ApartmentTypeOrderByApartment_AreaDescPriceAsc(ApartmentTypeEnum.THREE_ROOMS);
        List<String> offerStringList = offersToExport.stream().map(Offer::toString).toList();
        String result = String.join("", offerStringList);
        return result;
    }
}
