package com.hj.leetcode.kotlin.problem23

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/);
 */
class Solution2 {
    /* Complexity:
     * Time O(NLogK) and Space O(LogK) where N is the total nodes of lists and K is the size of lists;
     */
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        return mergeSorted(lists)
    }

    private fun mergeSorted(
        listOfSorted: Array<ListNode?>,
        fromIndex: Int = 0,
        toIndex: Int = listOfSorted.lastIndex
    ): ListNode? {

        if (fromIndex > toIndex) return null
        if (fromIndex == toIndex) return listOfSorted[fromIndex]

        val midIndex = (fromIndex + toIndex) ushr 1
        val sorted1 = mergeSorted(listOfSorted, fromIndex, midIndex)
        val sorted2 = mergeSorted(listOfSorted, midIndex + 1, toIndex)
        return mergeSorted(sorted1, sorted2)
    }

    private fun mergeSorted(sorted1: ListNode?, sorted2: ListNode?): ListNode? {
        val dummyHead = ListNode(-1)
        var currTail = dummyHead
        var currNode1 = sorted1
        var currNode2 = sorted2

        while (currNode1 != null && currNode2 != null) {
            if (currNode1.`val` < currNode2.`val`) {
                currTail.next = currNode1
                currNode1 = currNode1.next
            } else {
                currTail.next = currNode2
                currNode2 = currNode2.next
            }
            currTail = checkNotNull(currTail.next)
        }

        if (currNode1 != null) currTail.next = currNode1
        if (currNode2 != null) currTail.next = currNode2
        return dummyHead.next
    }
}