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
        val l1Stack = Stack(l1)
        val l2Stack = Stack(l2)
        var result: ListNode? = null
        var carry = 0
        repeat(maxOf(l1Stack.size, l2Stack.size)) {
            val sumDigits = (carry
                    + (l1Stack.removeLastOrNull()?.`val` ?: 0)
                    + (l2Stack.removeLastOrNull()?.`val` ?: 0))
            val newDigit = if (sumDigits < 10) sumDigits else sumDigits - 10
            result = result.prepended(newDigit)
            carry = if (sumDigits >= 10) 1 else 0
        }
        if (carry == 1) {
            result = result.prepended(1)
        }
        return result
    }

    private fun Stack(listNode: ListNode?): MutableList<ListNode> {
        val result = mutableListOf<ListNode>()
        var currentNode = listNode
        while (currentNode != null) {
            result.add(currentNode)
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