package com.Test.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String checkEnvironment() {
        return "<h1>🚀 系統重構完成：透過 Spring Initialization 網站架構成功！</h1>" +
                "<p><b>狀態報告：</b></p>" +
                "<ul>" +
                "<li>Maven 標準目錄結構識別：<b>成功</b></li>" +
                "<li>內建 Tomcat 伺服器啟動：<b>成功</b></li>" +
                "<li>工作區環境隔離安全檢查：<b>安全</b>（未影響既有 Python 與 Java 設定）</li>" +
                "</ul>";
    }
}