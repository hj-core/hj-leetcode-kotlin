package com.hj.leetcode.kotlin.problem46

/**
 * LeetCode page: [46. Permutations](https://leetcode.com/problems/permutations/);
 */
class Solution {
    /* Complexity:
     * Time O(N * N!) and Space O(N * N!) where N is the size of nums;
     */
    fun permute(nums: IntArray): List<List<Int>> {
        val permutes = mutableListOf(mutableListOf(nums[0]))

        for (index in 1..nums.lastIndex) {
            addNumberToEachPermute(nums[index], permutes)
            addRotationsOfEachPermute(permutes)
        }
        return permutes
    }

    private fun addNumberToEachPermute(number: Int, permutes: MutableList<MutableList<Int>>) {
        for (permute in permutes) {
            permute.add(number)
        }
    }

    private fun addRotationsOfEachPermute(permutes: MutableList<MutableList<Int>>) {
        repeat(permutes.size) { index ->
            addRotationsOfPermute(permutes[index], permutes)
        }
    }

    private fun addRotationsOfPermute(permute: List<Int>, container: MutableList<MutableList<Int>>) {
        val greatestShift = permute.size - 1

        for (shift in 1..greatestShift) {
            container.add(mutableListOf())

            for (index in shift..permute.lastIndex) {
                container.last().add(permute[index])
            }

            for (index in 0 until shift) {
                container.last().add(permute[index])
            }
        }
    }
}