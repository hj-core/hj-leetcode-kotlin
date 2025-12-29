package com.hj.leetcode.kotlin.problem756

/**
 * LeetCode page: [756. Pyramid Transition Matrix](https://leetcode.com/problems/pyramid-transition-matrix/);
 */
class Solution {
    // Complexity:
    // Time O(M+6^N) and Space O(6^N) where N is the length of
    // bottom and M is the length of allowed.
    fun pyramidTransition(
        bottom: String,
        allowed: List<String>,
    ): Boolean =
        canBuild(
            newPyramid(bottom),
            bottom.length - 2,
            0,
            groupWidth2Bottoms(allowed),
            HashSet(),
        )

    // Creates a new pyramid representing the initial state.
    private fun newPyramid(bottom: String): IntArray {
        val n = bottom.length
        val pyramid = IntArray(n)
        pyramid[n - 1] = encodeBottom(bottom)
        return pyramid
    }

    // Encodes each letter of bottom into 3-bit sequences. A–F map
    // to 1–6, with later indices occupying higher-order bits.
    private fun encodeBottom(bottom: String): Int =
        bottom.foldIndexed(0) { index, acc, ch ->
            (ch.code and 7) shl (index * 3) or acc
        }

    // groupWidth2Bottom groups the valid three-blocks by their
    // two-block bottoms and uses a bitmask to indicate the
    // permitted tops.
    private fun groupWidth2Bottoms(
        allowed: List<String>,
    ): IntArray {
        val result = IntArray(6 shl 3 + 6 + 1)
        for (threeBlock in allowed) {
            val bottom = encodeBottom(threeBlock.substring(0, 2))
            val top = threeBlock[2].code and 7
            result[bottom] = 1 shl top or result[bottom]
        }
        return result
    }

    private fun canBuild(
        pyramid: IntArray,
        currLevel: Int,
        currIndex: Int,
        // key= bottom, val= bitmask of permitted tops
        width2Bottom: IntArray,
        cannotBuild: HashSet<Int>, // val= bottom
    ): Boolean {
        if (currLevel == 0) {
            return width2Bottom[pyramid[1]] != 0
        }

        if (currIndex > currLevel) {
            if (pyramid[currLevel] in cannotBuild) {
                return false
            }

            if (canBuild(
                    pyramid,
                    currLevel - 1,
                    0,
                    width2Bottom,
                    cannotBuild,
                )
            ) {
                return true
            } else {
                cannotBuild.add(pyramid[currLevel])
                return false
            }
        }

        // The block left of the current one
        val left =
            if (currIndex == 0) {
                0
            } else {
                pyramid[currLevel] shr ((currIndex - 1) * 3) and 7
            }

        // The two blocks supporting the current block
        val directBottom =
            pyramid[currLevel + 1] shr (currIndex * 3) and 0x3f

        val topMask = width2Bottom[directBottom]

        for (block in 1..<7) {
            if (topMask shr block and 1 == 0) {
                continue
            }

            // Check compatibility with the previous block
            if (left > 0 &&
                width2Bottom[block shl 3 or left] == 0
            ) {
                continue
            }

            pyramid[currLevel] =
                pyramid[currLevel] xor (block shl (currIndex * 3))

            if (canBuild(
                    pyramid,
                    currLevel,
                    currIndex + 1,
                    width2Bottom,
                    cannotBuild,
                )
            ) {
                return true
            }

            pyramid[currLevel] =
                pyramid[currLevel] xor (block shl (currIndex * 3))
        }

        return false
    }
}
