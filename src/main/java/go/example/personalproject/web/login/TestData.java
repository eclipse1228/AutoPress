package go.example.personalproject.web.login;

import go.example.personalproject.domain.user.User;
import go.example.personalproject.domain.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestData {
    private final UserRepository repository;

    @PostConstruct
    public void init() {
        User user = new User();
        user.setName("TestUser");
        user.setPassword("1234");
        user.setLoginId("test");
        repository.save(user);
    }
}
