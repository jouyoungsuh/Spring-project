package springDemo.simpleproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProjController {

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

    // ResponseBody does not use viewResolver. This directly returns the desired value to the BODY of HTTP
    // This works as: http://localhost:8080/ex-responsebody?AttText=resbodyExample
    // But rather than this example, method below is used more often
    @GetMapping("ex-responsebody")
    @ResponseBody
    public String exResponseBodyMethod(@RequestParam("AttText") String text) {
        return "This is an example of @Responsebody, which is in the controller. It will now return what AttText in the right: " + text;
    }


    // This method returns in JSON format. This means if you return the object, then it becomes JSON.
    // It  works as: http://localhost:8080/ex-responsebody-api?key=thisIsKey
    // In the webpage, it will return: {"key":"thisIsKey","value":null}
    @GetMapping("ex-responsebody-api")
    @ResponseBody
    public Test exAPImethod1(@RequestParam("key") String key) {
        Test test = new Test();
        test.setKey(key);
        return test;
    }

    static class Test {
        private String key;
        private String value;
        public String getKey() {
            return key;
        }
        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }
}
