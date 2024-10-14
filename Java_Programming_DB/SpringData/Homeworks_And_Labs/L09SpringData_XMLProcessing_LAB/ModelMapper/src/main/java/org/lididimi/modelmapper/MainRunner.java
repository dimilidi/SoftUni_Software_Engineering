package org.lididimi.modelmapper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.lididimi.modelmapper.dtos.AddressDTO;
import org.lididimi.modelmapper.dtos.AddressWithCollectionDTO;
import org.lididimi.modelmapper.dtos.CountryXMLDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

@Component
public class MainRunner implements CommandLineRunner {

    private final JAXBContext addressContext;
    private final JAXBContext countryContext;
    private final JAXBContext addressWithCollectionContext;

    public MainRunner(@Qualifier("addressContext") JAXBContext addressContext,
                      @Qualifier("countryContext") JAXBContext countryContext,
                      @Qualifier("addressWithCollectionContext") JAXBContext addressWithCollectionContext) {
        this.addressContext = addressContext;
        this.countryContext = countryContext;
        this.addressWithCollectionContext = addressWithCollectionContext;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize BufferedReader for user input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // Display menu
            System.out.println("\nChoose an option:");
            System.out.println("1: Marshal AddressDTO");
            System.out.println("2: Marshal AddressWithCollectionDTO");
            System.out.println("3: Marshal CountryXMLDTO");
            System.out.println("4: Unmarshal AddressDTO");
            System.out.println("5: Unmarshal AddressWithCollectionDTO");
            System.out.println("Enter your choice (or press Ctrl+D to exit):");

            String choice = reader.readLine();
            if (choice == null || choice.isEmpty()) {
                System.out.println("Exiting application.");
                break; // Exit if Ctrl+D or an empty line is encountered
            }

            switch (choice) {
                case "1":
                    // Marshal AddressDTO
                    AddressDTO addressDTO = new AddressDTO(1, "Bulgaria", "Sofia");
                    marshalAddress(addressDTO);
                    break;

                case "2":
                    // Marshal AddressWithCollectionDTO
                    CountryXMLDTO countryDTO = new CountryXMLDTO("Bulgaria");
                    AddressWithCollectionDTO addressWithCollectionDTO = new AddressWithCollectionDTO(1, countryDTO, "Sofia");
                    marshalAddress(addressWithCollectionDTO);
                    break;

                case "3":
                    // Marshal CountryXMLDTO
                    CountryXMLDTO country = new CountryXMLDTO("Bulgaria");
                    marshalCountry(country);
                    break;

                case "4":
                    // Unmarshal AddressDTO
                    System.out.println("Enter XML for AddressDTO:");
                    String addressXmlInput = readXmlInput(reader);
                    if (addressXmlInput != null) {
                        try {
                            AddressDTO unmarshalledAddress = unmarshalAddress(addressXmlInput);
                            System.out.println("Unmarshalled AddressDTO: " + unmarshalledAddress);
                        } catch (Exception e) {
                            System.err.println("Error unmarshalling AddressDTO: " + e.getMessage());
                        }
                    }
                    break;

                case "5":
                    // Unmarshal AddressWithCollectionDTO
                    System.out.println("Enter XML for AddressWithCollectionDTO:");
                    String addressWithCollectionXmlInput = readXmlInput(reader);
                    if (addressWithCollectionXmlInput != null) {
                        try {
                            AddressWithCollectionDTO unmarshalledAddressWithCollection = unmarshalAddressWithCollection(addressWithCollectionXmlInput);
                            System.out.println("Unmarshalled AddressWithCollectionDTO: " + unmarshalledAddressWithCollection);
                        } catch (Exception e) {
                            System.err.println("Error unmarshalling AddressWithCollectionDTO: " + e.getMessage());
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        }
    }

    // Helper method to read XML input from the user
    private String readXmlInput(BufferedReader reader) throws Exception {
        StringBuilder inputBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            inputBuilder.append(line).append("\n");
        }
        return inputBuilder.toString().isEmpty() ? null : inputBuilder.toString();
    }

    // Marshalling methods
    private void marshalAddress(AddressDTO addressDTO) throws Exception {
        Marshaller addressMarshaller = addressContext.createMarshaller();
        addressMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        addressMarshaller.marshal(addressDTO, System.out);
    }

    private void marshalAddress(AddressWithCollectionDTO addressWithCollectionDTO) throws Exception {
        Marshaller addressMarshaller = addressWithCollectionContext.createMarshaller();
        addressMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        addressMarshaller.marshal(addressWithCollectionDTO, System.out);
    }

    private void marshalCountry(CountryXMLDTO country) throws Exception {
        Marshaller countryMarshaller = countryContext.createMarshaller();
        countryMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        countryMarshaller.marshal(country, System.out);
    }

    // Unmarshalling methods
    private AddressDTO unmarshalAddress(String xmlInput) throws Exception {
        Unmarshaller unmarshaller = addressContext.createUnmarshaller();
        return (AddressDTO) unmarshaller.unmarshal(new StringReader(xmlInput));
    }

    private AddressWithCollectionDTO unmarshalAddressWithCollection(String xmlInput) throws Exception {
        Unmarshaller unmarshaller = addressWithCollectionContext.createUnmarshaller();
        return (AddressWithCollectionDTO) unmarshaller.unmarshal(new StringReader(xmlInput));
    }
}
