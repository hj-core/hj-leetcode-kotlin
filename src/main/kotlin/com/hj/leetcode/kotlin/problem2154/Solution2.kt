package com.hj.leetcode.kotlin.problem2154

class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun findFinalValue(
        nums: IntArray,
        original: Int,
    ): Int {
        val targets = mutableSetOf(original)
        var target = original
        while (target <= 1000) {
            target *= 2
            targets.add(target)
        }

        for (num in nums) {
            targets.remove(num)
        }
        return targets.first()
    }
}
