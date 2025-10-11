package com.hj.leetcode.kotlin.problem3186

/**
 * LeetCode page: [3186. Maximum Total Damage With Spell Casting](https://leetcode.com/problems/maximum-total-damage-with-spell-casting/);
 */
class Solution2 {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of
    // power.
    //
    // # Caveats
    // This function may modify the input array.
    fun maximumTotalDamage(power: IntArray): Long {
        val sortedPower =
            if (power.size < 4096) {
                power.sort()
                power
            } else {
                radixSort(power, 30) // power[i] <= 10^9 < 2^30
            }

        // We work on a virtual sorted (power, freq) array,
        // and the DP is based on whether to take the new pair.
        // We only keep the four most recent sub results.
        val dp = LongArray(4)
        val dpPower = IntArray(4) { -3 }
        var size = 4 // index of the new power
        var freq = 1L // frequency of the new power

        for (i in 0..<sortedPower.size) {
            if (i < sortedPower.lastIndex && sortedPower[i] == sortedPower[i + 1]) {
                freq++
                continue
            }

            var j = size - 1
            while (dpPower[j and 3] + 2 >= sortedPower[i]) {
                j--
            }

            dp[size and 3] =
                maxOf(
                    dp[(size - 1) and 3],
                    freq * sortedPower[i] + dp[j and 3],
                )

            dpPower[size and 3] = sortedPower[i]
            size++
            freq = 1
        }

        return dp[(size - 1) and 3]
    }

    private fun radixSort(
        arr: IntArray,
        maxWidth: Int,
    ): IntArray {
        val shift = 8
        val mask = (1 shl shift) - 1

        val freq = Array((maxWidth + shift - 1) / shift) { IntArray(mask + 1) }
        for (num in arr) {
            for (i in freq.indices) {
                freq[i][num shr (i * shift) and mask]++
            }
        }

        var result = arr
        var tmpArr = IntArray(arr.size)

        for (i in freq.indices) {
            if (freq[i][0] == arr.size) {
                continue
            }

            freq[i][mask] = arr.size - freq[i][mask]
            for (j in mask - 1 downTo 0) {
                freq[i][j] = freq[i][j + 1] - freq[i][j]
            }

            result = tmpArr.also { tmpArr = result }
            for (num in tmpArr) {
                val pos = num shr (i * shift) and mask
                result[freq[i][pos]] = num
                freq[i][pos]++
            }
        }

        return result
    }
}
