/**
 * 
 */
package indi.algorithm.tree;

import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

/**
 * @author wzh
 * @since 2021.06.18
 */
@ExtendWith(TestSeparateExtension.class)
public class RedBlackTreeTest {
    
    /**
     * <p>红黑树的插入：原理是用二叉树与红黑标记实现2-3树，由下至上地平衡深度
     * 
     * <p>需要返回格式化后的父结点，使得发生调整后，上级结点能将链指向调整后的结点（单向）
     * 
     * <p>关于递归的逻辑：06.23前的实现并不够好，之前递归时，只处理黑结点，即递归处理的区间仅为整个2-或3-区间，但其实可以逐个结点
     * 递归处理：通过抽象出旋转、变色操作，共只分三种情况；其中的左旋转，可同时作用于2-与3-结点
     * 
     * <p>06.23 修改为《算法》中的简洁的代码
     * 
     * @param <T>
     * @param in
     * @param n
     * @param np 
     * @since 2021.06.22
     */
    <T extends Comparable<T>> BNode<T> putV(T key, Object val, BNode<T> n) {
        if (n == null) {
            BNode<T> in = new BNode<>();
            in.key = key;
            in.val = val;
            in.red = true;// 假设插入结点为红结点，这样若插入的是2-的左侧则不必处理（相当于2-变3-）
            return in;// 插入结点，将在上一个递归方法中赋给父结点；懒加载，否则结点已存在时会浪费
        }
        int cmp = key.compareTo(n.key);
        if (cmp > 0) {
            n.right = putV(key, val, n.right);
        } else if (cmp < 0) {
            n.left = putV(key, val, n.left);
        } else {
            n.val = val;
        }
        // 分情况处理：只需分三种情况；需依次进行；
        // a. 在2-或3-子的右侧插入
        // 2-旋转后变3-
        // 3-子旋转后变b情况
        if (isRed(n.right) && !isRed(n.left)) {
            // 此时n可能红也可能黑
            n = rotateLeft(n);
        }
        // b. 在3-子结点的左侧插入
        // 注意，这里操作的结点，之前已经处理过了（若存在 ）
        // 旋转后变为c情况
        if (n.left != null && isRed(n.left) && isRed(n.left.left)) {
            // 此时n可能红也可能黑
            n = rotateRight(n);
        }
        // c. 在3-父结点的右侧插入
        if (isRed(n.left) && isRed(n.right)) {
            flipColors(n);
        }
        // d. 其他情况无需处理（插入2-左侧）
        return n;
    }
    
    /** 用于简化对null值的处理 */
    <T extends Comparable<T>> boolean isRed(BNode<T> node) {
        return node == null ? false : node.red;
    }
    
    /*
     * 以下三种操作，都不会符合二叉树的左小右大的要求
     */
    /** 方法名学了《算法》，应该是“翻转颜色”的意思 */
    <T extends Comparable<T>> BNode<T> flipColors(BNode<T> n) {
        n.red = true;
        n.left.red = n.right.red = false;// 没必要这样写
        return n;
    }
    
    // 无需校验
    <T extends Comparable<T>> BNode<T> rotateLeft(BNode<T> n) {
        BNode<T> nr = n.right;
        n.right = nr.left;
        nr.left = n;
        nr.red = n.red;// 理论上n.red未知 FIXME:sure?
        n.red = true;// 理论上nr.red = true，否则也不需要处理 FIXME:sure?
        return nr;
    }
    
    <T extends Comparable<T>> BNode<T> rotateRight(BNode<T> n) {
        BNode<T> nl = n.left;
        n.left = nl.right;
        nl.right = n;
        nl.red = n.red;
        n.red = true;
        return nl;
    }
    
}
