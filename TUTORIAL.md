# Spring Boot 4.0 ハンズオンチュートリアル

> **目的**: Spring Boot の主要な特徴を**1時間で体験**するステップバイステップガイド
>
> **対象**: SES面談に向けて Spring の開発体験を積みたい方
>
> **背景**: Java Web 開発の現場では Spring Boot が事実上の標準。XML 設定不要・Auto-configuration・DI の三本柱を手を動かして理解することで、面談での技術説明力が身につく。
>
> **このチュートリアルで作るもの**: タスク管理の CRUD REST API（H2 インメモリDB + Spring Data JPA）を 8 ステップで段階的に構築する。

---

## 目次

| Step                                            | 内容                          | 所要時間 | 学べる Spring 特徴                         |
| ----------------------------------------------- | ----------------------------- | -------- | ------------------------------------------ |
| [Step 0](./tutorial/step0_project_structure.md) | `プロジェクト構成を理解する`  | `5分`    | `@SpringBootApplication`, `組み込みTomcat` |
| [Step 1](./tutorial/step1_hello_rest_api.md)    | `Hello World REST API`        | `10分`   | `@RestController`, `@GetMapping`           |
| [Step 2](./tutorial/step2_entity.md)            | `Entity を作成する`           | `10分`   | `@Entity`, `Lombok`, `Auto-configuration`  |
| [Step 3](./tutorial/step3_repository.md)        | `Repository 層を作成する`     | `5分`    | `JpaRepository`, `Query Methods`           |
| [Step 4](./tutorial/step4_service.md)           | `Service 層を作成する`        | `10分`   | `@Service`, `DI/IoC`                       |
| [Step 5](./tutorial/step5_crud_api.md)          | `CRUD API を作成する`         | `10分`   | `@PostMapping`, `ResponseEntity`           |
| [Step 6](./tutorial/step6_h2_console.md)        | `H2 Console で DB を確認する` | `5分`    | `インメモリDB`, `H2 Console`               |
| [Step 7](./tutorial/step7_testing.md)           | `テストを書く`                | `5分`    | `@SpringBootTest`, `MockMvc`               |

---

## 参考資料

- [使用技術と代替候補一覧](./tutorial/comparison.md) — 技術スタック比較 + 面談で話せるポイント
