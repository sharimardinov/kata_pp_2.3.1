package app.web.controller;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("allUsers", userService.getUsers());
        return "index";
    }

    @GetMapping(value = "/get_user/{id}")
    public String getUserById(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "updateUser";
    }

    @GetMapping(value = "/get_user_form")
    public String getUserForm(ModelMap model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @GetMapping(value = "/delete_user/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @PostMapping(value = "/add_user")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @PostMapping(value = "/update_user")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}
