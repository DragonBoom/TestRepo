package indi.lombok;

import org.junit.jupiter.api.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class LombokBuilderTest {

    @Test
    void go() {
        
    }
    
    @Getter
    @Setter
    @ToString
    @Builder
    public static class MyClass {
        @Builder.Default
        private String name = "default_value";
        private String ocde;
    }
}
