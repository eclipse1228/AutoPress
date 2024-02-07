package go.example.personalproject.web.Home;

import go.example.personalproject.domain.user.User;
import go.example.personalproject.domain.user.UserRepository;
import go.example.personalproject.web.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {

    UserRepository userRepository = new UserRepository();

    //    @GetMapping("/")
    public String main(@ModelAttribute User user, Model model) {
        return "user/home";
    }

    @GetMapping("/")
    public String homeLoginv3(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            return "/user/home";
        }

        User loginUser = (User) session.getAttribute(SessionConst.LOGIN_USER);

        if (loginUser == null) {
            return "home";
        }

        model.addAttribute("user", loginUser);
        return "user/loginHome";

//        if(userId == null) {
//            return "/user/home";
//        }
//
//        //Login
//        User user = userRepository.findById(userId);
//
//        if (user == null) {
//            return "/user/home";
//        }
//
//        model.addAttribute("user", user);
//        return "/user/loginHome";
    }
}

