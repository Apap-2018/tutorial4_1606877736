package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class DealerController {
    @Autowired
    private DealerService dealerService;

    @Autowired
    private CarService carService;

    @RequestMapping("/")
    private String home() {
        return "home";
    }

    @RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
    private String add(Model model){
        model.addAttribute("dealer", new DealerModel());
        return "addDealer";
    }

    @RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
    private String addDealerSubmit(@ModelAttribute DealerModel dealer){
        dealerService.addDealer(dealer);
        return "add";
    }

    @RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.GET)
    private String update(@PathVariable(value = "dealerId") Long dealerId, Model model){
        DealerModel archive = dealerService.getDealerDetailById(dealerId).get();
        model.addAttribute("newDealer", new DealerModel());
        model.addAttribute("dealer", archive);
        return "updateDealer";
    }

    @RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.POST)
    private String updateSubmit(@PathVariable(value = "dealerId") Long dealerId, @ModelAttribute DealerModel newDealer){
        dealerService.updateDealer(dealerId,newDealer);
        return "update";
    }

    @RequestMapping(value = "/dealer/delete/{dealerId}", method = RequestMethod.GET)
    private String delete(@PathVariable(value = "dealerId") Long dealerId, Model model){
        DealerModel archive = dealerService.getDealerDetailById(dealerId).get();
        dealerService.deleteDealer(archive);
        return "delete";
    }

    @RequestMapping(value = "/dealer/view", method = RequestMethod.GET)
    private String view(@RequestParam(value = "dealerId", required = true) Long dealerId, Model model){
        DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
        model.addAttribute("alamat", dealer.getAlamat());
        model.addAttribute("noTelp", dealer.getNoTelp());
        List<CarModel> archive = dealer.getListCar();
        Collections.sort(archive);
        model.addAttribute("listcar", archive);
        model.addAttribute("dealerId", dealerId);
        return "view-dealer";
    }

    @RequestMapping(value = "/dealer/viewall", method = RequestMethod.GET)
    private String viewAll(Model model){
        List<DealerModel> listDealer = dealerService.getAllDealer();
        model.addAttribute("listdealer", listDealer);
        return "view-all-dealer";
    }

}
