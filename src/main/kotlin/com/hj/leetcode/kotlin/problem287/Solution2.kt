package com.hj.leetcode.kotlin.problem287

/**
 * LeetCode page: [287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun findDuplicate(nums: IntArray): Int {
        /* Construct a graph with N+1 nodes, i.e., node 0, 1, ..., N,
         * and each (index, value) pair of nums represents a direct-edge
         * from node index to node value.
         *
         * The resulting graph contains a linked list starting from node 0
         * and ends with a cycle whose entrance is the node duplicated.
         */

        var slow = nums[0]
        var fast = nums[nums[0]]

        while (slow != fast) {
            slow = nums[slow]
            fast = nums[nums[fast]]
        }

        var result = 0
        while (result != slow) {
            result = nums[result]
            slow = nums[slow]
        }
        return result
    }
}