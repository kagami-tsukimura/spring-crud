# GitHub Actions YAML解説

## 実践：ci.yml の中身と解説

設定ファイルの例 (.github/workflows/ci.yml)
YAML
name: Python Testing CI

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  test:
    runs-on: ubuntu-latest

    steps:
    - name: Checkout code
      uses: actions/checkout@v4

    - name: Set up Python
      uses: actions/setup-python@v5
      with:
        python-version: '3.10'

    - name: Install dependencies
      run: |
        python -m pip install --upgrade pip
        pip install pytest
        if [ -f requirements.txt ]; then pip install -r requirements.txt; fi

    - name: Run pytest
      run: pytest

## 各行の詳細解説

行目	コード	何をしているか
1	name: ...	このワークフローの名前です。GitHubのActionsタブに表示されます。
3-7	on: ...	実行タイミングの指定。「mainブランチへのPush」または「Pull Request」があった時に動きます。
9-10	jobs: test:	「test」という名前のジョブを開始します。
11	runs-on: ubuntu-latest	仮想マシンの種類。GitHubが用意してくれるLinux(Ubuntu)の最新版で動かすよ、という意味です。
14-15	uses: actions/checkout@v4	実行環境（Linux）に、**自分のプログラムコードをコピー（チェックアウト）**してきます。
17-20	uses: actions/setup-python@v5	実行環境に Pythonをインストールします。バージョンも指定可能です。
22-26	run: ... (Install)	テストに必要な道具（pytestなど）をインストールします。requirements.txtがあればそれも読み込みます。
28-29	run: pytest	ついに本番！ pytest を実行します。ここで1つでも失敗すると、GitHub上で❌マークがつきます。
