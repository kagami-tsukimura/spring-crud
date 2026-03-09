# Step 1: Hello World REST API（10分）

> **前のステップ**: [Step 0: プロジェクト構成を理解する](./step0_project_structure.md)
>
> **背景**: REST API の世界では、URL と HTTP メソッドの組み合わせでリソース操作を表現する。Spring は `@RestController` + `@GetMapping` のアノテーションだけで、この仕組みを XML 設定なしで実現できる。
>
> **このステップでできること**: `GET /hello` にアクセスすると文字列を返す REST API を最小コードで作成し、curl で動作確認する。

---

## 🎯 学ぶこと

- `@RestController` — REST API コントローラーの宣言
- `@GetMapping` — HTTP GET メソッドのマッピング
- **DI（Dependency Injection）** の基礎

## 1-1. HelloController の作成

- **背景**: Web アプリケーションにおいて、ユーザー（ブラウザ）からのリクエストを最初に受け取り、適切なレスポンスを返す「受付窓口」が必要です。
- **ファイル役割**: `HelloController.java` は、URL パス（`/hello` など）と Java のメソッドを紐づけ、文字列やデータを返却する役割を担います。

`src/main/java/com/example/demo/controller/HelloController.java` を新規作成:

```java
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController = @Controller + @ResponseBody
// JSON/テキストを直接返すことを宣言する
@RestController
public class HelloController {

    // HTTP GET /hello にマッピング
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }

    // パスパラメータを受け取る例
    @GetMapping("/hello/{name}")
    public String helloName(@org.springframework.web.bind.annotation.PathVariable String name) {
        return "Hello, " + name + "!";
    }
}
```

## 1-2. 動作確認

```bash
./mvnw spring-boot:run
```

```bash
# 別ターミナルで
curl http://localhost:8080/hello
# => Hello, Spring Boot!

curl http://localhost:8080/hello/Spring
# => Hello, Spring! ... に名前が入る
```

## 💡 ポイント

- **コンポーネントスキャン**: `@SpringBootApplication` のあるパッケージ配下の `@RestController` は自動検出される
- **設定不要**: XML も設定ファイルも書かずに REST API が完成（Convention over Configuration）

---

> **次のステップ**: [Step 2: Entity を作成する](./step2_entity.md)
