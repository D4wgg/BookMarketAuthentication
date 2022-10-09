package ru.dawgg.bookmarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Форма логина")
public class LoginDto {

    @Email(message = "email not valid")
    @JsonProperty(value = "Почта")
    private String email;

    @NotNull
    @NotEmpty(message = "Password can not be empty and null")
    @JsonProperty(value = "Пароль")
    private String password;
}
