package go.example.personalproject.domain.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class User {
    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
}
