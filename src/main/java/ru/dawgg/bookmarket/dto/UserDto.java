package ru.dawgg.bookmarket.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Пользователь")
public class UserDto {
    @NotEmpty(message = "login can not be empty")
    @Schema(name = "Логин пользователя")
    private String login;

    @NotEmpty(message = "password can not be empty")
    @Schema(name = "Логин пользователя")
    private String password;

    @NotEmpty(message = "parents must name their children during first month according to the constitution of RF")
    @Schema(name = "Логин пользователя")
    private String name;

    @NotEmpty(message = "orphan? feel so sorry")
    @Schema(name = "Логин пользователя")
    private String surname;
}
