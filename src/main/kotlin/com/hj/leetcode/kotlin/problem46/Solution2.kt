package com.hj.leetcode.kotlin.problem46

/**
 * LeetCode page: [46. Permutations](https://leetcode.com/problems/permutations/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N * N!) and Space O(N * N!) where N is the size of nums;
     */
    fun permute(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        permute(ArrayDeque(nums.toList())) { permutation ->
            result.add(permutation)
        }
        return result
    }

    private fun permute(
        remaining: ArrayDeque<Int>,
        current: MutableList<Int> = mutableListOf(),
        onEachPermutation: (permutation: List<Int>) -> Unit
    ) {
        if (remaining.isEmpty()) {
            onEachPermutation(current.toList())
            return
        }

        repeat(remaining.size) {
            val newElement = remaining.removeFirst()
            current.add(newElement)

            permute(remaining, current, onEachPermutation)
            current.apply { removeAt(lastIndex) }
            remaining.addLast(newElement)
        }
    }
}