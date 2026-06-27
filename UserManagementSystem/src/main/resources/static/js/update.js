document.addEventListener("DOMContentLoaded", function () {
    const mainMenu = document.getElementById("main-menu");
    const resultMessage = document.getElementById("result-message");

    const menuMap = {
        "btn-update-name": "name-form-container",
        "btn-update-email": "email-form-container",
        "btn-update-dept": "dept-form-container",
        "btn-update-gender": "gender-form-container"
    };

    // 1. 選單切換
    Object.keys(menuMap).forEach(btnId => {
        const button = document.getElementById(btnId);
        if (button) {
            button.addEventListener("click", function () {
                mainMenu.classList.add("hidden");
                const targetFormId = menuMap[btnId];
                document.getElementById(targetFormId).classList.remove("hidden");
                resultMessage.innerHTML = "";
            });
        }
    });

    // 2. 返回首頁
    const backButtons = document.querySelectorAll(".btn-back");
    backButtons.forEach(btn => {
        btn.addEventListener("click", function () {
            const allForms = document.querySelectorAll(".form-container");
            allForms.forEach(form => form.classList.add("hidden"));
            mainMenu.classList.remove("hidden");
            const parentForm = btn.closest("form");
            if (parentForm) parentForm.reset();
        });
    });

    // 3. 各表單對應的後端 POST 路由網址
    const formUrls = {
        "name-form": "/employee/update-name",
        "email-form": "/employee/update-email",
        "dept-form": "/employee/update-dept",
        "gender-form": "/employee/update-gender"
    };

    // 4. 統一監聽所有表單提交事件
    Object.keys(formUrls).forEach(formId => {
        const form = document.getElementById(formId);
        if (!form) return;

        form.addEventListener("submit", function (e) {
            e.preventDefault(); // 阻止網頁跳轉重整

            const idInput = this.querySelector('input[name="id"]').value.trim();

            const idNumber = Number(idInput);
            if (isNaN(idNumber) || !Number.isInteger(idNumber) || idNumber < 1) {
                alert("驗證失敗：員工編號必須是正整數且不能小於 1");
                return;
            }

            // === 特殊驗證：針對「姓名表單」做額外嚴格驗證 ===
            if (formId === "name-form") {
                const nameInput = this.querySelector('input[name="name"]').value.trim();

                if (nameInput === "") {
                    alert("驗證失敗：新姓名不能為空值");
                    return;
                }

                const nameRegex = /^[\u4e00-\u9fa5a-zA-Z\s]+$/;
                if (!nameRegex.test(nameInput)) {
                    alert("驗證失敗：姓名格式不正確，只能包含中英文字母");
                    return;
                }
            }

            // === 格式驗證通過，發送請求給後端 ===
            const url = formUrls[formId];
            const formData = new FormData(this);

            fetch(url, {
                method: "POST",
                body: formData
            })
            .then(response => response.text())
            .then(htmlFragment => {
                resultMessage.innerHTML = htmlFragment;

                if (htmlFragment.includes("成功")) {
                    alert("更新成功！");
                    form.querySelector(".btn-back").click(); 
                } else {
                    const errorMsg = resultMessage.querySelector('b') ? resultMessage.querySelector('b').innerText : "更新失敗";
                    alert("驗證失敗：" + errorMsg);
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("系統連線錯誤，請稍後再試");
            });
        });
    });
});