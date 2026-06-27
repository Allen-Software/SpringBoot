package com.taskflow.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "認證成功後的回傳資料")
public class AuthResponse {

    @Schema(description = "JWT Token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "使用者 ID", example = "1")
    private Long userId;

    @Schema(description = "使用者姓名", example = "王小明")
    private String name;

    @Schema(description = "使用者信箱", example = "user@example.com")
    private String email;
}