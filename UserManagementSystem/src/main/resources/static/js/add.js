document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");

    if (form) {
        form.addEventListener("submit", function (event) {
            // 1. 取得所有輸入框的數值，並去除前後空白
            const name = document.getElementById("name").value.trim();
            const gmail = document.getElementById("gmail").value.trim();
            const department = document.getElementById("department").value.trim();
            const gender = document.getElementById("gender").value.trim();

            // 2. 自訂檢查：信箱必須包含 @ 符號且不能太短 (舉例)
            if (!gmail.includes("@") || gmail.length < 5) {
                alert("電子信箱格式不正確，請重新檢查！");
                
                // 💡 核心關鍵：阻擋表單送出，網頁絕對不會跳轉，會停留在原頁面
                event.preventDefault(); 
                return;
            }

            // 3. 自訂檢查：性別限定輸入「男」或「女」
            if (gender !== "男" && gender !== "女") {
                alert("性別欄位請輸入 '男' 或 '女'！");
                
                // 💡 核心關鍵：阻擋表單送出，留在原頁面
                event.preventDefault(); 
                return;
            }

            // 4. 如果以上檢查都通過了，JS 就不會執行 event.preventDefault()
            // 瀏覽器就會順利把資料送往後端 Controller 進行儲存並跳轉。
        });
    }
});