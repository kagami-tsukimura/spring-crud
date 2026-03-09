# Step 0: プロジェクト構成を理解する（5分）

> **前提**: Spring Initializr でプロジェクトを作成済み
>
> **背景**: 「どこに何を書くか」が分からないまま進むと迷子になりやすい。Spring Boot は Maven の標準ディレクトリ規約に従っており、構成を把握するだけでコード配置の迷いがなくなる。
>
> **このステップでできること**: プロジェクトを起動し、ブラウザでアプリが動いていることを確認する。

---

## ディレクトリ構造の役割

Spring Boot は「設定より規約（Convention over Configuration）」を重視しており、特定のディレクトリにファイルを置くだけで、ビルドツールやフレームワークが自動的にその役割を認識します。

- **背景**: 大規模開発において、開発者ごとに配置場所がバラバラだと保守性が著しく低下するため、標準的な構成に従うことが Java 開発の鉄則です。
- **何ができるか**: どこにソースコードを書き、どこに設定ファイルを置くべきかの「定位置」を把握できます。

```bash
demo/
├── pom.xml                          # Maven ビルド定義
├── mvnw / mvnw.cmd                  # Maven Wrapper（環境に Maven 不要）
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   └── DemoApplication.java # エントリーポイント
│   │   └── resources/
│   │       └── application.properties # アプリ設定
│   └── test/
│       └── java/com/example/demo/
│           └── DemoApplicationTests.java
```

## 確認: アプリケーションを起動する

```bash
./mvnw spring-boot:run
```

ブラウザで <http://localhost:8080> にアクセス → Whitelabel Error Page が表示されれば成功（まだエンドポイントがないため）。

`Ctrl+C` で停止。

## 💡 Spring Boot のポイント

| 特徴                     | 説明                                                                                  |
| ------------------------ | ------------------------------------------------------------------------------------- |
| `@SpringBootApplication` | コンポーネントスキャン + Auto-configuration + 設定クラスを1つにまとめたアノテーション |
| `組み込みサーバー`       | `Tomcat が内蔵されており、JAR 単体で起動可能`                                         |
| `Maven Wrapper`          | `mvnw により、Maven をインストールしなくてもビルドできる`                             |

---

> **次のステップ**: [Step 1: Hello World REST API](./step1_hello_rest_api.md)
