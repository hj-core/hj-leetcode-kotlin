package com.hj.leetcode.kotlin.problem218

import java.util.*

/**
 * LeetCode page: [218. The Skyline Problem](https://leetcode.com/problems/the-skyline-problem/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of buildings;
     */
    fun getSkyline(buildings: Array<IntArray>): List<List<Int>> {
        val walls = getWallsSortedByLocationThenSignedHeight(buildings)

        val skyline = mutableListOf<List<Int>>()
        val firstWall = walls.first()
        val firstSkylinePoint = listOf(firstWall.location, firstWall.unsignedHeight)
        skyline.add(firstSkylinePoint)

        val countPerActiveHeights = TreeMap<Int, Int>(reverseOrder<Int>())
        countPerActiveHeights[0] = 1 // Use to connect discrete building groups;

        for (wall in walls) {
            updateCountPerActiveHeights(wall, countPerActiveHeights)

            val maxActiveHeight: Int = countPerActiveHeights.firstKey()
            val heightOfLastSkylinePoint = skyline.last()[1]
            val isNewSkylinePoint = maxActiveHeight != heightOfLastSkylinePoint
            if (isNewSkylinePoint) {
                val newPoint = listOf(wall.location, maxActiveHeight)
                skyline.add(newPoint)
            }
        }
        return skyline
    }

    private fun getWallsSortedByLocationThenSignedHeight(buildings: Array<IntArray>): List<Wall> {
        val walls = mutableListOf<Wall>()

        for (building in buildings) {
            walls.add(leftWallOf(building))
            walls.add(rightWallOf(building))
        }

        walls.sortWith(compareBy({ it.location }, { it.signedHeight }))
        return walls
    }

    private data class Wall(val location: Int, val signedHeight: Int) {
        val unsignedHeight get() = Math.abs(signedHeight)
    }

    private fun leftWallOf(building: IntArray): Wall {
        val location = building[0]
        val signedHeight = building[2] * (-1)
        return Wall(location, signedHeight)
    }

    private fun rightWallOf(building: IntArray): Wall {
        val location = building[1]
        val signedHeight = building[2]
        return Wall(location, signedHeight)
    }

    private fun updateCountPerActiveHeights(currWall: Wall, counts: MutableMap<Int, Int>) {
        val unsignedHeight = currWall.unsignedHeight
        val currCount = counts[unsignedHeight] ?: 0
        val newCount = if (currWall.isLeft()) currCount + 1 else currCount - 1

        if (newCount == 0) {
            counts.remove(unsignedHeight)
        } else {
            counts[unsignedHeight] = newCount
        }
    }

    private fun Wall.isLeft() = signedHeight < 0
}