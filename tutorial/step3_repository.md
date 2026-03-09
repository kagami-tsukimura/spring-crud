# Step 3: Repository 層を作成する（5分）

> **前のステップ**: [Step 2: Entity を作成する](./step2_entity.md)
>
> **背景**: Entity だけではデータをどう操作するかが決まらない。従来は DB アクセスのたびに SQL + コネクション管理のコードを書く必要があった。Spring Data JPA の Repository はその定形作業を完全に自動化する仕組みを提供する。
>
> **このステップでできること**: インターフェースを1つ定義するだけで `findAll()` / `save()` / `deleteById()` などの DB 操作メソッドが自動的に使えるようになる。

---

## 🎯 学ぶこと

- `JpaRepository` — CRUD メソッドが自動生成される仕組み
- **インターフェースだけで実装不要** という Spring Data の強力さ

## 3-1. TaskRepository の作成

- **背景**: データベースのデータを検索したり、新しく保存したりする際、SQL を手書きするのは時間がかかり、ミスも発生しやすくなります。
- **ファイル役割**: `TaskRepository.java` は、データベースへの「データアクセス」を抽象化する役割を担います。インターフェースを定義するだけで、Spring Data JPA が自動的に SQL を生成・実行する仕組みを裏側で作成してくれます。

`src/main/java/com/example/demo/repository/TaskRepository.java` を新規作成:

```java
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
```

## 💡 ポイント

- **実装クラスが不要**: インターフェースを定義するだけで Spring が実装を自動生成
- **Query Methods**: `findByXxx` のようにメソッド名を書くだけで SQL が自動生成される
- これが Spring Data JPA の最大の特徴。他フレームワークでは DAO 層の実装を手書きする必要がある

---

> **次のステップ**: [Step 4: Service 層を作成する](./step4_service.md)
