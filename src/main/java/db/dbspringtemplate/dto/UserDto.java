package db.dbspringtemplate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import db.dbspringtemplate.model.User;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private LocalDate birthdate;

    private String password;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .birthdate(user.getBirthdate())
                .password(user.getPassword())
                .build();
    }

    public static List<UserDto> fromEntity(List<User> users) {
        return users.stream().map(UserDto::fromEntity).collect(Collectors.toList());
    }
}
