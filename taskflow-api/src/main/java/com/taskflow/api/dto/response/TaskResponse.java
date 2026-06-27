package com.taskflow.api.dto.response;

import com.taskflow.api.entity.Task;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private Task.TaskStatus status;
    private Task.TaskPriority priority;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 這裡不回傳整個 User 實體，避免無窮迴圈或敏感資料外洩，僅回傳 user_id 即可
    private Long userId;
}