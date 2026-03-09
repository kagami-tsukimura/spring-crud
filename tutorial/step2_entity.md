# Step 2: Entity を作成する（10分）

> **前のステップ**: [Step 1: Hello World REST API](./step1_hello_rest_api.md)
>
> **背景**: API が返すデータを DB に永続化するには、Java クラスとテーブルを対応付ける必要がある。JPA はそのマッピングをアノテーションで宣言的に行い、Lombok はその際のボイラープレートを自動生成で排除する。
>
> **このステップでできること**: `Task` クラスを `@Entity` として定義し、H2 インメモリ DB にテーブルが自動生成される状態を作る。

---

## 🎯 学ぶこと

- `@Entity` — JPA エンティティの宣言
- Lombok による ボイラープレートコードの削除
- Spring Data JPA の自動テーブル生成

## 2-1. Task エンティティの作成

- **背景**: アプリケーションで扱うデータ（今回はタスク）をメモリ上の「モノ（オブジェクト）」としてだけでなく、データベースに永続化して保存できる形式にする必要があります。
- **ファイル役割**: `Task.java` は、Java クラスと DB のテーブルを 1 対 1 でマッピングする役割を担います。`@Entity` を付けることで、JPA が自動的にテーブル操作の対象として認識します。

`src/main/java/com/example/demo/entity/Task.java` を新規作成:

```java
package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity: このクラスを DB テーブルにマッピング
@Entity
// @Data: getter/setter/toString/equals/hashCode を自動生成（Lombok）
@Data
// JPA はデフォルトコンストラクタが必須
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    // @Id: 主キー / @GeneratedValue: 自動採番
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private boolean completed;
}
```

## 2-2. アプリケーション設定の編集

- **背景**: Spring Boot は多くの設定を自動で行いますが、DB の接続先や各機能の動作をカスタマイズする必要がある場合に、この設定ファイルを使用します。
- **ファイル役割**: `application.properties` は、アプリケーション全体の動作パラメータ（ポート番号、DB 接続情報、ログ設定など）を一括管理する中心的な設定ファイルです。

`src/main/resources/application.properties` を編集:

```properties
spring.application.name=demo

# H2 Database settings
spring.datasource.url=jdbc:h2:mem:taskdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA settings
# DDL auto: create-drop = 起動時にテーブル作成、終了時に削除
spring.jpa.hibernate.ddl-auto=create-drop
# SQL ログを出力して学習しやすくする
spring.jpa.show-sql=true

# H2 Console（ブラウザで DB を閲覧）
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## 💡 ポイント

| 特徴                 | 説明                                                                      |
| -------------------- | ------------------------------------------------------------------------- |
| `Auto-configuration` | `H2 の依存を追加するだけで DataSource が自動構成される`                   |
| `Lombok`             | `@Data 1つで getter/setter/toString 等を自動生成。ボイラープレートを排除` |
| `JPA DDL Auto`       | `エンティティクラスからテーブルを自動生成。開発中は create-drop が便利`   |

---

> **次のステップ**: [Step 3: Repository 層を作成する](./step3_repository.md)
