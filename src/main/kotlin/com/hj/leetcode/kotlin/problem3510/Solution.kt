package com.hj.leetcode.kotlin.problem3510

import java.util.PriorityQueue

/**
 * LeetCode page: [3510. Minimum Pair Removal to Sort Array II](https://leetcode.com/problems/minimum-pair-removal-to-sort-array-ii/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N), where N is the length of nums.
    fun minimumPairRemoval(nums: IntArray): Int {
        var violations =
            (0..<nums.size - 1).count { nums[it] > nums[it + 1] }
        if (violations == 0) {
            return 0
        }

        val pq =
            PriorityQueue(
                compareBy(
                    ListNode::pairSum,
                    ListNode::arrIndex,
                ),
            )
        addAllNodes(pq, nums)

        var minRemoval = 0
        while (violations > 0) {
            val curr = checkNotNull(pq.poll())
            if (curr.isStaled) {
                continue
            }

            minRemoval++
            var prev = checkNotNull(curr.prev)
            val prevPrev = checkNotNull(prev.prev)
            var next = checkNotNull(curr.next)
            val nextNext = checkNotNull(next.next)
            curr.prev = null
            curr.next = null

            if (prev.arrValue > curr.arrValue) {
                violations--
            }
            if (curr.arrValue > next.arrValue) {
                violations--
            }
            if (next.arrValue > nextNext.arrValue) {
                violations--
            }

            if (prev.arrIndex != -1) {
                prev.isStaled = true
                prev.prev = null
                prev.next = null

                prev = prev.copy()
                prev.pairSum += next.arrValue
                prev.prev = prevPrev
                prev.isStaled = false
                prevPrev.next = prev
                pq.offer(prev)
            }

            if (next.arrIndex != nums.size - 1) {
                next.isStaled = true
                next.prev = null
                next.next = null

                next = next.copy()
                next.arrValue += curr.arrValue
                next.pairSum += curr.arrValue
                next.next = nextNext
                next.isStaled = false
                nextNext.prev = next
                pq.offer(next)
            } else {
                next.arrValue += curr.arrValue
            }

            prev.next = next
            next.prev = prev

            if (prev.arrValue > next.arrValue) {
                violations++
            }
            if (next.arrValue > nextNext.arrValue) {
                violations++
            }
        }

        return minRemoval
    }

    private fun addAllNodes(
        pq: PriorityQueue<ListNode>,
        nums: IntArray,
    ) {
        require(nums.size > 1)

        val dummy = ListNode(-1, Long.MIN_VALUE, 0, null, null, false)
        dummy.prev = dummy

        var curr =
            ListNode(
                0,
                nums[0].toLong(),
                nums[0].toLong(),
                dummy,
                null,
                false,
            )
        curr.next = curr
        dummy.next = curr

        for (i in 0..<nums.size - 1) {
            val next =
                ListNode(
                    i + 1,
                    nums[i + 1].toLong(),
                    nums[i + 1].toLong(),
                    curr,
                    null,
                    false,
                )
            next.next = next

            curr.pairSum += next.arrValue
            curr.next = next

            pq.offer(curr)
            curr = next
        }
    }
}

private class ListNode(
    val arrIndex: Int,
    var arrValue: Long,
    var pairSum: Long,
    var prev: ListNode?,
    var next: ListNode?,
    var isStaled: Boolean,
) {
    fun copy(): ListNode =
        ListNode(arrIndex, arrValue, pairSum, prev, next, isStaled)
}
