package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// 共通のパスプレフィックス
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    // Service を DI で注入
    private final TaskService taskService;

    // GET /api/tasks → 全件取得
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    // GET /api/tasks/{id} → 1件取得
    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.findById(id);
    }

    // POST /api/tasks → 新規作成
    // @RequestBody: リクエストボディの JSON を Task オブジェクトに自動変換
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task created = taskService.create(task);
        // 201 Created ステータスで返却
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/tasks/{id} → 更新
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.update(id, task);
    }

    // DELETE /api/tasks/{id} → 削除
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        // 204 No Content
        return ResponseEntity.noContent().build();
    }
}