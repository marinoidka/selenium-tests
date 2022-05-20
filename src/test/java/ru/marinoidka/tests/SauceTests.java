package ru.marinoidka.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

class SauceTests {
    static  WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);

        // изменять размеры окна - на максимум
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");
    }

    @Test
    void authorisationPositiveTest() {
        // ввести логин
        driver.findElement(By.id("user-name")).click();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        // ввести пароль
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        // нажать на кнопку Login
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();
        // проверки
        assertThat(driver.getCurrentUrl(), equalTo("https://www.saucedemo.com/inventory.html"));
        // проверить наличие элемента
        assertThat(driver.findElements(By.cssSelector("#shopping_cart_container")).size(), not(equalTo(0)));

    }


    @Test
    void happyPathOrderingTest() {
        // ввести логин
        driver.findElement(By.id("user-name")).click();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        // ввести пароль
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        // нажать на кнопку Login
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();
        // выбрать товар и добавить в корзину
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        // зайти в корзину
        driver.findElement(By.id("shopping_cart_container")).click();
        // нажать checkout
        driver.findElement(By.name("checkout")).click();
        assertThat(driver.getCurrentUrl(), equalTo("https://www.saucedemo.com/checkout-step-one.html"));
        // заполнить данные
        driver.findElement(By.cssSelector("[data-test='firstName']")).click();
        driver.findElement(By.cssSelector("[data-test='firstName']")).sendKeys("Marina");
        driver.findElement(By.cssSelector("[data-test='lastName']")).click();
        driver.findElement(By.cssSelector("[data-test='lastName']")).sendKeys("Chernykh");
        driver.findElement(By.cssSelector("[data-test='postalCode']")).click();
        driver.findElement(By.cssSelector("[data-test='postalCode']")).sendKeys("426000");
        // закончить оформление заказа
        driver.findElement(By.id("continue")).click();
        assertThat(driver.getCurrentUrl(), equalTo("https://www.saucedemo.com/checkout-step-two.html"));
        // нажать finish
        driver.findElement(By.cssSelector("[data-test='finish']")).click();
        assertThat(driver.getCurrentUrl(), equalTo("https://www.saucedemo.com/checkout-complete.html"));
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }
}
