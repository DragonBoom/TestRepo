package indi.util;

import java.util.Date;

import indi.fasterxml.jackson.ObjectMapperTest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TestObjects {

    @Data
    public static class Plain1ArgObj {
        private String arg1;
    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StringPropClass {
        String id;
        String name;
    }
    
    @Data
    public static class IntegerPropClass {
        Integer id;
    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DatePropClass {
        Date date;
    }
}
