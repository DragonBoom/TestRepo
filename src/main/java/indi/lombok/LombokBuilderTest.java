package indi.lombok;

import org.junit.jupiter.api.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class LombokBuilderTest {

    // 测试使用Lombok的@Builder时设置默认值
    @Test
    void defaultValueTest() {
        MyClass defaultObj = MyClass.builder().build();
        System.out.println(defaultObj);// 
        MyClass binaryObj = defaultObj.toBuilder().build();
        System.out.println(binaryObj);
        
    }
    
    @Getter
    @Setter
    @ToString
    @Builder(toBuilder = true// 生成toBuilder()方法，将对象转化为拥有默认值的构造器，
    )
    public static class MyClass {
        @Builder.ObtainVia(field = "ocde")// 配合@Builder(toBuilder=true)使用，设置所转化的构造器的默认值
        private String name;
        @Builder.Default// 声明有默认值（实际通过新增private的静态变量实现，而不是真的去获取该属性的值（该属性的修饰符可能会死Builder无法访问））
        @Builder.ObtainVia(method = "go")
        private final String ocde = "ocdeDefault";
        
        public String go() {
            return "method";
        }
    }
}
