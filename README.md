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
