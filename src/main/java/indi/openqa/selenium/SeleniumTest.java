/**
 * 
 */
package indi.openqa.selenium;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import indi.test.TestSeparateExtension;

/**
 * 官网文档：https://www.selenium.dev/documentation/en/
 * 
 * <p>需要先安装驱动：
 * 
 * <li>Firefox: https://github.com/mozilla/geckodriver/releases
 * <li>Chrome: http://chromedriver.storage.googleapis.com/index.html
 * 
 * @author wzh
 * @since 2020.06.09
 */
@ExtendWith(TestSeparateExtension.class)
class SeleniumTest {
    
    @BeforeAll
    static void before() {
        // 设置驱动路径
        // Firefox
        System.setProperty("webdriver.gecko.driver", "D:\\myApplication\\SeleniumDriver\\geckodriver.exe");
        // Chrome version 83
        System.setProperty("webdriver.chrome.driver", "D:\\myApplication\\SeleniumDriver\\chromedriver_83.exe");
    }

    /**
     * 请先看类的注释。始于官方的教程。使用Chrome，毕竟比较流行。
     * 
     * <p>将执行本机安装的浏览器，需要确保驱动与浏览器版本一致！
     * 启动后会出现红色信息说Chrome的驱动还需要先开放对远程主机的访问，否则只能访问本地链接：https://chromedriver.chromium.org/security-considerations，
     * 但亲测并不需要！！
     * 
     * 
     * @author DragonBoom
     * @since 2020.06.09
     */
    @Test
    void officialDocumentTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Proxy proxy = new Proxy();
//        proxy.setAutodetect(true);// 只能自动设置非SSL的代理。。。
        proxy.setHttpProxy("127.0.0.1:10809");
        proxy.setSslProxy("127.0.0.1:10809");// SSL的代理需要另行设置！！
        chromeOptions.setProxy(proxy);
        // 设置启动参数
        // a. 设置远程访问白名单。弃用-不需要
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            driver.get("https://google.com");
            System.out.println(driver.getTitle());
            driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
            WebElement firstResult = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h3>div")));
            System.out.println(firstResult.getAttribute("textContent"));
        } finally {
            driver.quit();
        }
    }
}
