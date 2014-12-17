package ru.qatools.school.twister.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dima on 14.12.14.
 */
@Controller
public class WebController {

    @RequestMapping("/welcome")
    public String printHello( Model model ) {
        System.out.println( "Hello, Spring!" );
        model.addAttribute("message", "Hello, Spring!");

        return "welcome";
    }

}
