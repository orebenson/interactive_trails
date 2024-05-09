package com.Team4.SmartTowns.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
    @GetMapping("/403")
    public ModelAndView error403() {
        return new ModelAndView("error/403");
    }
}
