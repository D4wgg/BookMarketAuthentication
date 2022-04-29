package ru.dawgg.bookmarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(value = "Логин")
    private String login;

    @NotNull
    @NotEmpty(message = "Password can not be empty and null")
    @JsonProperty(value = "Пароль")
    private String password;
}
