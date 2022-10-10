package com.hj.leetcode.kotlin.problem229

/**
 * LeetCode page: [229. Majority Element II](https://leetcode.com/problems/majority-element-ii/);
 */
class Solution {

    private val k = 3 // Divisor of size of nums describing the threshold to be considered as majority;

    /* Complexity:
     * Time O(N) and Space O(k) where N is the size of nums. For this problem k = 3 thus space complexity
     * is actually O(1);
     */
    fun majorityElement(nums: IntArray): List<Int> {
        val candidates = findCandidatesForMajorityElements(nums)
        return findMajorityElements(candidates, nums)
    }

    private fun findCandidatesForMajorityElements(nums: IntArray): List<Int> {
        val countPerCandidates = hashMapOf<Int, Int>()

        for (num in nums) {
            countPerCandidates[num] = countPerCandidates.getOrDefault(num, 0) + 1
            reduceCandidatesIfTotalReachesK(countPerCandidates)
        }

        return countPerCandidates.keys.toList()
    }

    private fun reduceCandidatesIfTotalReachesK(countPerCandidates: MutableMap<Int, Int>) {
        if (countPerCandidates.size == k) {
            val iterator = countPerCandidates.iterator()

            while (iterator.hasNext()) {
                val (candidate, count) = iterator.next()
                val newCount = count - 1
                if (newCount == 0) iterator.remove() else countPerCandidates[candidate] = newCount
            }
        }
    }

    private fun findMajorityElements(candidates: List<Int>, nums: IntArray): List<Int> {
        val threshold = nums.size / k

        return candidates.filter { candidate ->
            val count = nums.count { num -> num == candidate }
            count > threshold
        }
    }
}