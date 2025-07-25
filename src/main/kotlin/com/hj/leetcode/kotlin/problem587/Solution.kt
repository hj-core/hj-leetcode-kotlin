package com.hj.leetcode.kotlin.problem587

/**
 * LeetCode page: [587. Erect the Fence](https://leetcode.com/problems/erect-the-fence/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of trees.
    fun outerTrees(trees: Array<IntArray>): Array<IntArray> {
        // We solve the problem with Monotone chain algorithm.
        // [Convex hull algorithms](https://en.wikipedia.org/wiki/Convex_hull_algorithms)

        // Sort trees by x-coordinate; break ties with larger
        // y-coordinate.
        val sortedTrees =
            trees.sortedArrayWith { t1, t2 ->
                (t1[0] - t2[0]).let { if (it == 0) t1[1] - t2[1] else it }
            }

        return collectFenceTrees(
            sortedTrees,
            findUpperFence(sortedTrees),
            findLowerFence(sortedTrees),
        )
    }

    // Returns the tree indices of upper half fence. sortedTrees
    // should be sorted by x-coordinate and break ties with larger
    // y-coordinates.
    private fun findUpperFence(sortedTrees: Array<out IntArray>): List<Int> {
        val result = mutableListOf<Int>()
        for (i in sortedTrees.indices) {
            while (result.size >= 2 &&
                cmpSlope(
                    sortedTrees[result[result.size - 1]],
                    sortedTrees[result[result.size - 2]],
                    sortedTrees[i],
                ) < 0
            ) {
                result.removeLast()
            }
            result.add(i)
        }
        return result
    }

    // Returns an integer whose sign indicates whether the slope
    // from start to end1 is larger than the slope from start to
    // end2. start should not be vertically aligned with either end.
    private fun cmpSlope(
        start: IntArray,
        end1: IntArray,
        end2: IntArray,
    ): Int {
        val dy1 = start[1] - end1[1]
        val dx1 = start[0] - end1[0]
        val dy2 = start[1] - end2[1]
        val dx2 = start[0] - end2[0]
        return dy1 * dx2 - dy2 * dx1
    }

    // Returns the tree indices of lower half fence. sortedTrees
    // should be sorted by x-coordinate and break ties with larger
    // y-coordinates.
    private fun findLowerFence(sortedTrees: Array<out IntArray>): List<Int> {
        val result = mutableListOf<Int>()
        for (j in sortedTrees.indices.reversed()) {
            while (result.size >= 2 &&
                cmpSlope(
                    sortedTrees[result[result.size - 1]],
                    sortedTrees[result[result.size - 2]],
                    sortedTrees[j],
                ) < 0
            ) {
                result.removeLast()
            }
            result.add(j)
        }
        return result
    }

    // Collects the fence trees given the tree indices in the
    // upper fence and the lower fence.
    private fun collectFenceTrees(
        sortedTrees: Array<out IntArray>,
        upperFence: List<Int>,
        lowerFence: List<Int>,
    ): Array<IntArray> {
        val isFence = BooleanArray(sortedTrees.size)
        val result = mutableListOf<IntArray>()

        for (index in upperFence) {
            if (!isFence[index]) {
                result.add(sortedTrees[index])
            }
            isFence[index] = true
        }
        for (index in lowerFence) {
            if (!isFence[index]) {
                result.add(sortedTrees[index])
            }
            isFence[index] = true
        }
        return result.toTypedArray()
    }
}
