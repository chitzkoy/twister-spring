package ru.qatools.school.twister.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dima on 14.12.14.
 */
@Controller
public class RootPageController {

    @RequestMapping(value="/welcome", method = RequestMethod.GET)
    public String printHello( ModelMap modelMap ) {
        modelMap.addAttribute("message", "Hello, Spring!");

        return "welcome";
    }

}
