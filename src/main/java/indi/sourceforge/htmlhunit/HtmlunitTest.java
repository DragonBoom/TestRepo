/**
 * 
 */
package indi.sourceforge.htmlhunit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import indi.test.TestSeparateExtension;

/**
 * 官方文档地址：http://htmlunit.sourceforge.net/gettingStarted.html
 * 
 * <p>一款可模拟浏览器去执行js的工具，和selenium一样，主要用于自动化测试，也可用于爬虫；据说支持很复杂的js逻辑，但测试个别网站时出现了问题，
 * 拿来做爬虫的辅助估计还是不太现实
 * 
 * @author wzh
 * @since 2020.04.07
 * @see {@link http://htmlunit.sourceforge.net/gettingStarted.html}
 */
@ExtendWith(TestSeparateExtension.class)
class HtmlunitTest {

    @Test
    void pixivTest() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        // 以代理的形式启动WebClient
        try (final WebClient webClient = new WebClient(BrowserVersion.getDefault(), "127.0.0.1", 10809)) {
            // 设置
            WebClientOptions options = webClient.getOptions();
            options.setRedirectEnabled(true);
            options.setThrowExceptionOnScriptError(false);// js错误不抛异常
            
            System.out.println("是否使用cookie：" + webClient.getCookieManager().isCookiesEnabled());
            
            // 获取pixiv登陆页面
            // 该方法返回值类型是继承了Page的类
            HtmlPage loginPage = webClient.getPage(
                    "https://accounts.pixiv.net/login?return_to=https%3A%2F%2Fwww.pixiv.net%2F&lang=zh&source=pc&view_type=page");
            System.out.println(loginPage.getTitleText());
            // 获取登陆表单，先获取所有表单，再逐一排查
            List<HtmlForm> forms = loginPage.getForms();
            System.out.println(forms);
            System.out.println(forms.size());
            HtmlForm loginForm = null;
            for (HtmlForm htmlForm : forms) {
                System.out.println(htmlForm.asXml());
                if ("/login".equals(htmlForm.getActionAttribute())) {
                    loginForm = htmlForm;
                }
            }
            if (loginForm == null) {
                throw new RuntimeException("找不到登陆表单");
            }
            
//            经测试，发现浏览器类型为Chrome时找不到id=LoginComponent的div
//            List<DomElement> loginEles = loginPage.getElementsById("LoginComponent");
//            if (loginEles.size() != 1) {
//                throw new RuntimeException("找到" + loginEles.size() + "个登陆表单，无法准确匹配表单：" + loginEles);
//            }
//            DomElement loginDiv = loginEles.get(0);
            
            // 输入账号、密码
            DomNodeList<HtmlElement> inputs = loginForm.getElementsByTagName("input");
            for (HtmlElement ele : inputs) {
                System.out.println(ele);
                if ("邮箱地址/pixiv ID".equals(ele.getAttribute("placeholder"))) {
                    ele.type("1312449403@qq.com");
                    System.out.println(ele.asXml());
                }
                if ("密码".equals(ele.getAttribute("placeholder"))) {
                    ele.type("qq1312449403");
                    System.out.println(ele.asXml());
                }
            }
            
            // 点击登陆
            DomNodeList<HtmlElement> buttons = loginForm.getElementsByTagName("button");
            if (buttons.size() != 1) {
                throw new RuntimeException("找到的登录按钮数量不为1：" + buttons.size());
            }
            HtmlElement submitButton = buttons.get(0);
            System.out.println(submitButton.asXml());
            
            HtmlPage clickedPage = submitButton.click();// 返回的还是登陆页面
            
            System.out.println(webClient.getCurrentWindow());
            
            
            System.out.println("登陆完成！");
            
            // 测试登陆是否成功：
            // a. 访问收藏夹，测试登陆时候成功 2020.04.07 报错
            // TypeError: Cannot instantiate an arrow function (https://s.pximg.net/www/js/bundle/vendors~pixiv~stacc3.c6ea04be6943136aba8f.js#3)
//            HtmlPage bookmarkPage = webClient.getPage("https://www.pixiv.net/bookmark.php");
//            System.out.println(bookmarkPage);
//            System.out.println(bookmarkPage.toString().contains("DragonBoom"));
            
//            // b. 测试直接访问API，调用失败，返回{"error":true,"message":"An unknown error occurred","body":[]}，应该是登录失败了
            Page page = webClient.getPage("https://www.pixiv.net/ajax/user/10127376/illusts/bookmarks?tag=&offset=10&limit=10&rest=show");
            System.out.println(page);
        }
    }
    
    @Test
    @Disabled
    void pornhubTest() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        // 以代理的形式启动WebClient
        try (final WebClient webClient = new WebClient(BrowserVersion.getDefault(), "127.0.0.1", 10809)) {
            // 设置
            WebClientOptions options = webClient.getOptions();
            options.setRedirectEnabled(true);
            options.setThrowExceptionOnScriptError(false);// js错误不抛异常
            
            // 2020.04.07 这里会报错：missing ) in parenthetical
            HtmlPage page = webClient.getPage("https://cn.pornhub.com/login");
            
            // 输入账号密码
            ((HtmlElement) page.getElementById("username")).type("qq1312449403");
            ((HtmlElement) page.getElementById("password")).type("1312449403");
            // 点击登陆
            ((HtmlElement) page.getElementById("submit")).click();
            
            // 测试登陆是否成功（通过访问编辑页面）
            HtmlPage editPage = webClient.getPage("https://cn.pornhub.com/user/edit");
            
            System.out.println(editPage.asXml().contains("1312449403"));
            
        }
    }
}
