# 使用技術と代替候補一覧

> チュートリアルで使用した技術スタックと、各カテゴリの代替候補を比較する
>
> **背景**: 面談では「なぜその技術を選んだか」「他の選択肢を知っているか」が問われる。各技術の選定理由と代替候補を把握しておくことで、技術選定の意思決定ができるエンジニアとしての説得力が増す。
>
> **このページでわかること**: チュートリアルで採用した技術ごとの選定理由・代替候補・Spring Boot の主要特徴・面談で使えるトーク例。

---

## プロジェクト全体構成

| カテゴリ         | 採用技術             | 選定理由                                                                | 代替候補                                                         |
| ---------------- | -------------------- | ----------------------------------------------------------------------- | ---------------------------------------------------------------- |
| `フレームワーク` | `Spring Boot 4.0.3`  | `Javaエンタープライズの事実上の標準。Auto-configuration による高速開発` | `Quarkus`, `Micronaut`, `Jakarta EE (Payara/WildFly)`, `Helidon` |
| `言語`           | `Java 25`            | `最新LTS。Virtual Threads、Pattern Matching 等のモダン機能`             | `Kotlin`, `Scala`, `Groovy`                                      |
| `ビルドツール`   | `Maven`              | `XML ベースで設定が明示的。企業案件での採用率が高い`                    | `Gradle (Groovy DSL / Kotlin DSL)`                               |
| `Webサーバー`    | `Tomcat（組み込み）` | `Spring Boot デフォルト。安定性・実績`                                  | `Jetty`, `Undertow`, `Netty`                                     |

---

## データアクセス層

| カテゴリ               | 採用技術                      | 選定理由                                                                          | 代替候補                                                                        |
| ---------------------- | ----------------------------- | --------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| `ORM`                  | `Spring Data JPA (Hibernate)` | `Repository インターフェースだけでCRUD実装不要。Query Methods による宣言的クエリ` | `MyBatis (SQL直書き派向け)`, `jOOQ (型安全SQL)`, `JDBI`, `Spring JDBC Template` |
| `データベース`         | `H2 (インメモリ)`             | `開発・テスト用途。依存追加だけで利用可能`                                        | `PostgreSQL`, `MySQL`, `MariaDB`, `Oracle`, `SQLite`                            |
| `ボイラープレート削減` | `Lombok`                      | `@Data 等でgetter/setter/コンストラクタを自動生成`                                | `Java Records (Java 16+)`, `Kotlin data class`, `IDE自動生成`                   |

---

## テスト

| カテゴリ               | 採用技術  | 選定理由                                                   | 代替候補                                                       |
| ---------------------- | --------- | ---------------------------------------------------------- | -------------------------------------------------------------- |
| `テストフレームワーク` | `JUnit 5` | `Spring Boot デフォルト。パラメータ化テスト等の機能が充実` | `TestNG`, `Spock (Groovy)`                                     |
| `モック`               | `MockMvc` | `HTTPレイヤーのテストに特化。サーバー不要で高速`           | `RestAssured`, `WebTestClient (WebFlux用)`, `TestRestTemplate` |

---

## Spring Boot の主要特徴まとめ

| 特徴                            | 説明                                                                              | 該当 Step   |
| ------------------------------- | --------------------------------------------------------------------------------- | ----------- |
| `Auto-configuration`            | `依存を追加するだけで DataSource, JPA, Web等が自動設定される`                     | `Step 2, 6` |
| `DI / IoC`                      | `@Service, @Repository のインスタンスを Spring コンテナが自動管理・注入`          | `Step 4, 5` |
| `アノテーション駆動`            | `XML不要。@RestController, @Entity 等のアノテーションで宣言的に設定`              | `全Step`    |
| `Convention over Configuration` | `規約に従えば設定不要。パッケージ構成やメソッド命名で動作が決まる`                | `Step 1, 3` |
| `Spring Data Query Methods`     | `findByXxx のメソッド名だけで SQL が自動生成される`                               | `Step 3`    |
| `組み込みサーバー`              | `Tomcat内蔵で JAR 単体実行可能。デプロイが容易`                                   | `Step 0`    |
| `テストサポート`                | `@SpringBootTest + MockMvc で統合テストが容易に書ける`                            | `Step 7`    |
| `プロファイル`                  | `application-{profile}.properties で環境ごとに設定を切り替え可能（今回は未使用）` | `-`         |

---

## 面談で話せるポイント

### 「なぜ Spring Boot を選ぶのか？」

1. **エンタープライズの標準**: Java Web 開発のデファクトスタンダード
2. **生産性**: Auto-configuration と Spring Data で最小限のコードで動く
3. **エコシステム**: Security, Batch, Cloud, AI 等の公式サブプロジェクトが充実
4. **テスタビリティ**: DI により単体テスト・統合テストが書きやすい
5. **スケーラビリティ**: 小規模 API から大規模マイクロサービスまで対応

### 「Spring Boot 4.0 の新機能は？」

- **Spring Framework 7.0 ベース**（Jakarta EE 11 対応）
- **Java 25 対応**（Virtual Threads の本格サポート）
- **モジュール分割の強化**（より軽量な JAR 構成）
- **Null Safety の改善**（JSpecify 統合）
- **Spring AI Core**（LLM クライアントの統合）
