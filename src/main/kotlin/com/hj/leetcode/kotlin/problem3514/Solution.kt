package com.hj.leetcode.kotlin.problem3514

/**
 * LeetCode page: [3514. Number of Unique XOR Triplets II](https://leetcode.com/problems/number-of-unique-xor-triplets-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N*(M+N)) and Space O(M) where N is the length of nums and
    // M is the maximum number of nums.
    //
    // TODO: [7 ms, magical algorithm: Walsh-Hadamard Transform](https://leetcode.com/problems/number-of-unique-xor-triplets-ii/solutions/8416110/7-ms-magical-algorithm-walsh-hadamard-tr-2h3m)
    fun uniqueXorTriplets(nums: IntArray): Int {
        val xorTuples = hashSetOf<Int>()
        xorTuples.add(0)
        for (i in nums.indices) {
            for (j in i + 1..<nums.size) {
                xorTuples.add(nums[i] xor nums[j])
            }
        }

        val xorTriplets = hashSetOf<Int>()
        for (tuple in xorTuples) {
            for (num in nums) {
                xorTriplets.add(num xor tuple)
            }
        }

        return xorTriplets.size
    }
}
