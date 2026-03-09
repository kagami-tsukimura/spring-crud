# Step 4: Service 層を作成する（10分）

> **前のステップ**: [Step 3: Repository 層を作成する](./step3_repository.md)
>
> **背景**: Controller が Repository を直接呼ぶと、HTTP の関心事（リクエスト/レスポンス）とビジネスロジックが混在して保守しにくくなる。Service 層を挟むことで責務を分離し、DI（依存性注入）によって各層の結合度を下げる。
>
> **このステップでできること**: `@Service` + コンストラクタインジェクションで Spring の IoC コンテナと DI の動きを体験し、Controller → Service → Repository の3層構造を完成させる。

---

## 🎯 学ぶこと

- `@Service` — ビジネスロジック層の宣言
- **DI（Dependency Injection）** — Spring の最も重要な特徴
- コンストラクタインジェクション

## 4-1. TaskService の作成

- **背景**: Controller が直接 Repository を操作すると、「データの保存方法」に依存したコードになり、複雑な計算やチェック（ビジネスロジック）が必要になった際にコードが肥大化します。
- **ファイル役割**: `TaskService.java` は、アプリケーションの「ビジネスロジック」を集中管理する役割を担います。データの整合性チェックや、複数の Repository を組み合わせた複雑な処理をここで行います。

`src/main/java/com/example/demo/service/TaskService.java` を新規作成:

```java
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
```

## 💡 ポイント: DI（Dependency Injection）

```
┌──────────────┐     DI      ┌──────────────────┐
│  Controller  │ ──────────→ │     Service       │
│              │             │ (@Service)        │
└──────────────┘             └────────┬─────────┘
                                      │ DI
                                      ▼
                             ┌──────────────────┐
                             │   Repository      │
                             │ (JpaRepository)   │
                             └──────────────────┘
```

- **IoC コンテナ**: Spring がオブジェクトのライフサイクルと依存関係を管理
- **コンストラクタインジェクション**: `@RequiredArgsConstructor` + `final` フィールドで実現。テスト時にモックを注入しやすい
- `new TaskRepository()` のようにインスタンスを自分で作る必要がない

---

> **次のステップ**: [Step 5: CRUD API を作成する](./step5_crud_api.md)
