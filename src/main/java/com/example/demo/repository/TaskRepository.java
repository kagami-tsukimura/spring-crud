package com.example.demo.repository;

import com.example.demo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository<エンティティ型, 主キー型> を extends するだけで
// save(), findById(), findAll(), deleteById() 等が自動実装される
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // メソッド名から自動でクエリを生成（Spring Data Query Methods）
    // → SELECT * FROM task WHERE completed = ?
    java.util.List<Task> findByCompleted(boolean completed);
}