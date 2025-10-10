package com.hj.leetcode.kotlin.problem3147

/**
 * LeetCode page: [3147. Taking Maximum Energy From the Mystic Dungeon](https://leetcode.com/problems/taking-maximum-energy-from-the-mystic-dungeon/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(k) where N is the length of
    // energy.
    fun maximumEnergy(
        energy: IntArray,
        k: Int,
    ): Int {
        // Split the energy into k independent sequences and
        // find the maximum suffix sum among them.
        val seqSum = IntArray(k)
        var result = energy.last()
        for (i in energy.indices.reversed() step k) {
            for (j in 0..<minOf(i + 1, k)) {
                seqSum[j] += energy[i - j]
                result = maxOf(result, seqSum[j])
            }
        }
        return result
    }
}
