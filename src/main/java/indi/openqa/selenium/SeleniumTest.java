/**
 * 
 */
package indi.openqa.selenium;

import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import indi.collection.CollectionUtils;
import indi.io.ClassPathProperties;
import indi.test.TestSeparateExtension;
import indi.util.TestUtils;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
class SeleniumTest {
    
    @BeforeAll
    static void before() {
        // 设置驱动路径
        // 具体已下载了哪些版本可到目录下查看
        // Firefox
        System.setProperty("webdriver.gecko.driver", "D:\\myApplication\\SeleniumDriver\\geckodriver.exe");
        // Chrome version: 84
        System.setProperty("webdriver.chrome.driver", "D:\\myApplication\\SeleniumDriver\\chromedriver_84.exe");
    }

    /**
     * 请先看类的注释。始于官方的教程。使用Chrome驱动，毕竟比较流行。
     * 
     * <p>将执行本机安装的浏览器，需要确保驱动与浏览器版本一致！若不一致，将抛SessionNotCreatedException异常
     * 
     * <p>启动后会出现红色警告信息说Chrome的驱动还需要先开放对远程主机的访问，否则只能访问本地链接：https://chromedriver.chromium.org/security-considerations，
     * 但亲测并不需要，无视即可！
     * 
     * 
     * @author DragonBoom
     * @since 2020.06.09
     */
    @Test
    @Disabled
    void officialDocumentTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Proxy proxy = new Proxy();
//        proxy.setAutodetect(true);// 只能自动设置非SSL的代理。。。
        proxy.setHttpProxy("127.0.0.1:10809");// HTTP 代理
        proxy.setSslProxy("127.0.0.1:10809");// HTTPS 代理
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
    
    private String pixivUsername = ClassPathProperties.getProperty("/account.properties", "pixiv-username");
    private String pixivPassword = ClassPathProperties.getProperty("/account.properties", "pixiv-password");
    
    /**
     * pixiv 实战测试：
     * 
     * <p>注意：对于不是id等绝对的定位，一定要查出集合！
     * 
     * <p>验证码出现后，再多次访问登陆页面，突然发现貌似被ban了，暂时搁置
     * 
     * @author DragonBoom
     * @throws InterruptedException 
     * @since 2020.09.09
     */
    @Test
//    @Disabled
    void pixivLoginTest() throws InterruptedException {
        String url = "https://accounts.pixiv.net/login?return_to=https%3A%2F%2Fwww.pixiv.net%2F&lang=zh&source=pc&view_type=page";
        ChromeOptions chromeOptions = new ChromeOptions();
        Proxy proxy = new Proxy();
//        proxy.setAutodetect(true);// 只能自动设置非SSL的代理。。。
        proxy.setHttpProxy("127.0.0.1:10809");// HTTP代理
        proxy.setSslProxy("127.0.0.1:10809");// HTTPS代理
        chromeOptions.setProxy(proxy);
        // 设置启动参数
        // a. 设置远程访问白名单。弃用-不需要
        
        ChromeDriver driver = new ChromeDriver(chromeOptions);// 没有实现Autocloseable接口
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get(url);
        try {
            System.out.println("title: " + driver.getTitle());
            // 获取可根据ID定位的登陆表单
            WebElement loginContainer = driver.findElement(By.cssSelector("#container-login"));
            // 有时会切换登陆表单（可能是登陆次数过多导致）
            WebElement loginContainer2 = driver.findElement(By.cssSelector("#LoginComponent"));
            
            // 从表单内获取输入项
            WebElement username = CollectionUtils.getSingle(loginContainer.findElements(By.cssSelector("input[type=text]")));
            if (checkPosition(username, false)) {
                username = CollectionUtils.getSingle(loginContainer2.findElements(By.cssSelector("input[type=text]")));
            }
            checkPosition(username, true);
            WebElement pwd = CollectionUtils.getSingle(loginContainer.findElements(By.cssSelector("input[type=password]")));
            if (checkPosition(pwd, true)) {
                pwd = CollectionUtils.getSingle(loginContainer2.findElements(By.cssSelector("input[type=password]")));
            }
            checkPosition(pwd, true);
            TestUtils.holdUntil(11160000);
            // 获取登陆按钮
            // 有两个，无法分辨，只能通过location判断
            WebElement submit = CollectionUtils.getSingle(driver.findElements(By.cssSelector(".signup-form__submit")).stream()
                    .filter(e -> e.getLocation().getX() != 0).collect(Collectors.toList()));
            checkPosition(submit, true);
            // 输入账号密码
            username.click();
            username.sendKeys(pixivUsername);
            username.click();
            pwd.sendKeys(pixivPassword);
            // 人机验证
            WebElement recaptcha = driver.findElement(By.cssSelector(".recaptcha-checkbox-border"));
            if (checkPosition(recaptcha, false)) {
                // 手工输入 
                log.info("需要手工输入验证码！");
            } else {
                // 点击登陆
                submit.click();
                log.info("登陆成功");
            }
            
            // 等待页面加载
            while (driver.getCurrentUrl().contains("accounts")) {
                System.out.println(driver.getCurrentUrl());
                Thread.sleep(500);
            }
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#container-login")));
            
            
            // 获取cookie
            Cookie cookie = driver.manage().getCookieNamed("PHPSESSID");
            System.out.println(cookie);
            
            TestUtils.holdUntil(60000);
            System.out.println("wake up");
//            pwdEle.click();
//            pwdEle..sendKeys("pixivPassword");
            
//            driver.findElementsByClassName("input-field");
//            driver.findElement(By.className("input").tagName(name)).sendKeys("cheese" + Keys.ENTER);
//            System.out.println(firstResult.getAttribute("textContent"));
            
        } finally {
            driver.quit();
        }
    }
    
    private static final Point NOT_FOUND_POINT = new Point(0, 0);
    
    private boolean checkPosition(WebElement ele, boolean needThrow) {
        Objects.requireNonNull(ele);
        boolean exists = !NOT_FOUND_POINT.equals(ele.getLocation());
        if (needThrow) {
            throw new IllegalArgumentException("无法定位WebElement: " + ele);
        }
        return exists;
    }
    

}
