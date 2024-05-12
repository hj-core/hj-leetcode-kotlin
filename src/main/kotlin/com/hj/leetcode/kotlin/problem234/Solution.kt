package com.hj.leetcode.kotlin.problem234

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [234. Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of head;
     */
    fun isPalindrome(head: ListNode?): Boolean {
        return head.toList().isPalindrome()
    }

    private fun ListNode?.toList(): List<Int> = buildList {
        var nodePtr = this@toList

        while (nodePtr != null) {
            add(nodePtr.`val`)
            nodePtr = nodePtr.next
        }
    }

    private fun List<Int>.isPalindrome(): Boolean {
        var left = 0
        var right = lastIndex

        while (left <= right) {
            if (this[left] != this[right]) {
                return false
            }
            left += 1
            right -= 1
        }
        return true
    }
}