package com.taskflow.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "使用者登入請求資料")
public class LoginRequest {

    @Schema(description = "登入信箱", example = "user@example.com")
    @NotBlank(message = "信箱不能為空")
    @Email(message = "信箱格式不正確")
    private String email;

    @Schema(description = "登入密碼", example = "Password123!")
    @NotBlank(message = "密碼不能為空")
    private String password;
}