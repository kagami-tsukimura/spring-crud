# Step 6: H2 Console で DB を確認する（5分）

> **前のステップ**: [Step 5: CRUD API を作成する](./step5_crud_api.md)
>
> **背景**: API 経由でデータを操作したあと「本当に DB に保存されているか」をコードだけで確認するのは難しい。H2 Console は Spring Boot が用意するブラウザ UI で、インメモリ DB の中身を SQL で直接確認できる。
>
> **このステップでできること**: ブラウザ上で H2 Console を開き、`TASK` テーブルに保存されたデータを SQL で確認する。

---

## 🎯 学ぶこと

- H2 Console による DB の視覚的確認
- Spring Boot の Auto-configuration で組み込み DB がすぐ使える利便性

## 6-1. H2 Console の利用

- **背景**: データの登録や更新を行った後、プログラムのバグではなく「データが正しく入っているか」を直接目で確認したい場面が多くあります。
- **機能の役割**: H2 Console はブラウザからデータベースの中身を操作・確認できる GUI ツールです。開発中、DB クライアントソフトを別途インストールすることなく、テーブル構造やデータを SQL で即座に確認できます。

1. アプリ起動中にブラウザで <http://localhost:8080/h2-console> にアクセス
2. 接続情報を入力:

| 項目        | 値                   |
| ----------- | -------------------- |
| `JDBC URL`  | `jdbc:h2:mem:taskdb` |
| `User Name` | `sa`                 |
| `Password`  | `（空欄）`           |

1. 「Connect」をクリック
1. 左ペインに `TASK` テーブルが表示される
1. `SELECT * FROM TASK;` を実行して、作成したタスクを確認

## 💡 ポイント

- **インメモリ DB**: アプリ停止と同時にデータが消える（開発・テスト用途）
- 本番では PostgreSQL や MySQL に差し替えるが、**コードの変更は `application.properties` の接続先のみ**（JPA の抽象化の恩恵）

---

> **次のステップ**: [Step 7: テストを書く](./step7_testing.md)
