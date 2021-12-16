package com.plamena.findahomeweb.controllers;

import com.plamena.findahomeweb.api.EstatesApi;
import com.plamena.findahomeweb.requests.estates.EstateCreateRequest;
import com.plamena.findahomeweb.requests.estates.EstateEditRequest;
import com.plamena.findahomeweb.responses.estates.EstateGetResponse;
import com.plamena.findahomeweb.responses.estates.EstatesCollection;
import com.plamena.findahomeweb.utils.AppConstants;
import com.plamena.findahomeweb.utils.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class EstatesController {

  @Autowired
  private EstatesApi estatesApi;

  @GetMapping("/estates")
  public String getEstates(@RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "limit", defaultValue = "6") Integer limit, HttpServletRequest request, Model model) {

    String otherParams = "";

    if (limit == null || limit <= 0) {
      limit = AppConstants.MAX_ITEMS_PER_PAGE;
//      limit = 1;
    } else {
      otherParams = "&limit=" + limit;
    }

    EstatesCollection estates = estatesApi.getEstates(page, limit, request);
    model.addAttribute("collection", estates);
    model.addAttribute("maxPagesPerView", 5);

    model.addAttribute("page", page);
    model.addAttribute("limit", limit);
    model.addAttribute("otherParams", otherParams);
    model.addAttribute("menu", "estates");

    return "estates/estates";
  }

  @GetMapping("/estate")
  public String getEstate(@RequestParam("id") String estateId, HttpServletRequest request, Model model) {

    EstateGetResponse estate = estatesApi.getEstate(estateId, request);

    model.addAttribute("estate", estate);
    model.addAttribute("menu", "estates");

    return "estates/estate";
  }

  @GetMapping("/testtt")
  public String testMethod(Model model) {

    Name name = new Name();
    name.setFirstName("John");
    name.setLastName("Doe");

    model.addAttribute("name", name);

    return "test";
  }

  @GetMapping("/estates/create")
  public String prepareCreateEstate(Model model) {

    model.addAttribute("ecr", new EstateCreateRequest());

    return "estates/create";
  }

  @PostMapping("/estates/create")
  public String createEstate(@ModelAttribute("ecr") @Valid EstateCreateRequest ecr, BindingResult bindingResult, HttpServletRequest request, Model model) {

    if (bindingResult.hasErrors()) {
      return "estates/create";
    }

    estatesApi.createEstate(ecr, request);

    return "redirect:/estates";
  }

  @GetMapping("/estates/edit")
  public String prepareEditEstate(@RequestParam("id") String estateId, HttpServletRequest request, Model model) {

    EstateGetResponse estate = estatesApi.getEstate(estateId, request);
    EstateEditRequest eer = new EstateEditRequest();
    eer.setEstateId(estate.getEstateId());
    eer.setName(estate.getName());
    eer.setCountry(estate.getCountry());
    eer.setCity(estate.getCity());
    eer.setNeighborhood(estate.getNeighborhood());
    eer.setPrice(estate.getPrice());
    eer.setRooms(estate.getRooms());
    eer.setFloor(estate.getFloor());
    eer.setArea(estate.getArea());
    eer.setAddress(estate.getAddress());
    eer.setIsRent(estate.getIsRent());

    model.addAttribute("eer", eer);

    return "estates/edit";
  }

  @PostMapping("/estates/edit")
  public String editEstate(@ModelAttribute("eer") @Valid EstateEditRequest eer, BindingResult bindingResult, HttpServletRequest request, Model model) {

    if (bindingResult.hasErrors()) {
      return "estates/edit";
    }

    estatesApi.editEstate(eer, request);

    return "redirect:/estate?id=" + eer.getEstateId();
  }

  @PostMapping("/estates/delete")
  public String deleteEstate(@RequestParam("id") String estateId, HttpServletRequest request, Model model) {

    estatesApi.deleteEstate(estateId, request);

    return "redirect:/estates";
  }
}
