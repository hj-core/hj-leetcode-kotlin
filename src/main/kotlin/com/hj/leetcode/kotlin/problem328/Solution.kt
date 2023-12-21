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
        val headOfEven = head?.next
        var currOddNode = head
        var currEvenNode = headOfEven

        while (currEvenNode?.next != null) {
            currOddNode?.next = currEvenNode.next
            currOddNode = currOddNode?.next
            currEvenNode.next = currOddNode?.next
            currEvenNode = currEvenNode.next
        }
        currOddNode?.next = headOfEven
        return head
    }
}