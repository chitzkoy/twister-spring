package ru.qatools.school.twister.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dima on 14.12.14.
 */
@Controller
public class WebController {

    @RequestMapping("/welcome")
    public ModelAndView welcome(@RequestParam String name ) {
        return new ModelAndView( "welcome", "message", "Hello, " + name + "!");
    }

}
