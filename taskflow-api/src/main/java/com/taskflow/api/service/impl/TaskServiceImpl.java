package com.taskflow.api.service.impl;

import com.taskflow.api.dto.request.TaskCreateRequest;
import com.taskflow.api.dto.response.TaskResponse;
import com.taskflow.api.entity.Task;
import com.taskflow.api.entity.User;
import com.taskflow.api.exception.ResourceNotFoundException;
import com.taskflow.api.repository.TaskRepository;
import com.taskflow.api.repository.UserRepository;
import com.taskflow.api.service.TaskService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    // 模擬取得當前登入使用者的 ID (在接上 Spring Security JWT 後，這會從 SecurityContext 中動態取得)
    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new com.taskflow.api.exception.ResourceNotFoundException("找不到當前登入的使用者"));
    }

    @Override
    @Transactional
    public TaskResponse createTask(TaskCreateRequest request) {
        User user = getCurrentUser();

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .priority(request.getPriority())
                .dueDate(request.getDueDate())
                .user(user)
                .build();

        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        User currentUser = getCurrentUser();
        List<Task> tasks = taskRepository.findAllByUserId(currentUser.getId());

        return tasks.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = getTaskEntityByIdAndCheckOwner(id);
        return mapToResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse updateTask(Long id, TaskCreateRequest request) {
        Task task = getTaskEntityByIdAndCheckOwner(id);

        // 更新欄位
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());

        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        Task task = getTaskEntityByIdAndCheckOwner(id);
        taskRepository.delete(task);
    }

    // --- 共用私有方法 ---

    // 輔助方法：確保任務存在，且屬於當前使用者
    private Task getTaskEntityByIdAndCheckOwner(Long taskId) {
        User currentUser = getCurrentUser();
        return taskRepository.findByIdAndUserId(taskId, currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("任務不存在或您沒有權限存取"));
    }

    // 輔助方法：將 Task Entity 轉換為 TaskResponse DTO
    private TaskResponse mapToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .userId(task.getUser().getId())
                .build();
    }
}