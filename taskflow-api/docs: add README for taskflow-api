# TaskFlow API - 團隊任務與進度追蹤系統後端

# 專案用途 (Project Purpose)
TaskFlow API 是一個專為「團隊任務與進度追蹤系統」打造的現代化後端 RESTful API 服務。
本專案採用前後端分離架構設計，提供安全、穩定且高效的資料處理中心。前端（Web、iOS、Android）可透過標準的 HTTP 請求與 JSON 格式與本系統進行串接，實現使用者註冊登入、任務建立、狀態追蹤以及權限隔離等核心業務邏輯。


# 技術棧 (Tech Stack)
核心框架： Java 21, Spring Boot
安全認證： Spring Security, JWT (JSON Web Token), BCrypt 密碼加密
資料庫存取： Spring Data JPA (Hibernate)
資料驗證： Spring Boot Starter Validation (@Valid, @NotBlank 等)
API 文件化： SpringDoc OpenAPI (Swagger UI)
開發輔助： Lombok (減少樣板程式碼)


# 核心功能 (Core Features)
1.帳號與安全認證 (Authentication)
使用者註冊： 嚴格的資料格式驗證與信箱重複檢查，密碼採用 BCrypt 強加密儲存。
使用者登入： 驗證憑證並核發具備時效性的 JWT Token。
無狀態驗證： 採用 Stateless Session 策略，透過 JWT 進行全站 API 路由保護。

2.任務管理 (Task Management)
CRUD 完整操作： 支援任務的新增、查詢、修改與刪除。
彈性任務屬性： 可設定任務標題、描述、狀態 (TODO, IN_PROGRESS, COMPLETED)、優先級 (LOW, MEDIUM, HIGH) 與截止日期。
自動時間戳記： 系統自動追蹤任務的建立時間 (createdAt) 與最後更新時間 (updatedAt)。


# 實作亮點 (Implementation Highlights)
1.嚴密的資料隔離與防護 (Tenant Isolation & Anti-IDOR) :
  在 Service 層實作 findByIdAndUserId 邏輯，確保使用者只能查詢、修改或刪除屬於自己的任務，徹底防範「水平越權漏洞 (IDOR)」。

2.標準化 DTO 設計模式 (Data Transfer Object) :
  嚴格劃分 Entity 與 DTO 的界線。Request DTO 負責第一線資料校驗，Response DTO 負責過濾敏感資料（如密碼）並防止實體關聯導致的 JSON 無窮迴圈 (Circular Reference)。

3.優雅的全域例外處理 (Global Exception Handling) :
  使用 @RestControllerAdvice 集中攔截系統例外（包含格式驗證失敗、業務邏輯錯誤、未知例外）。
  統一回傳標準化 ErrorResponse 格式 (含 Timestamp、Status、Error、Message)，極大降低前後端溝通成本。

4.自動化互動式 API 文件
  整合 Swagger UI，並設定全域 BearerAuth 授權按鈕，開發與測試人員可直接在瀏覽器介面上夾帶 JWT Token 進行 API 實測。


# 專案重點目錄結構 (Directory Structure)
  專案採用標準的 Spring Boot 分層架構 (Layered Architecture)，職責劃分明確 :
  src/main/java/com/taskflow/api
├── config/             # 全域設定檔 (如 OpenAPI/Swagger 設定)
├── controller/         # API 控制器 (接收 HTTP 請求、路由分發)
├── dto/                # 資料傳輸物件 (Data Transfer Objects)
│   ├── request/        # 封裝並驗證前端傳來的請求資料
│   └── response/       # 封裝並過濾準備回傳給前端的資料
├── entity/             # JPA 實體模型 (對應資料庫資料表與關聯)
├── exception/          # 全域例外處理 (@RestControllerAdvice 與自訂例外)
├── repository/         # 資料存取層 (Spring Data JPA 介面，負責 DB 互動)
├── security/           # 安全防護層 (JWT Filter, Token Provider, Security Config)
└── service/            # 業務邏輯層 (商業邏輯處理、權限判斷)
    └── impl/           # 業務邏輯實作類別
