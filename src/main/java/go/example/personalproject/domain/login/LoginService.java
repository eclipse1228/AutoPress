package go.example.personalproject.domain.login;

import go.example.personalproject.domain.user.User;
import go.example.personalproject.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    UserRepository userRepository = new UserRepository();

    public User login(String loginId, String password  ) {
        return userRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

}
