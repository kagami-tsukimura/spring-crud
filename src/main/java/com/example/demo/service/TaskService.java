package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service: このクラスをサービス層の Bean として登録
@Service
// @RequiredArgsConstructor: final フィールドを引数に取るコンストラクタを自動生成
// → コンストラクタインジェクション（推奨パターン）が実現される
@RequiredArgsConstructor
public class TaskService {

    // Spring が TaskRepository の実装を自動で注入（DI）
    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        // orElseThrow: 見つからない場合は例外（Java Optional の活用）
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: id=" + id));
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Long id, Task taskDetails) {
        Task task = findById(id);
        task.setTitle(taskDetails.getTitle());
        task.setCompleted(taskDetails.isCompleted());
        // save() は ID があれば UPDATE、なければ INSERT を実行
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}