package go.example.personalproject.web.user;

import go.example.personalproject.domain.user.User;
import go.example.personalproject.domain.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    // SignUp Page
    @GetMapping("/add")
    public String SignUp(@ModelAttribute("user") User user) {
        return "user/signup";
    }

    @PostMapping("/add")
    public String updateUser(@Validated @ModelAttribute User user, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            log.info("Error:add = {} ", result.getFieldError());
            return "/user/signup";
        }

        userRepository.save(user);
        return "redirect:/";
    }

//    @GetMapping("/login")
//    public String login(@ModelAttribute("user") User user, BindingResult result) {
//        return "/user/login";
//    }
//
//    @PostMapping("/login")
//    public String UpdateUser(@ModelAttribute("user)") User user, BindingResult result) {
//        if(result.hasErrors()) {
//            return "/user/login";}
//
//        return "redirect:/";
//    }
}
