/**
 * 
 */
package indi.openfeign;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import feign.Client.Proxied;
import feign.Feign;
import feign.Logger.Level;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;
import indi.test.TestSeparateExtension;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wzh
 * @since 2020.06.09
 */
@ExtendWith(TestSeparateExtension.class)
@Slf4j
class FeignTest {

    /**
     * 基于Github项目文档的测试方法： https://github.com/OpenFeign/feign
     * 
     * @author DragonBoom
     * @since 2020.06.09
     */
    @Test
    void officialDocumentTest() {
        // 设置代理
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10809));
        // 构建代理：
        GitHub github = Feign.builder().client(new Proxied(null, null, proxy))// 设置代理（相当麻烦。。。）
//                .options(Options)// 设置超时时间等
                // 默认实现类为 NoOpLogger，即默认不记日志；若要引入Slf4j，可引入相应模块；默认为DEBUG级别
                // 也可以自行实现，默认只需实现一个抽象方法，实际上Slf4jLogger的实现就非常简单
                .logger(new Slf4jLogger())
                .logLevel(Level.FULL)
                // 需要另外引入相应模块才能获得JacksonDecoder
                // 建议查看实现类的具体实现或自定义一个Decoder，JacksonDecoder里面内置了一个ObjectMapper，需要小心使用
                .decoder(new JacksonDecoder())
                .target(GitHub.class, "https://api.github.com");

        // 自行代理方法：
        // Fetch and print a list of the contributors to this library.
        List<Contributor> contributors = github.contributors("OpenFeign", "feign");
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }

    }

    interface GitHub {
        @RequestLine("GET /repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);

        @RequestLine("POST /repos/{owner}/{repo}/issues")
        void createIssue(Issue issue, @Param("owner") String owner, @Param("repo") String repo);

    }

    @Data
    public static class Contributor implements Serializable {
        private static final long serialVersionUID = 1L;

        private String login;
        private int contributions;
    }

    @Data
    public static class Issue implements Serializable {
        private static final long serialVersionUID = 1L;

        private String title;
        private String body;
        private List<String> assignees;
        private int milestone;
        private List<String> labels;
    }

}
