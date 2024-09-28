package com.hj.leetcode.kotlin.problem2807

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [2807. Insert Greatest Common Divisors in Linked List](https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/);
 */
class Solution {
    /* Complexity:
     * Time O(LogM) and Space O(1) where M is the product of all node values
     * in head.
     */
    fun insertGreatestCommonDivisors(head: ListNode?): ListNode? {
        var node = head
        while (node?.next != null) {
            val gcd = gcd(node.`val`, checkNotNull(node.next).`val`)
            val newNode = ListNode(gcd).apply { next = checkNotNull(node).next }
            node.next = newNode
            node = newNode.next
        }
        return head
    }

    private tailrec fun gcd(
        a: Int,
        b: Int,
    ): Int {
        require(a >= 0 && b >= 0)
        return if (a == 0) b else gcd(b % a, a)
    }
}
