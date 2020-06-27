package indi.net.sf.jsonlib;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.google.common.hash.Hashing;

import indi.util.extension.TestSeparateExtension;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@ExtendWith(TestSeparateExtension.class)
class JsonLibTest {
    
    @Test
    @Disabled
    void jsonArrayIteratorTest() {
        JSONArray jsonArray = JSONArray.fromObject(jsonArrayStr);
        Iterator iterator = jsonArray.iterator();
        
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
            System.out.println(next.getClass());
        }
    }
    
    String jsonArrayStr = "[{\"title\":\"现场照片\",\"param\":\"person\",\"count\":1,\"range\":\"201,202,203,204,205,206,207,208,209,210,211,212,213\",\"photoIds\":\"215\"},{\"title\":\"《机动车牌证申请表》\",\"param\":\"application\",\"count\":1,\"range\":\"201,202,204,205,206,207,208,210\",\"photoIds\":\"216\"},{\"title\":\"社会信用代码证\",\"param\":\"socialCode\",\"count\":1,\"ownerIdType\":1,\"photoIds\":\"218\"},{\"title\":\"代理人身份证（正面）\",\"param\":\"proxyIdCardFront\",\"count\":1,\"ownerIdType\":1,\"photoIds\":\"218\"},{\"title\":\"代理人身份证（反面）\",\"param\":\"proxyIdCardBack\",\"count\":1,\"ownerIdType\":1,\"photoIds\":\"217\"},{\"title\":\"单位授权书（加盖公章）\",\"param\":\"companyProxyPaper\",\"count\":1,\"ownerIdType\":1,\"photoIds\":\"217\"},{\"title\":\"行驶证（正面）\",\"param\":\"runLicenceFront\",\"count\":1,\"range\":\"201,202,205,207,208,210\",\"photoIds\":\"219\"},{\"title\":\"行驶证（反面）\",\"param\":\"runLicenceBack\",\"count\":1,\"range\":\"201,202,205,207,208,210\",\"photoIds\":\"220\"},{\"title\":\"交通事故责任强制保险凭证\",\"param\":\"accidentPaper\",\"count\":1,\"range\":\"207,208,210\",\"photoIds\":\"221\"},{\"title\":\"车船税纳税或者免税证明\",\"param\":\"taxPaper\",\"count\":1,\"range\":\"210\",\"photoIds\":\"222\"},{\"title\":\"委托授权书\",\"param\":\"proxyPaper\",\"count\":1,\"range\":\"201,202,204,205,206,207,208,210\",\"photoIds\":\"216\"}]";
    
    @Test
    @Disabled
    void jsonGetNullTest() {
        JSONObject jsonObject = JSONObject.fromObject("{\"fff\": \"ww\"}");
        Object obj = jsonObject.get("ffffff");
        
        System.out.println(obj);// pring: null
        System.out.println("is nulll ? " + (obj == null));// print: is nulll ? true
        String str = jsonObject.getString("ffffff");// throw net.sf.json.JSONException: JSONObject["ffffff"] not found.
        
        System.out.println(str);
        System.out.println("is nulll ? " + (str == null));// print: is nulll ? true
    }
    
    String requestBody = "{"
            + "      \"barCode\": \"123\",\r\n" + 
            "      \"code\":\"CG201906232100187\"" +

            "}";

    @Test
    void signTest() throws UnsupportedEncodingException, ParseException {
        JSONObject fromObject = JSONObject.fromObject(requestBody);
        Set<Entry<String, Object>> entrySet = fromObject.entrySet();
        System.out.println(entrySet);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse("2019-10-16 16:16:48");
        
        String signTime = new SimpleDateFormat("yyyyMMddHHmm").format(date);
        fromObject.put("signTime", signTime);
        
        String result = entrySet.stream()
                .map(e -> {
                    String sign = e.getKey() + ":" + e.getValue();
                    
                    return sign;
                })
                .sorted()
                .collect(Collectors.joining(":"));
        result = "ylj2017:" + result;
        
        
        
        result += ":ylj2017";
        
        // 实际结果：yljapp:idNo:440583199608184232:idType:A:signTime:201909301355:yljapp
        // 预期结果：ylj2017:idNo:440583199608184232:idType:A:signTime:201909301355:ylj2017
        System.out.println(result);
        System.out.println(DigestUtils.md5Hex(result));
    }

}
