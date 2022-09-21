package com.hj.leetcode.kotlin.problem1696

/**
 * LeetCode page: [1696. Jump Game VI](https://leetcode.com/problems/jump-game-vi/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun maxResult(nums: IntArray, k: Int): Int {
        val maxScorePerIndex = IntArray(nums.size)
        val bestJumpIndexPq = CustomQueue(k) { index -> maxScorePerIndex[index] }

        maxScorePerIndex[0] = nums[0]
        bestJumpIndexPq.offer(0)

        for (index in 1..nums.lastIndex) {
            val bestJumpIndex = bestJumpIndexPq.poll(index)
            maxScorePerIndex[index] = nums[index] + maxScorePerIndex[bestJumpIndex]
            bestJumpIndexPq.offer(index)
        }

        return maxScorePerIndex.last()
    }

    private class CustomQueue(
        private val jumpLength: Int,
        private val readMaxScore: (index: Int) -> Int
    ) {
        private val queue = ArrayDeque<Int>()
        private var lastIndex = -1

        fun offer(newIndex: Int) {
            require(newIndex == lastIndex + 1) { "Current state is valid for index ${lastIndex + 1} only." }
            lastIndex++
            when {
                queue.isEmpty() -> updateCaseEmpty(newIndex)
                isNewBest(newIndex) -> updateCaseNewBest(newIndex)
                else -> updateCaseNotBest(newIndex)
            }
        }

        private fun updateCaseEmpty(newIndex: Int) {
            queue.add(newIndex)
        }

        private fun isNewBest(newIndex: Int) = readMaxScore(newIndex) >= readMaxScore(queue.first())

        private fun updateCaseNewBest(newIndex: Int) {
            queue.clear()
            queue.add(newIndex)
        }

        private fun updateCaseNotBest(newIndex: Int) {
            val maxScore = readMaxScore(newIndex)
            while (readMaxScore(queue.last()) <= maxScore) {
                queue.removeLast()
            }
            queue.addLast(newIndex)
        }

        fun poll(currIndex: Int): Int {
            require(currIndex == lastIndex + 1) { "Current state is valid for index ${lastIndex + 1} only." }
            popFirstUntilValid(currIndex)
            return queue.first()
        }

        private fun popFirstUntilValid(currIndex: Int) {
            while (queue.first() < currIndex - jumpLength) {
                queue.removeFirst()
            }
        }
    }
}