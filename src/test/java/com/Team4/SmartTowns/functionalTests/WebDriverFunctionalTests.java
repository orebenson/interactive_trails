package com.Team4.SmartTowns.functionalTests;

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
public class WebDriverFunctionalTests {
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


    // testing registration, to ensure that a user can be created in the database then logged in with
    @Test
    public void testRegistration() {

        String testUsername = "testUser1";
        String testEmail = "testUser1@gmail.com";
        String testPassword = "testUser1P@ssword";
        String testAddress1 = "testUser1Address";
        String testAddress2 = "testUser1Address2";
        String testCity = "testUser1City";
        String testZipcode = "testUser1Zipcode";

        // register a new user
        this.webDriver.get("http://localhost:" + port + "/registration");
        this.webDriver.findElement(By.name("userName")).sendKeys(testUsername);
        this.webDriver.findElement(By.name("email")).sendKeys(testEmail);
        this.webDriver.findElement(By.name("password")).sendKeys(testPassword);
        this.webDriver.findElement(By.name("address")).sendKeys(testAddress1);
        this.webDriver.findElement(By.name("address2")).sendKeys(testAddress2);
        this.webDriver.findElement(By.name("city")).sendKeys(testCity);
        this.webDriver.findElement(By.name("zipCode")).sendKeys(testZipcode);
        this.webDriver.findElement(By.xpath("//button[@type='submit' and text()='Register']")).click();
        assert webDriver.findElement(By.cssSelector("h1")).getText().contains("VZTA");

        // test that the user can then login successfully
        this.webDriver.get("http://localhost:" + port + "/login");
        this.webDriver.findElement(By.name("username")).sendKeys(testUsername);
        this.webDriver.findElement(By.name("password")).sendKeys(testPassword);
        this.webDriver.findElement(By.cssSelector("input[type='submit'][value='Sign In']")).click();
        assert webDriver.findElement(By.cssSelector("h1")).getText().contains("Login Success!");

        // test that the user can then see their profile successfully with their info
        this.webDriver.get("http://localhost:" + port + "/profile");
        assert webDriver.findElement(By.xpath("/html/body/div/div/p[1]")).getText().contains(testUsername);
        assert webDriver.findElement(By.xpath("/html/body/div/div/p[2]")).getText().contains(testEmail);
        assert webDriver.findElement(By.xpath("/html/body/div/div/p[3]")).getText().contains(testAddress1);
        assert webDriver.findElement(By.xpath("/html/body/div/div/p[4]")).getText().contains(testAddress2);
        assert webDriver.findElement(By.xpath("/html/body/div/div/p[5]")).getText().contains(testCity);
        assert webDriver.findElement(By.xpath("/html/body/div/div/p[6]")).getText().contains(testZipcode);
    }

    // testing dark mode, to check that a user is able to see the dark mode when they change the theme
    @Test
    public void testDarkMode() {
        // test that the user can change to dark mode
        this.webDriver.get("http://localhost:" + port + "/");
        this.webDriver.findElement(By.id("darkModeToggle")).click();
        assert webDriver.findElement(By.cssSelector("body")).getAttribute("class").contains("dark-mode");
    }



}
