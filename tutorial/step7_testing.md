# Step 7: テストを書く（5分）

> **前のステップ**: [Step 6: H2 Console で DB を確認する](./step6_h2_console.md)
>
> **背景**: 手動 curl での確認はリグレッション（デグレ）を防げない。Spring は `@SpringBootTest` + `MockMvc` によって HTTP サーバーを起動せずに Controller 層まで含めた統合テストを書く仕組みを標準提供しており、DI のおかげでテストが書きやすい設計になっている。
>
> **このステップでできること**: CRUD API の各操作（作成・更新・削除）が正しく動くことを自動テストで検証し、`./mvnw test` で全テスト GREEN を確認する。

---

## 🎯 学ぶこと

- `@SpringBootTest` — 統合テスト
- `MockMvc` — HTTPリクエストをシミュレート
- Spring のテストサポートの充実さ

## 7-1. TaskControllerTest の作成

- **背景**: 手動で API を叩いて動作確認すると、機能が増えるたびに確認の手間が雪だるま式に増え、以前作った機能が壊れていないか（退行/リグレッション）を保証するのが難しくなります。
- **ファイル役割**: `TaskControllerTest.java` は、Spring Context を起動して HTTP リクエストを擬似的に送信し、期待通りのレスポンスやステータスコードが返ってくるかを「自動で」検証する役割を担います。

`src/test/java/com/example/demo/controller/TaskControllerTest.java` を新規作成:

```java
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
```

## 7-2. テスト実行

```bash
./mvnw test
```

全テストが通れば ✅ 成功！

## 💡 ポイント

- `@SpringBootTest` + `@AutoConfigureMockMvc` の組み合わせで、サーバー起動不要の統合テストが書ける
- `MockMvc` は HTTP request/response をシミュレートし、レスポンスの JSON を `jsonPath` で検証できる
- テストメソッド名を日本語にすることも可能（JUnit 5）

---

> **完了！** 技術スタックの詳細は [比較表](./comparison.md) を参照
