package ru.dawgg.bookmarket.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Токен")
public class TokenDto {

    @Schema(name = "Значение токена")
    private String value;
}
