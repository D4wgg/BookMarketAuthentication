package ru.dawgg.bookmarket.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Форма логина")
public class LoginDto {

    @NotNull
    @NotEmpty(message = "Login can not be empty and null")
    @Schema(name = "Логин пользователя")
    private String login;

    @NotNull
    @NotEmpty(message = "Password can not be empty and null")
    @Schema(name = "Пароль пользователя")
    private String password;
}
