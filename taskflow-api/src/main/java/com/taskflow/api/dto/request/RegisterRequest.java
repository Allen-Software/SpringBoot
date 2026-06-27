package com.taskflow.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "使用者註冊請求資料")
public class RegisterRequest {

    @Schema(description = "使用者信箱 (作為登入帳號)", example = "user@example.com")
    @NotBlank(message = "信箱不能為空")
    @Email(message = "信箱格式不正確")
    private String email;

    @Schema(description = "使用者密碼 (長度需介於 6 到 20 字元)", example = "Password123!")
    @NotBlank(message = "密碼不能為空")
    @Size(min = 6, max = 20, message = "密碼長度必須介於 6 到 20 個字元之間")
    private String password;

    @Schema(description = "使用者姓名", example = "王小明")
    @NotBlank(message = "姓名不能為空")
    @Size(max = 50, message = "姓名長度不能超過 50 個字元")
    private String name;
}