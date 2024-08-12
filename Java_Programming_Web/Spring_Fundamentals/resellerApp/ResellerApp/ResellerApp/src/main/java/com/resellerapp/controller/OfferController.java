package com.resellerapp.controller;

import com.resellerapp.model.OfferCreateBindingModel;
import com.resellerapp.service.UserService;
import com.resellerapp.service.OfferService;
import com.resellerapp.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final UserService userService;


    public OfferController(OfferService offerService, UserService userService) {
        this.offerService = offerService;
        this.userService = userService;
    }

    @ModelAttribute("offerData")
    public OfferCreateBindingModel offerCreateData() {
        return new OfferCreateBindingModel();
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return SecurityUtils
                .checkAccess(userService, "login", true)
                .orElseGet(() -> new ModelAndView("offer-add"));
    }

    @PostMapping("/create")
    public ModelAndView create(
            @Valid  @ModelAttribute("offerData") OfferCreateBindingModel offerCreateBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        return SecurityUtils
                .checkAccess(userService, "login", true)
                .orElseGet(() -> {
                    if (bindingResult.hasErrors()) {
                        redirectAttributes.addFlashAttribute("offerData", offerCreateBindingModel);
                        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerData", bindingResult);

                        return new ModelAndView("redirect:/offers/create");
                    }

                    boolean isCreated = offerService.create(offerCreateBindingModel);
                    String view = isCreated ? "redirect:/home" : "redirect:/offers/create";
                    return new ModelAndView(view);
                });
    }

    @PostMapping("/buy/{id}")
    public ModelAndView buyOffer(@PathVariable("id") UUID id) {
        return SecurityUtils
                .checkAccess(userService, "login", true)
                .orElseGet(() ->{
                    offerService.buy(id);
                    return  new ModelAndView("redirect:/home");
                });
    }

    @GetMapping("/remove/{id}")
    public String delete(@PathVariable("id") UUID id) {
        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        offerService.delete(id);
        return "redirect:/home";
    }

}
