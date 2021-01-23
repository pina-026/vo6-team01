# vol6-team01

ウィンターハッカソン vol.6 チーム1の開発用

### 概要

ここにサービスの概要を書く

### 使い方

```sh
$ make # docker-compose up -d をする

$ make java/bash # Java コンテナに入る
$ make react/ash # React コンテナに入る

$ make mysql # MySQLコンテナに入る
$ make redis/bash # Redisコンテナに入る

# 困ったら
$ make help # コマンド一覧が出力される
```

### 開発環境

#### サーバサイドプログラミング

```sh
# Java 開発時
$ make # Dockerコンテナを立ち上げる docker-compose up -d
$ make java/bash # Javaコンテナに入る
$ sh gradlew build # Gradlewビルド
$ java -jar build/libs/api-0.0.1-SNAPSHOT.jar # サーバ起動 Run Java program
```

#### フロントエンド

```sh
# React 開発時
$ make # Dockerコンテナを立ち上げる docker-compose up -d
$ make react/ash # Reactコンテナに入る
$ npm install
$ npm run start
```

### デプロイ(外部公開)について

```sh
brew install awscli
```

AWSのセキュリティ認証情報からアクセスキーを取得します。

**docker-compose.prod.yml** や下記の`301151589520.dkr.ecr.ap-northeast-1.amazonaws.com` は自身のECRのURLでお願いします。

```sh
$ docker context create ecs winter
# ここでAWSのアクセスキーIDとシークレットアクセスキーの入力をする
$ aws ecr get-login-password --region ap-northeast-1 | docker login --username AWS --password-stdin 301151589520.dkr.ecr.ap-northeast-1.amazonaws.com
$ make docker/build
$ make docker/prod/push
$ docker context use winter
$ make docker/prod/up
```

#### 削除の仕方

```sh
$ make docker/prod/down
```
