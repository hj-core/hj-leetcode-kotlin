package com.hj.leetcode.kotlin.problem445

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [445. Add Two Numbers II](https://leetcode.com/problems/add-two-numbers-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(M+N) and Space O(M+N) where M and N are the number of nodes in l1 and l2;
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val digits1 = l1.values()
        val digits2 = l2.values()
        var result: ListNode? = null
        var carry = 0
        repeat(maxOf(digits1.size, digits2.size)) {
            val sum = (carry
                    + (digits1.removeLastOrNull() ?: 0)
                    + (digits2.removeLastOrNull() ?: 0))
            val digit = if (sum < 10) sum else sum - 10
            result = result.prepended(digit)
            carry = if (sum >= 10) 1 else 0
        }
        if (carry == 1) {
            result = result.prepended(1)
        }
        return result
    }

    private fun ListNode?.values(): MutableList<Int> {
        val result = mutableListOf<Int>()
        var currentNode = this
        while (currentNode != null) {
            result.add(currentNode.`val`)
            currentNode = currentNode.next
        }
        return result
    }

    private fun <T> MutableList<T>.removeLastOrNull(): T? {
        return if (isEmpty()) null else removeAt(lastIndex)
    }

    private fun ListNode?.prepended(value: Int): ListNode {
        return ListNode(value).apply { next = this@prepended }
    }
}