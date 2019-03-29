package ru.burmistrov.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.enumerated.Role;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    @Nullable
    private String id;

    @Nullable
    private String password;

    @Nullable
    private String firstName;

    @Nullable
    private String middleName;

    @Nullable
    private String lastName;

    @Nullable
    private String login;

    @Nullable
    private String email;

    @Nullable
    private Role role;
}
