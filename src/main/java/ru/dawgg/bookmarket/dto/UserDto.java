package ru.dawgg.bookmarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(value = "Логин")
    private String login;

    @NotEmpty(message = "password can not be empty")
    @JsonProperty(value = "Пароль")
    private String password;

    @NotEmpty(message = "parents must name their children during first month according to the constitution of RF")
    @JsonProperty(value = "Имя пользователя")
    private String name;

    @NotEmpty(message = "You have to provide us with the information")
    @JsonProperty(value = "Фамилия пользователя")
    private String surname;
}
