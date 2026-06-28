# UserManagementSystem - 使用者管理系統

一個基於 Spring Boot 框架開發的後端使用者管理系統，提供完整的使用者註冊、登入、權限驗證與資料維護功能。

## 技術棧 (Tech Stack)
- **後端核心**: Java / Spring Boot
- **MVC 架構**: Spring MVC (`@Controller`, `Model`)
- **前端技術**: JavaScript, HTML5, CSS3
- **模板引擎**: Thymeleaf (負責動態網頁渲染與資料綁定)
- **資料庫**: MySQL / Spring Data JPA
- **專案管理**: Maven

## 核心功能與實作亮點
- **動態頁面渲染**: 使用 `@Controller` 處理路由，並透過 Thymeleaf 將後端數據動態注入到前端 HTML 中。
- **前後端非同步互動 (AJAX/Fetch)**: 運用 **JavaScript Fetch API** 實作局部網頁更新（例如：[請填寫您用 JS 做功能，如：非同步刪除使用者 / 動態狀態切換]），提升使用者體驗，避免頻繁刷新頁面。
- **Thymeleaf 與 JS 數據整合**: 熟練運用 `th:inline="javascript"` 機制，將後端 Model 的變數安全地傳遞給前端 JavaScript 腳本進行邏輯處理。
- **表單驗證與互動**: 結合 Spring Validation 後端驗證與 JavaScript 前端即時檢查，雙重把關資料正確性。
- **快顯訊息回饋**: 利用 `RedirectAttributes` 與 Thymeleaf / JavaScript 提示視窗，在操作成功後給予使用者直觀的反饋。

## 專案重點目錄結構
```text
UserManagementSystem/
├── src/main/java/.../controller/  # 存放 @Controller，負責路由與頁面轉發
├── src/main/java/.../model/       # 存放 Entity 與 DTO 資料模型
├── src/main/java/.../service/     # 存放商業邏輯
└── src/main/resources/
    ├── templates/                 # 存放 Thymeleaf HTML 模板檔案 (.html)
    └── static/                    # 存放靜態資源
        ├── js/                    # 存放自訂的 JavaScript 腳本 (.js)
        └── css/                   # 存放樣式表 (.css)
