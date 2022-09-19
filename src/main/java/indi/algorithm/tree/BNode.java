/**
 * 
 */
package indi.algorithm.tree;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 
 * 为方便访问，成员变量都用默认修饰符;BNode = BinaryNode，但实际可当作单向使用
 * 
 * @author wzh
 * @since 2021.06.18
 */
@AllArgsConstructor
@NoArgsConstructor
class BNode<T extends Comparable<T>> {
    BNode<T> parent;
    BNode<T> left;
    BNode<T> right;
    /** for red-black tree */
    boolean red;
    T key;
    Object val;
}
