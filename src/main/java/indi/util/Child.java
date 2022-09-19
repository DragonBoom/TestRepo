/**
 * 
 */
package indi.util;

/**
 * @author wzh
 * @since 2021.06.16
 */
public class Child extends Parent {

    @Override
    protected void overridedProtectedMethod() {}
    
    protected void newChildProtectedMethod() {}
    
    protected static void newChildStaticProtectedMethod() {
        Parent.newParentStaticProtectedMethod();
    };
}
