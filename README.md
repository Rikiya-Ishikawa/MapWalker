# Practice RPG (Java Desktop App)

## プロジェクト概要
Java（Swing）を使って作成しているデスクトップ向け RPG 風アプリです。  
現時点では、キャラクターがマップ上を移動し、ゴールに到達するとキー操作を受け付けなくなる簡易的なゲームとなっています。

本プロジェクトは、Java GUI・イベント処理・ゲームロジックの基礎を学ぶことを目的としています。

---

## 現状の機能
- キャラクターの上下左右移動
- マップ上のゴール判定
- ゴール到達後のキー入力無効化
- シンプルな描画処理（Swing）

---

## 今後の改善予定
- 命名・可読性：Fログを修正
- マジックナンバー地獄の解消
- 手計算9点チェック（複雑・エラー多発）をenum TilePosition + AABB判定に変更
- boolean gameWon（グローバル汚染）をenum GameState { PLAYING, WON, GAME_OVER }に変更
- TARGET_FPS = 60.0, deltaTime正規化
- Main.javaからJFrameを切離し

---

## 開発環境
- Java 17
- Eclipse（Gradle 未使用）
- Swing（標準ライブラリ）

---

## ディレクトリ構造
practice/
├─ README.md
├─ src/
│   └─ （Javaソースコード）
├─ ref/
│   └─ （参考資料など）
└─ JRE システム・ライブラリー [JavaSE-17]

---

## 実行方法
1. Eclipse で本プロジェクトをインポート  
2. `src` 内のメインクラスを右クリック  
3. 「実行」→「Java アプリケーション」

---

## ライセンス
このプロジェクトは個人学習用のため、特にライセンスは設定していません。

