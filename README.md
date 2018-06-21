# 1062-java-5

## 組員
406410232  
406411016  
406414028  
406410562  

## 遊戲說明  
- 塔防遊戲  
- 此遊戲使用 IntelliJ IDE 編輯  
## 專案使用  
- main檔為 ./src/main/TKUTD.java  
    - Lib 引入  
        - 預設由 IntelliJ 引入  
        - 或手動引入  
         ./natives 裡對應系統之資源檔  
         ./lib/classes/ 所有檔案  
    - MySQL 設定
        - 帳號：root  
        - 密碼：0000  
## 執行遊戲
- Linux 類系統
```
java -jar FinalPro.jar
```
- Windows Command Line
```
java -jar FinalPro.jar
```
## 地圖設定
- 放置在 ./res/maps/ 底下，每張地圖一個資料夾
### 檔案內容
- 包含三個檔案  
    - map.tmx：由 TiledMapEditor 編輯而成
        - 大小： 22x15 tiles  
        - 每格大小 48x48 px
        - 兩層以上 Layer
            - 第二層為路線圖 
    - preview.png：於遊戲內顯示的預覽圖
    - properties.xml：遊戲設定
        - Name：地圖名稱
        - ID：第幾張地圖
        - StartMoney：初始金錢
        - MoneyPerKill：擊殺獲得金額
        - MoneyPerWave：通關獲得金額
        - BaseHpXXX：每個怪物初始血量
        - HealthMultiplier：血量基礎倍率，每關往上乘
        - Spawns：怪物出生點
        - Base：城堡位置
        - Waves：此地圖有幾波怪物
