package go.example.personalproject.web.login;

import go.example.personalproject.domain.login.LoginService;
import go.example.personalproject.domain.user.User;
import go.example.personalproject.web.SessionConst;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm form) {
        return "/user/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form, BindingResult result
    , HttpServletResponse response , HttpServletRequest request) {
        if (result.hasErrors()) {
            result.reject("loginFail", "아이디,비밀번호를 확인해주세요");
            return "/user/login";
        }

        User loginUser = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login {}", loginUser);

        if (loginUser == null) {
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "user/login";
        }

        // if session was not found - > create new session (true) is default
        HttpSession session =  request.getSession();
        // Session save User's Properties
        session.setAttribute(SessionConst.LOGIN_USER,loginUser);


        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
//        exPiredCookie(response, "userId");
        return "redirect:/";
    }

    public void exPiredCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }


}
