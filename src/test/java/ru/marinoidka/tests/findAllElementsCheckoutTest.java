package ru.marinoidka.tests;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class findAllElementsCheckoutTest {
    static  WebDriver driver;
    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

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
    }


    @Test
    void shouldFindBMMenuBtnTest() {
        assertThat(driver.findElement(By.id("react-burger-menu-btn")).isDisplayed(), equalTo(true));
    }

    @Test
    void shouldFindBMMenuAllElementsTest() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        assertThat(driver.findElement(By.className("bm-menu-wrap")).isDisplayed(), equalTo(true));
        assertThat(driver.findElement(By.id("inventory_sidebar_link")).isDisplayed(), equalTo(true));
        assertThat(driver.findElement(By.id("about_sidebar_link")).isDisplayed(), equalTo(true));
        assertThat(driver.findElement(By.id("logout_sidebar_link")).isDisplayed(), equalTo(true));
        assertThat(driver.findElement(By.id("reset_sidebar_link")).isDisplayed(), equalTo(true));
        assertThat(driver.findElement(By.id("react-burger-cross-btn")).isDisplayed(), equalTo(true));
        driver.findElement(By.id("react-burger-cross-btn")).click();
    }

    @Test
    void shouldFindHeaderLabelTest() {
        assertThat(driver.findElement(By.className("header_label")).isDisplayed(), equalTo(true));
    }

    @Test
    void shouldFindLogoTest() {
        assertThat(driver.findElement(By.cssSelector(".app_logo")).isDisplayed(), equalTo(true));
    }

    @Test
    void shouldFindShoppingCartTest() {
        assertThat(driver.findElement(By.id("shopping_cart_container")).getAttribute("class"), equalTo("shopping_cart_container"));
    }

    @Test
    void shouldFindShoppingCartLinkTest() {
        assertThat(driver.findElement(By.className("shopping_cart_link")).isDisplayed(), equalTo(true));
    }

    @Test
    void shouldFindShoppingCartBadgeTest() {
        assertThat(driver.findElement(By.className("shopping_cart_badge")).isDisplayed(), equalTo(true));
    }

    @Test
    void shouldFindHeaderCheckoutTest() {
        assertThat(driver.findElement(By.cssSelector(".header_secondary_container")).getText(), equalTo("CHECKOUT: YOUR INFORMATION"));
    }

    @Test
    void shouldFindCheckoutInfoContainerTest() {
        assertThat(driver.findElement(By.className("checkout_info_container")).isDisplayed(), equalTo(true));
    }

    @Test
    void shouldFindFirstnameTest() {
        assertThat(driver.findElement(By.cssSelector("[data-test='firstName']")).isDisplayed(), equalTo(true));
    }

    @Test
    void shouldFindLastnameTest() {
        assertThat(driver.findElement(By.cssSelector("[data-test='lastName']")).isDisplayed(), equalTo(true));
    }

    @Test
    void shouldFindZipCodeTest() {
        assertThat(driver.findElement(By.cssSelector("[data-test='postalCode']")).getAttribute("type"), equalTo("text"));
    }

    @Test
    void shouldFindErrorMessageContainerTest() {
        assertThat(driver.findElement(By.className("error-message-container")).isDisplayed(), equalTo(true));
    }

    @Test
    void shouldFindCheckoutBtnsTest() {
        assertThat(driver.findElement(By.className("checkout_buttons")).isDisplayed(), equalTo(true));
    }

    @Test
    void shouldFindCancelBtnTest() {
        assertThat(driver.findElement(By.cssSelector("[data-test='cancel']")).getText(), equalTo("CANCEL"));
    }

    @Test
    void shouldFindBackCancelTest() {
        assertThat(driver.findElement(By.className("back-image")).isDisplayed(), equalTo(true));
    }

    @Test
    void shouldFindContinueBtnTest() {
        assertThat(driver.findElement(By.cssSelector("[data-test='continue']")).getAttribute("value"), equalTo("Continue"));
    }

    @Test
    void shouldFindTwitterLinkTest() {
        assertThat(driver.findElement(By.className("social_twitter")).isDisplayed(), equalTo(true));
    }

    @Test
    void shouldFindFacebookLinkTest() {
        assertThat(driver.findElement(By.className("social_facebook")).isDisplayed(), equalTo(true));
    }

    @Test
    void shouldFindLinkedinLinkTest() {
        assertThat(driver.findElement(By.className("social_linkedin")).isDisplayed(), equalTo(true));
    }

    @Test
    void shouldFindCopyTest() {
        assertThat(driver.findElement(By.className("footer_copy")).isDisplayed(), equalTo(true));
    }

    @Test
    void shouldFindRobotTest() {
        assertThat(driver.findElement(By.className("footer_robot")).isDisplayed(), equalTo(true));
    }


    @AfterAll
    static void afterAll() {
        driver.quit();
    }

}
