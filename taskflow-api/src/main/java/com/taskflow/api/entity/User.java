package com.taskflow.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String name;

    // 定義與 Task 的一對多關聯
    // mappedBy = "user" 指向 Task 實體中名為 user 的屬性，表示 User 是非擁有方
    // cascade = CascadeType.ALL: 當 User 被刪除時，他底下的所有 Tasks 也會跟著被刪除
    // orphanRemoval = true: 若從集合中移除 Task，該 Task 在資料庫中也會被刪除
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default // 讓 Builder 模式初始化為空列表而非 null
    private List<Task> tasks = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}