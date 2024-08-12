package com.resellerapp.service.impl;

import com.resellerapp.model.*;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.util.LoggedUser;
import com.resellerapp.service.OfferService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ConditionRepository conditionRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    public OfferServiceImpl(
            OfferRepository offerRepository,
            ConditionRepository conditionRepository,
            LoggedUser loggedUser,
            UserRepository userRepository
    ) {
        this.offerRepository = offerRepository;
        this.conditionRepository = conditionRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public OfferHomeDTO getOffersForHomePage() {
        List<Offer> offers = offerRepository.findAll();
        List<MyOfferDTO> myOffers = new ArrayList<>();
        List<BoughtOffersDTO> boughtOffers = new ArrayList<>();
        List<OtherOffersDTO> otherOffers = new ArrayList<>();

        for (Offer offer : offers) {
            String loggedUserName = loggedUser.username();

            if (offer.getBoughtBy() == null && offer.getCreatedBy().getUsername().equals(loggedUserName)) {
                myOffers.add(new MyOfferDTO(offer));
            } else if (offer.getBoughtBy() != null && offer.getBoughtBy().getUsername().equals(loggedUserName)) {
                boughtOffers.add(new BoughtOffersDTO(offer));
            } else if (offer.getBoughtBy() == null) {
                otherOffers.add(new OtherOffersDTO(offer));
            }
        }

        return new OfferHomeDTO(myOffers, boughtOffers, otherOffers);
    }

    @Override
    public boolean create(OfferCreateBindingModel offerCreateBindingModel) {
       Condition condition = conditionRepository.findByName(offerCreateBindingModel.getCondition());
       Optional<User> userOptional = userRepository.findByUsername(loggedUser.username());

       if(condition != null && userOptional.isPresent()) {
            Offer offer = new Offer(offerCreateBindingModel, condition, userOptional.get());

            offerRepository.save(offer);

            return true;
       }

       return false;
    }

    @Override
    public void buy(UUID id) {
        Optional<Offer> offerOptional = offerRepository.findById(id);

        if(offerOptional.isPresent()) {
            Offer offer = offerOptional.get();
            Optional<User> userOptional = userRepository.findByUsername(loggedUser.username());

            userOptional.ifPresent(offer::setBoughtBy);

            offerRepository.save(offer);
        }
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        offerRepository.deleteById(id);
    }
}
