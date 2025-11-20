package com.hj.leetcode.kotlin.problem2154

class Solution2 {
    // Complexity:
    // Time O(N) and Space O(LogM) where N is the length of nums
    // and M is the upper bound of values.
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
