package com.taskflow.api.dto.request;

import com.taskflow.api.entity.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskCreateRequest {

    @NotBlank(message = "任務標題不能為空")
    @Size(max = 100, message = "標題長度不能超過 100 字元")
    private String title;

    private String description;

    @NotNull(message = "任務狀態不能為空")
    private Task.TaskStatus status;

    @NotNull(message = "任務優先級不能為空")
    private Task.TaskPriority priority;

    private LocalDateTime dueDate;
}