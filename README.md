# 1062-java-5

## 組員
406410232  
406411016  
406414028  
406410562  

## 架構
- main
    - main TD
        - state
            - MainMenu
            - MapMenu
            - Credits
    - ImageManager 
        - 建表起來放好了 參考網路資源的寫法
    - ParameterManager
        - 把定值的參數整理起來放在一起 這樣要變動參數時比較方便 (5/26更新)
- states
    - Game
    - MainMenu
    - MapMenu
        - 讀取XML 參考網路上的寫法就行
        <
    - Summary
        - END GAME
    - Credits
- towers (用繼承寫好了)
    - tower
        - ShootingTower (單純射擊的放這一塊)
            - NormalTower
            - AirTower
            - GroundTower
        - SlowTower
        - IceTower
        - 其他功能塔
- enemy
    - 有地面 空軍 和BOSS 這個還要想
- misc (其他或混合的東西 要處裡的都放這) 
    - 地圖處理
    - 文字
    - 子彈
- res
    - 圖片
    - 地圖
    - 字型