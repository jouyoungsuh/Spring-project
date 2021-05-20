package springDemo.simpleproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class projController {

    @GetMapping("test")
    public String hello(Model model){
        model.addAttribute("data", "This String is value of the data");
        return "test";

    }
}
