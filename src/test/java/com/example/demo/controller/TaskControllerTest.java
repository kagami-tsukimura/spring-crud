package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// アプリケーション全体を起動する統合テスト
@SpringBootTest
// MockMvc を自動構成（実際の HTTP サーバーを起動せずにテスト）
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // 各テスト前に DB をクリア
        taskRepository.deleteAll();
    }

    @Test
    void タスクを作成して取得できる() throws Exception {
        Task task = new Task(null, "テストタスク", false);

        // POST でタスクを作成
        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("テストタスク"))
                .andExpect(jsonPath("$.completed").value(false));

        // GET で全件取得して確認
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("テストタスク"));
    }

    @Test
    void タスクを更新できる() throws Exception {
        // 事前にタスクを保存
        Task saved = taskRepository.save(new Task(null, "更新前", false));

        Task updated = new Task(null, "更新後", true);

        mockMvc.perform(put("/api/tasks/" + saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("更新後"))
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test
    void タスクを削除できる() throws Exception {
        Task saved = taskRepository.save(new Task(null, "削除対象", false));

        mockMvc.perform(delete("/api/tasks/" + saved.getId()))
                .andExpect(status().isNoContent());

        // 全件取得で0件を確認
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}