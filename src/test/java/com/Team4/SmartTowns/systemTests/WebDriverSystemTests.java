package com.Team4.SmartTowns.systemTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebDriverSystemTests {
    @Value(value = "${local.server.port}")
    private int port;

    WebDriver webDriver;

    @BeforeAll
    static void initDriverManager() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void initDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        webDriver = new FirefoxDriver(options);
    }

    @AfterEach
    void quitDriver() {
        webDriver.quit();
    }

    // quick firefox driver test to make sure the home page loads
    @Test
    public void testHomePageLoads() {
        this.webDriver.get("http://localhost:" + port + "/");
        assert webDriver.findElement(By.cssSelector("h1")).getText().contains("VZTA");
    }

    // firefox driver test to make sure that users are redirected if they try to access admin restricted pages
    @Test
    public void testAdminPageIsSecure() {
        this.webDriver.get("http://localhost:" + port + "/admin/trails/add");
        assert webDriver.getCurrentUrl().endsWith("/login");
    }

    // firefox driver test to test that users can't access their profile when not logged in
    @Test
    public void testProfilePageIsSecure() {
        this.webDriver.get("http://localhost:" + port + "/profile");
        assert webDriver.getCurrentUrl().endsWith("/login");
    }

}
