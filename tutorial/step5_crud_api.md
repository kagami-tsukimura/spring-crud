# Step 5: CRUD API を作成する（10分）

> **前のステップ**: [Step 4: Service 層を作成する](./step4_service.md)
>
> **背景**: REST API では GET（読み取り）だけでなく POST/PUT/DELETE による作成・更新・削除も必要になる。各 HTTP メソッドに対応したアノテーションと、JSON ↔ Java の自動変換、適切な HTTP ステータスコード返却の3点セットが実務の基本となる。
>
> **このステップでできること**: タスクの作成・取得・更新・削除（CRUD）を curl で実際に叩いて確認できる REST API を完成させる。

---

## 🎯 学ぶこと

- `@PostMapping`, `@PutMapping`, `@DeleteMapping`
- `@RequestBody` — JSON ボディをオブジェクトにマッピング
- `ResponseEntity` — HTTP ステータスコードの制御

## 5-1. TaskController の作成

- **背景**: データの取得だけでなく、作成（POST）、更新（PUT）、削除（DELETE）といった一連の操作（CRUD）を標準的な HTTP メソッドに則って提供することで、使いやすく保守性の高い API になります。
- **ファイル役割**: `TaskController.java` は、タスク操作に関するリクエストを適切なサービス（`TaskService`）に振り分け、処理結果を HTTP ステータスコードとともにクライアントへ返す役割を担います。

`src/main/java/com/example/demo/controller/TaskController.java` を新規作成:

```java
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
```

## 5-2. 動作確認（curl）

アプリを起動して、以下を順番に実行:

```bash
./mvnw spring-boot:run
```

```bash
# 1. タスクを作成
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title": "Spring Bootを学ぶ", "completed": false}'

# レスポンス: {"id":1,"title":"Spring Bootを学ぶ","completed":false}

# 2. 全件取得
curl http://localhost:8080/api/tasks

# 3. もう1件作成
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title": "CRUD APIを作る", "completed": false}'

# 4. 1件取得
curl http://localhost:8080/api/tasks/1

# 5. 更新（完了に変更）
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"title": "Spring Bootを学ぶ", "completed": true}'

# 6. 削除
curl -X DELETE http://localhost:8080/api/tasks/2

# 7. 全件取得して確認（id=1 のみ残り、completed=true に更新済み）
curl http://localhost:8080/api/tasks
```

## 💡 ポイント

- `@RequestBody` で Jackson（JSON ライブラリ）が自動で JSON ↔ Java 変換
- `ResponseEntity` で HTTP ステータスコードを明示的に制御できる
- レイヤードアーキテクチャ: **Controller → Service → Repository → DB** の責務分離

---

> **次のステップ**: [Step 6: H2 Console で DB を確認する](./step6_h2_console.md)
