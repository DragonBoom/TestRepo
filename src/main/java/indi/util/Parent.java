/**
 * 
 */
package indi.util;

/**
 * @author wzh
 * @since 2021.06.16
 */
public class Parent {

    /** Child将重写该方法 */
    protected void overridedProtectedMethod() {}
    /** Child不重写该方法 */
    protected void notOverridedProtectedMethod() {}
    protected static void newParentStaticProtectedMethod() {};
}
