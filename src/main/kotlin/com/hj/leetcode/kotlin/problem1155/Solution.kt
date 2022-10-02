package com.hj.leetcode.kotlin.problem1155

/**
 * LeetCode page: [1155. Number of Dice Rolls With Target Sum](https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/);
 *
 * TODO : There is solution with space complexity O(1) ([see REF](https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/discuss/1159705));
 */
class Solution {
    private val mod = 1_000_000_007

    /* Complexity:
     * Time O(n * target) and Space O(target);
     */
    fun numRollsToTarget(n: Int, k: Int, target: Int): Int {
        val maxSum = n * k

        val isImpossible = target > maxSum || target < n
        if (isImpossible) return 0

        val hasOnlyOneWay = target == n || target == maxSum
        if (hasOnlyOneWay) return 1

        val equivalentTarget = getEquivalentTarget(n, maxSum, target)
        val numWaysPerSum = getContainerForNumWaysPerSum(n, equivalentTarget)

        updateForFirstDice(numWaysPerSum, k)
        updateForRemainingDices(numWaysPerSum, k, n - 1)
        return numWaysPerSum.last()
    }

    private fun getEquivalentTarget(minSum: Int, maxSum: Int, target: Int): Int {
        val symmetricTarget = maxSum - (target - minSum)
        return minOf(target, symmetricTarget)
    }

    private fun getContainerForNumWaysPerSum(minSum: Int, equivalentTarget: Int): IntArray {
        val reducedSize = equivalentTarget + 1 - minSum
        return IntArray(reducedSize)
    }

    private fun updateForFirstDice(container: IntArray, numFaces: Int) {
        val activeLastIndex = getActiveLastIndex(1, numFaces, container.lastIndex)

        for (index in 0..activeLastIndex) {
            container[index] = 1
        }
    }

    private fun getActiveLastIndex(minSum: Int, maxSum: Int, containerLastIndex: Int): Int {
        val rangeSize = maxSum - minSum + 1
        val isRangeSizeEven = rangeSize and 1 == 0
        val indexAtSymmetricStart = if (isRangeSizeEven) rangeSize shr 1 else 1 + (rangeSize shr 1)
        return minOf(indexAtSymmetricStart - 1, containerLastIndex)
    }

    private fun updateForRemainingDices(container: IntArray, numFaces: Int, numRemainingDices: Int) {
        repeat(numRemainingDices) { count ->
            val currNumDices = count + 1
            val nextNumDices = currNumDices + 1
            val nextMaxSum = nextNumDices * numFaces
            val nextActiveLastIndex = getActiveLastIndex(nextNumDices, nextMaxSum, container.lastIndex)

            fillSymmetricBeforeUpdateNewDice(container, currNumDices, numFaces, nextActiveLastIndex)
            updateForNewDice(container, numFaces, nextActiveLastIndex)
        }
    }

    private fun fillSymmetricBeforeUpdateNewDice(
        container: IntArray,
        currNumDice: Int,
        numFaces: Int,
        nextEffectiveLastIndex: Int
    ) {
        val currMaxSum = currNumDice * numFaces
        val currActiveLastIndex = getActiveLastIndex(currNumDice, currMaxSum, container.lastIndex)

        for (index in currActiveLastIndex + 1..nextEffectiveLastIndex) {
            val symmetricIndex = currMaxSum - index - currNumDice
            container[index] = container[symmetricIndex]
        }
    }

    private fun updateForNewDice(container: IntArray, numFaces: Int, newEffectiveLastIndex: Int) {
        var windowEndIndex = newEffectiveLastIndex
        val windowSumOfEnd = getWindowSum(container, numFaces, windowEndIndex)

        var prevWindowEndValue = container[windowEndIndex]
        container[windowEndIndex] = windowSumOfEnd

        while (windowEndIndex > 1) {
            windowEndIndex--
            val windowEndValue = container[windowEndIndex]
            val windowStartIndex = windowEndIndex - numFaces + 1

            val diffToPrevWindowSum = -prevWindowEndValue + container.getOrElse(windowStartIndex) { 0 }
            container[windowEndIndex] = (container[windowEndIndex + 1] + diffToPrevWindowSum) % mod
            if (container[windowEndIndex] < 0) container[windowEndIndex] += mod

            prevWindowEndValue = windowEndValue
        }
    }

    private fun getWindowSum(container: IntArray, windowSize: Int, windowEndIndex: Int): Int {
        var windowSum = 0
        val windowStartIndex = windowEndIndex - windowSize + 1

        for (index in windowStartIndex..windowEndIndex) {
            windowSum += container.getOrElse(index) { 0 }
            windowSum %= mod
        }
        return windowSum
    }
}