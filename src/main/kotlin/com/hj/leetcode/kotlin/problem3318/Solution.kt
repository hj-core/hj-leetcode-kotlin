package com.hj.leetcode.kotlin.problem3318

import java.util.*

/**
 * LeetCode page: [3318. Find X-Sum of All K-Long Subarrays I](https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/);
 */
class Solution {
    // Complexity:
    // Time O(NLogk) and Space O(k) where N is the length of nums.
    fun findXSum(
        nums: IntArray,
        k: Int,
        x: Int,
    ): IntArray {
        val numFreq = IntArray(nums.max() + 1)

        // We use a size-k sliding window. setL contains the x
        // largest numbers in the window according to the rule.
        // setR contains the remaining numbers in the window.
        val setL = TreeSet(compareBy(numFreq::get, { it }))
        var sumL = 0
        val setR = TreeSet(compareBy(numFreq::get, { it }))

        // Solve the result for nums[0..<k]
        for (i in 0..<k) {
            numFreq[nums[i]]++
        }
        for (i in 0..<k) {
            val num = nums[i]
            if (num !in setL) {
                setL.add(num)
                sumL += num * numFreq[num]
            }
        }
        while (setL.size > x) {
            val num = checkNotNull(setL.pollFirst())
            sumL -= num * numFreq[num]
            setR.add(num)
        }

        val result = IntArray(nums.size - k + 1)
        result[0] = sumL

        for (i in k..<nums.size) {
            val newNum = nums[i]
            if (newNum in setL) {
                setL.remove(newNum)
                numFreq[newNum]++
                setL.add(newNum)
                sumL += newNum
            } else {
                setR.remove(newNum)
                numFreq[newNum]++
                setR.add(newNum)
            }

            val popNum = nums[i - k]
            if (popNum in setL) {
                setL.remove(popNum)
                numFreq[popNum]--
                sumL -= popNum
                if (numFreq[popNum] > 0) {
                    setL.add(popNum)
                }
            } else {
                setR.remove(popNum)
                numFreq[popNum]--
                if (numFreq[popNum] > 0) {
                    setR.add(popNum)
                }
            }

            if (setL.size < x && setR.isNotEmpty()) {
                val num = checkNotNull(setR.pollLast())
                setL.add(num)
                sumL += num * numFreq[num]
            }

            if (setR.isNotEmpty() &&
                isSmaller(
                    setL.first(),
                    setR.last(),
                    numFreq,
                )
            ) {
                val numL = checkNotNull(setL.pollFirst())
                sumL -= numL * numFreq[numL]
                setR.add(numL)

                val numR = checkNotNull(setR.pollLast())
                setL.add(numR)
                sumL += numR * numFreq[numR]
            }

            result[i - k + 1] = sumL
        }

        return result
    }

    // Compares the two numbers according to the rule specified
    // in the problem.
    private fun isSmaller(
        num1: Int,
        num2: Int,
        numFreq: IntArray,
    ): Boolean =
        if (numFreq[num1] == numFreq[num2]) {
            num1 < num2
        } else {
            numFreq[num1] < numFreq[num2]
        }
}
