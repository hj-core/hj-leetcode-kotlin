package com.hj.leetcode.kotlin.problem1436

/**
 * LeetCode page: [1436. Destination City](https://leetcode.com/problems/destination-city/);
 */
class Solution {
    /* Complexity:
     * Time O(P+S) and Space O(S) where P and S are the flattened
     * lengths of cityA's and cityB's (each path goes from a cityA
     * to a cityB);
     */
    fun destCity(paths: List<List<String>>): String {
        val candidates = hashSetOf<String>()
        for ((_, cityB) in paths) {
            candidates.add(cityB)
        }

        for ((cityA, _) in paths) {
            if (cityA in candidates) {
                candidates.remove(cityA)
            }
        }
        check(candidates.size == 1)
        return candidates.first()
    }
}