package indi.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;
import lombok.EqualsAndHashCode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 * 
 * <p>You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 * <pre>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 * </pre>
 * 
 * @author Think
 *
 */
@ExtendWith(TestSeparateExtension.class)
class AddTwoNumbers {
    
    private final ListNode EMPTY_NODE = new ListNode(0);
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 在读链表的同时相加
        boolean isCarry = false;
        
        
        /**
         * 计算结果继续使用l1即可
         */
        ListNode result = l1;// save l1
        
        ListNode lastNode = null;
        
        while (l1 != null) {
            int curSum = l1.val + (l2 == null ? 0 : l2.val);
            if (isCarry) {
                curSum++;// 进位
            }
            if (curSum >= 10) {
                l1.val = curSum % 10;
                isCarry = true;
            } else {
                l1.val = curSum;
                isCarry = false;
            }
            
            // 若l1, l2长度不一
            // 需要确保l1.next必不为空
            if (l1.next == null && l2 != null && l2.next != null) {
                l1.next = l2.next;
                l2.next = null;
            }
            
            lastNode = l1;
            
            l1 = l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (isCarry) {// 为最后一次循环进位
            lastNode.next = new ListNode(1);
            isCarry = false;
        }
        return result;
    }
    
    @EqualsAndHashCode
    public static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }
    
    @Test
    void go() {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(6);
        
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        
        ListNode r = new ListNode(7);
        r.next = new ListNode(0);
        r.next.next = new ListNode(1);
        r.next.next.next = new ListNode(1);// 测试 进位导致增加链表深度
        
        Assertions.assertEquals(r, addTwoNumbers(l1, l2));
    }
    
}
