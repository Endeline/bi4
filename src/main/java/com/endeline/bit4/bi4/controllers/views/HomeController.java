package com.endeline.bit4.bi4.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class is for control page request.
 */
@Controller
public class HomeController {

    /**
     * Function return home page to browser.
     * @return hello.html
     */
    @RequestMapping(value = "/")
    public String homeView() {
        return "hello";
    }
}
