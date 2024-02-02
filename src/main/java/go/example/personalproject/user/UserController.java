package go.example.personalproject.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.Binding;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/add")
    public String SignUp(@ModelAttribute("user") User user) {
        return "user/signup";
    }

    @PostMapping("/add")
    public String updateUser(@Validated @ModelAttribute User user, Binding result) {
        return "user/signup";
    }


}
