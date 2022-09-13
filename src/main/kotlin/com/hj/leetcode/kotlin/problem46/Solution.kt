package com.hj.leetcode.kotlin.problem46

/**
 * LeetCode page: [46. Permutations](https://leetcode.com/problems/permutations/);
 */
class Solution {
    /* Complexity:
     * Time O(N * N!) and Space O(N!) where N is the size of nums;
     */
    fun permute(nums: IntArray): List<List<Int>> {
        val permutes = mutableListOf(mutableListOf(nums[0]))

        for (index in 1..nums.lastIndex) {
            permutes.addNumberToEachPermute(nums[index])
            permutes.addRotationOfExistingPermutes()
        }
        return permutes
    }

    private fun MutableList<MutableList<Int>>.addNumberToEachPermute(newNumber: Int) {
        for (permute in this) {
            permute.add(newNumber)
        }
    }

    private fun MutableList<MutableList<Int>>.addRotationOfExistingPermutes() {
        repeat(this.size) { index ->
            this.addRotationOfPermute(index)
        }
    }

    private fun MutableList<MutableList<Int>>.addRotationOfPermute(permuteIndex: Int) {
        val permuteToRotate = this[permuteIndex]
        val greatestShift = permuteToRotate.size - 1
        for (shift in 1..greatestShift) {
            this.add(mutableListOf())
            for (index in shift..permuteToRotate.lastIndex) {
                this.last().add(permuteToRotate[index])
            }
            for (index in 0 until shift) {
                this.last().add(permuteToRotate[index])
            }
        }
    }
}