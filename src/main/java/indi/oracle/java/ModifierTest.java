/**
 * 
 */
package indi.oracle.java;

import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;
import indi.util.Child;
import indi.util.Parent;

/**
 * 测试Java的修饰符
 * 
 * @author wzh
 * @since 2021.06.16
 */
@ExtendWith(TestSeparateExtension.class)
public class ModifierTest extends Child {

    /**
     * 测试protected修饰的方法是否不能被子类的子类访问；注意，本类是Child的子类
     * 
     * <p>答案：能
     * 
     * @since 2021.06.16
     */
    void protectedTest() {
        Child child = new Child();
        
        newChildProtectedMethod();
        
        // child.overridedProtectedMethod();// 无法编译
        // child.newChildProtectedMethod();// 无法编译
        super.newChildProtectedMethod();
        super.overridedProtectedMethod();
        // 访问静态protected方法：
        Child.newChildStaticProtectedMethod();// 无法编译
        Child.newParentStaticProtectedMethod();// 可继承静态方法！
        Parent.newParentStaticProtectedMethod();
    }   
    
}
