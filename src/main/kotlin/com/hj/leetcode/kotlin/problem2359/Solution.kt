package com.hj.leetcode.kotlin.problem2359

/**
 * LeetCode page: [2359. Find Closest Node to Given Two Nodes](https://leetcode.com/problems/find-closest-node-to-given-two-nodes/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of edges.
    fun closestMeetingNode(
        edges: IntArray,
        node1: Int,
        node2: Int,
    ): Int {
        val n = edges.size

        var curr1 = node1
        val seen1 = BooleanArray(n)
        seen1[curr1] = true

        var curr2 = node2
        val seen2 = BooleanArray(n)
        seen2[curr2] = true

        while (curr1 != -1 && curr2 != -1) {
            when {
                seen1[curr2] && seen2[curr1] -> return minOf(curr1, curr2)
                seen1[curr2] -> return curr2
                seen2[curr1] -> return curr1
            }
            curr1 = getNextAndUpdateSeen(curr1, edges, seen1)
            curr2 = getNextAndUpdateSeen(curr2, edges, seen2)
        }

        while (curr1 != -1) {
            if (seen2[curr1]) {
                return curr1
            }
            curr1 = getNextAndUpdateSeen(curr1, edges, seen1)
        }
        while (curr2 != -1) {
            if (seen1[curr2]) {
                return curr2
            }
            curr2 = getNextAndUpdateSeen(curr2, edges, seen2)
        }
        return -1
    }

    //  `getNextAndUpdateSeen` returns the next node from `curr` and marks it
    //  as seen. If a cycle is detected, it returns -1 instead, as if there is
    //  no next node.
    private fun getNextAndUpdateSeen(
        curr: Int,
        edges: IntArray,
        seen: BooleanArray,
    ): Int {
        var result = edges[curr]
        if (result != -1) {
            if (seen[result]) {
                result = -1
            } else {
                seen[result] = true
            }
        }
        return result
    }
}
