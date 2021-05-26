package springDemo.simpleproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springDemo.simpleproject.domain.User;
import springDemo.simpleproject.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/new")
    public String createForm(){
        return "users/createNewUserForm-template";
    }

    @PostMapping(value = "/users/new")
    public String createUser(UserForm form) {
        User member = new User();
        member.setName(form.getName());
        userService.signUp(member);
        return "redirect:/";
    }

    @GetMapping(value = "/users")
    public String list(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "users/userList-template";
    }

}
