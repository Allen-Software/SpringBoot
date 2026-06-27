package com.taskflow.api.service;

import com.taskflow.api.dto.request.TaskCreateRequest;
import com.taskflow.api.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(TaskCreateRequest request);
    List<TaskResponse> getAllTasks();
    TaskResponse getTaskById(Long id);
    TaskResponse updateTask(Long id, TaskCreateRequest request);
    void deleteTask(Long id);
}