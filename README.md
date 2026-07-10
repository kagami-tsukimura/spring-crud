# Spring CRUD

Spring Bootを用いた、ユーザー情報を管理するCRUD（Create / Read / Update / Delete）アプリケーションです。

登録・一覧表示・詳細表示・更新・削除といったWebアプリケーションの基本機能を実装しており、  
Spring BootによるMVCアーキテクチャやJPAを用いたデータ操作を確認できます。

## 主な機能

- ユーザー情報の一覧表示
- ユーザー情報の詳細表示
- ユーザー情報の新規登録
- ユーザー情報の編集
- ユーザー情報の削除
- H2 Databaseを利用したデータ管理

## 使用技術

| 技術 | バージョン |
|------|-----------|
| Java | 25 |
| Spring Boot | 4.0.3 |
| Spring MVC | 4.0.3 |
| Spring Data JPA | 4.0.3 |
| H2 Database | Latest |
| Maven | 3.x |
| Lombok | Latest |

## 起動方法

### 1. リポジトリを取得

```bash
git clone https://github.com/k-shimura7617/spring-crud.git
cd spring-crud
```

### 2. アプリケーションを起動

**Linux / macOS**

```bash
./mvnw spring-boot:run
```

**Windows**

```cmd
mvnw.cmd spring-boot:run
```

### 3. ブラウザからアクセス

```
http://localhost:8080
```

## データベース

開発用データベースとしてH2 Databaseを使用しています。

H2 Console

```
http://localhost:8080/h2-console
```

接続情報は `src/main/resources/application.properties` を参照してください。

## ディレクトリ構成

```text
src
├── main
│   ├── java
│   │   └── com/example/demo
│   │       ├── controller
│   │       ├── entity
│   │       ├── repository
│   │       ├── service
│   │       └── DemoApplication.java
│   └── resources
│       ├── application.properties
│       ├── static
│       └── templates
└── test
```

## 使用ライブラリ

- Spring Boot Starter Web MVC
- Spring Boot Starter Data JPA
- H2 Database
- Lombok
- Spring Boot Starter Test

## 備考

本リポジトリの実装およびREADMEは生成AIを活用して作成しています。  
