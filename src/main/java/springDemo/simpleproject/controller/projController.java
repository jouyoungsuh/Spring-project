package springDemo.simpleproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class projController {

    // Note that this maps into the test-template.html in the templates folder under resources folder.
    // This gets the attribute from this controller.
    @GetMapping("test")
    public String testMethod(Model model){
        model.addAttribute("data", "This String is the value of data in controller");
        return "test-template";
    }

    // Note that this maps into the template-mvc-template.html in the templates folder under resources folder.
    // This gets the attribute from the RequestParam, such as: http://localhost:8080/ex-mvc?AttText=AttributeTextExample
    @GetMapping("ex-mvc")
    public String exmvcMethod(@RequestParam("AttText") String AttText, Model model) {
        model.addAttribute("AttText", AttText);
        return "ex-mvc-template";
    }

    // The sniplet below set required as false. This means it is not essential to pass the AttText
    /**
     @GetMapping("ex-mvc")
     public String exmvcMethod(@RequestParam(value = "AttText", required = false) String AttText, Model model) {
     model.addAttribute("AttText", AttText);
     return "ex-mvc-template";
     }
     */


}
