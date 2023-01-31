package com.hj.leetcode.kotlin.problem1626

/**
 * LeetCode page: [1626. Best Team With No Conflicts](https://leetcode.com/problems/best-team-with-no-conflicts/);
 *
 * TODO : There is a solution which make use of Fenwick Tree (see [Ref.](https://leetcode.com/problems/best-team-with-no-conflicts/solution/));
 */
class Solution {
    /* Algorithm:
     * 1. Build a list of player P from scores and ages;
     * 2. Sort P by age and break tie by score, both in ascending orders;
     * 3. Apply dynamic programming:
     * a) Sub-Problem: X(i), the max overall score includes player i for sub-array of P from index 0 to index i.
     *    Array Dp stores the result of each sub-problem;
     * b) Relation: X(i) = maxOf({P[i]+Dp[j] | j = 0 until i and P[j] <= P[i]} U {P[i]});
     * c) Topological order: Solve X(i) from 0 to P.lastIndex;
     * d) Base Case: Dp[0] = P[0];
     * e) Original Problem: Max value in Dp;
     * f) Time Complexity: P.size (i.e. number of sub-problems) * P.size (i.e. complexity of each sub-problem),
     *    thus (P.size)^2;
     */

    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the number of players, i.e. size of scores and ages;
     */
    fun bestTeamScore(scores: IntArray, ages: IntArray): Int {
        val sortedPlayers = buildPlayers(scores, ages).apply { sortByAgeThenScore() }
        return buildDp(sortedPlayers).max()!!
    }

    private fun buildPlayers(scores: IntArray, ages: IntArray): MutableList<Player> {
        return MutableList(scores.size) { Player(scores[it], ages[it]) }
    }

    private data class Player(val score: Int, val age: Int)

    private fun MutableList<Player>.sortByAgeThenScore() {
        val comparator = compareBy<Player>({ it.age }, { it.score })
        sortWith(comparator)
    }

    private fun buildDp(sortedPlayers: List<Player>): IntArray {
        val dp = IntArray(sortedPlayers.size)
        dp[0] = sortedPlayers[0].score // Base case

        for (i in 1 until sortedPlayers.size) {
            var subResult = sortedPlayers[i].score
            for (j in 0 until i) {
                if (sortedPlayers[j].score <= sortedPlayers[i].score) {
                    subResult = maxOf(subResult, sortedPlayers[i].score + dp[j])
                }
            }
            dp[i] = subResult
        }
        return dp
    }
}