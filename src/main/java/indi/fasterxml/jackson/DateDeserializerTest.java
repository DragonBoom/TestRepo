package indi.fasterxml.jackson;

import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DateDeserializerTest {

    /**
     * 测试jackson不能反序列化'yyy-MM-dd hh-mm-ss'时间的问题
     * @throws IOException 
     */
    @Test
    void go() throws IOException {
        PassCardPrintThreeDTO dto = new PassCardPrintThreeDTO();
        dto.setCreateTime(new Date());
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(dto));
        // 接下来这段代码会抛出异常
        String json = "{\"itemNo\":null,\"passApplyId\":null,\"applyUnitName\":null,\"createTime\":\"2018-08-09 10:22:33\",\"startTime\":null,\"endTime\":null,\"vehicleList\":null}\r\n";
        System.out.println(mapper.readValue(json, dto.getClass()));
    }
}
