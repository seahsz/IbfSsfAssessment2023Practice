package ssf.ibfAssessment2023.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ssf.ibfAssessment2023.models.PizzaOrder;

import static ssf.ibfAssessment2023.common.Constants.*;

@Controller
@RequestMapping
public class LandingPageController {

    @GetMapping(path = {"/", "index.html"})
    public String getIndex(Model model) {

        model.addAttribute(TH_PIZZA_ORDER, new PizzaOrder());

        return "index";
    }
    
}
