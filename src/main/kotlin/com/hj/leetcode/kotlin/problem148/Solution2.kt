package com.hj.leetcode.kotlin.problem148

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [148. Sort List](https://leetcode.com/problems/sort-list/);
 */
class Solution2 {
    /* Complexity:
     * Time O(NLogN) and Space O(1) where N is the number of nodes in head;
     */
    fun sortList(head: ListNode?): ListNode? {
        val dummyHead = ListNode(-1).also { it.next = head }
        var currSplitSize = 1
        var mergeSortNotDone = true

        while (mergeSortNotDone) {
            val numSubListPairs =
                performSubMergeSortAndReturnNumSubListPairs(dummyHead, currSplitSize)

            mergeSortNotDone = numSubListPairs > 1
            currSplitSize = currSplitSize shl 1
        }

        return dummyHead.next
    }

    private fun performSubMergeSortAndReturnNumSubListPairs(
        listWithDummyHead: ListNode,
        splitSize: Int
    ): Int {
        var numSubListPairs = 0
        var currTail = listWithDummyHead
        var headOfNextSubListPair = currTail.next

        while (headOfNextSubListPair != null) {
            val head1 = headOfNextSubListPair
            val tail1 = head1.getNthNode(splitSize)

            val head2 = tail1?.next
            val tail2 = head2.getNthNode(splitSize)

            headOfNextSubListPair = tail2?.next
            tail1?.next = null
            tail2?.next = null

            currTail = addMergedSortedToTailAndReturnNewTail(head1, head2, currTail)
            numSubListPairs++
        }
        return numSubListPairs
    }

    private fun ListNode?.getNthNode(n: Int): ListNode? {
        require(n > 0) { "Require n to start at 1, i.e. head is the first node." }

        var currNode = this
        var count = 1
        while (count < n && currNode != null) {
            currNode = currNode.next
            count++
        }
        return currNode
    }

    private fun addMergedSortedToTailAndReturnNewTail(
        sorted1: ListNode?,
        sorted2: ListNode?,
        tail: ListNode
    ): ListNode {
        var currTail = tail
        var ptr1 = sorted1
        var ptr2 = sorted2

        while (ptr1 != null && ptr2 != null) {
            if (ptr1.`val` < ptr2.`val`) {
                currTail.next = ptr1
                ptr1 = ptr1.next
            } else {
                currTail.next = ptr2
                ptr2 = ptr2.next
            }

            currTail = checkNotNull(currTail.next)
        }

        currTail.next = ptr1 ?: ptr2
        return currTail.last()
    }

    private fun ListNode.last(): ListNode {
        var currNode = this

        while (currNode.next != null) {
            currNode = checkNotNull(currNode.next)
        }
        return currNode
    }
}