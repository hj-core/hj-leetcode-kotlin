package com.hj.leetcode.kotlin.problem3714

/**
 * LeetCode page: [3714. Longest Balanced Substring II](https://leetcode.com/problems/longest-balanced-substring-ii/);
 */
class Solution {
    // Complexity:
    // Time O(2^C * C * N) and Space O(2^C * C * N) where N is the
    // length of s and C is the size of charset (i.e., 3).
    fun longestBalanced(s: String): Int {
        var longest = longestSingleChar(s)

        // We use 1, 2, 4 to indicate whether char a, b, c is present in
        // the substring. We exclude the 1, 2, 4 which are the single
        // char cases.
        val targetMasks = intArrayOf(3, 5, 6, 7)
        // The anchor cIdx for each target mask
        val anchors = intArrayOf(0, 0, 1, 0)
        // freq_{a, b, c}
        val freq = intArrayOf(0, 0, 0)

        // key =
        //   56            54            36           18           0
        //   | target_mask |   r_freq_c  |  r_freq_b  |  r_freq_a  |
        //
        // value = minimum prefix
        val bestPrefix = hashMapOf<PackedFreq, Int>()
        for (presentMask in targetMasks) {
            bestPrefix[PackedFreq.new(presentMask)] = -1
        }

        for ((i, c) in s.withIndex()) {
            val cIdx = c - 'a'
            freq[cIdx]++

            for ((k, presentMask) in targetMasks.withIndex()) {
                val anchor = anchors[k]

                var packed = PackedFreq.new(presentMask)
                for (j in 0..<3) {
                    val relativeFreq =
                        if (presentMask and (1 shl j) != 0) {
                            // j appears in our target mask; relate its
                            // freq to anchor.
                            freq[j] - freq[anchor]
                        } else {
                            // j does not appear in our target mask;
                            // remove all its occurrences.
                            freq[j]
                        }
                    packed = packed.withFreq(relativeFreq, j)
                }

                if (bestPrefix.containsKey(packed)) {
                    longest = maxOf(longest, i - bestPrefix[packed]!!)
                } else {
                    bestPrefix[packed] = i
                }
            }
        }

        return longest
    }

    private fun longestSingleChar(s: String): Int {
        var longest = 1
        var runLength = 1
        for (i in 1..<s.length) {
            if (s[i] == s[i - 1]) {
                runLength++
                longest = maxOf(longest, runLength)
            } else {
                runLength = 1
            }
        }

        return longest
    }

    @JvmInline
    private value class PackedFreq(
        val value: Long,
    ) {
        fun withFreq(
            freq: Int,
            cIdx: Int,
        ): PackedFreq =
            PackedFreq(
                (freq and 0x3_ffff).toLong() shl (cIdx * 18) or value,
            )

        companion object {
            fun new(presentMask: Int): PackedFreq =
                PackedFreq(presentMask.toLong() shl 54)
        }
    }
}
