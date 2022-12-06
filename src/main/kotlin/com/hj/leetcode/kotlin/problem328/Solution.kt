package com.hj.leetcode.kotlin.problem328

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [328. Odd Even Linked List](https://leetcode.com/problems/odd-even-linked-list/description/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun oddEvenList(head: ListNode?): ListNode? {
        var currOdd = head
        val headOfEven = head?.next
        var currEven = headOfEven

        while (currEven?.next != null) {
            currOdd?.next = currEven.next
            currOdd = currOdd?.next
            currEven.next = currOdd?.next
            currEven = currEven.next
        }
        currOdd?.next = headOfEven
        return head
    }
}