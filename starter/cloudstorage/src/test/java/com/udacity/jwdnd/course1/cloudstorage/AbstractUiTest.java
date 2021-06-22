package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractUiTest {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;

    private static String baseUrl;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseUrl = "http://localhost:";
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    public Integer getPort() {
        return port;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}
