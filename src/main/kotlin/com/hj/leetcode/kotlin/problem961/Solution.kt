package com.hj.leetcode.kotlin.problem961

import kotlin.random.Random

/**
 * LeetCode page: [961. N-Repeated Element in Size 2N Array](https://leetcode.com/problems/n-repeated-element-in-size-2n-array/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    //
    // Sieve with step 1.
    fun repeatedNTimes(nums: IntArray): Int {
        for (i in nums.indices step 2) {
            if (nums[i] == nums[i + 1]) {
                return nums[i]
            }
        }

        return if (nums[1] == nums[3]) nums[1] else nums[0]
    }

    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    //
    // A step of 2 improves the worst case over a step of 1, though
    // its impact on common cases is unverified.
    fun repeatedNTimesByStep2Sieve(nums: IntArray): Int {
        for (i in nums.indices step 2) {
            if (nums[i] == nums[i + 1]) {
                return nums[i]
            }
        }

        return if (
            nums[0] == nums[2] || nums[0] == nums[3]
        ) {
            nums[0]
        } else {
            nums[1]
        }
    }

    // Complexity:
    // Expected Time O(rand) and Space O(rand).
    fun repeatedNTimesByRandom(nums: IntArray): Int {
        while (true) {
            val i = Random.nextInt(nums.size)
            val j = Random.nextInt(nums.size)

            if (i != j && nums[i] == nums[j]) {
                return nums[i]
            }
        }
    }

    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    //
    // Always compare with nums[0] to make the duplicated number a
    // majority in nums[1:] and apply the voting logic.
    fun repeatedNTimesByVoter(nums: IntArray): Int {
        for (i in 1..<nums.size) {
            if (nums[i] == nums[0] || nums[i] == nums[i - 1]) {
                return nums[i]
            }
        }
        return nums[1]
    }

    // Complexity:
    // Time O(NLogM) and Space O(M) where M is 14 for the current
    // constraints.
    //
    // Always compare with nums[0] to make the duplicated number a
    // majority in nums[1:] and find duplicated number by bit
    // counts.
    fun repeatedNTimesByBitCount(nums: IntArray): Int {
        // max possible nums[i] <= 10^4 < 2^14
        val bitCount = IntArray(14)

        for (i in 1..<nums.size) {
            if (nums[i] == nums[0]) {
                return nums[0]
            }

            for (shift in bitCount.indices) {
                if (nums[i] and (1 shl shift) > 0) {
                    bitCount[shift]++
                }
            }
        }

        val half = nums.size / 2
        return bitCount.foldIndexed(0) { shift, acc, count ->
            if (count >= half) acc + (1 shl shift) else acc
        }
    }

    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun repeatedNTimesBySet(nums: IntArray): Int {
        val seen = hashSetOf<Int>()
        for (num in nums) {
            if (num in seen) {
                return num
            } else {
                seen.add(num)
            }
        }

        throw IllegalStateException(
            "Unreachable as duplication exists.",
        )
    }
}
