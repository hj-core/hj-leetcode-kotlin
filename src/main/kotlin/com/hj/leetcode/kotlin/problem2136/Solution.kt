package com.hj.leetcode.kotlin.problem2136

/**
 * LeetCode page: [2136. Earliest Possible Day of Full Bloom](https://leetcode.com/problems/earliest-possible-day-of-full-bloom/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of plantTime and growTime;
     */
    fun earliestFullBloom(plantTime: IntArray, growTime: IntArray): Int {
        val sortedSeeds = getSeedsSortedByDescendingGrowTime(plantTime, growTime)
        var plantDoneTime = 0
        var latestBloomTime = 0

        for (seed in sortedSeeds) {
            plantDoneTime += seed.plantTime

            val newSeedBloomTime = plantDoneTime + seed.growTime
            latestBloomTime = maxOf(latestBloomTime, newSeedBloomTime)
        }

        return latestBloomTime
    }

    private fun getSeedsSortedByDescendingGrowTime(plantTime: IntArray, growTime: IntArray): List<Seed> {
        val seeds = MutableList(plantTime.size) { index -> Seed(plantTime[index], growTime[index]) }
        seeds.sortByDescending { it.growTime }
        return seeds
    }

    private class Seed(val plantTime: Int, val growTime: Int)
}