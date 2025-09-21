package com.hj.leetcode.kotlin.problem1912

import java.util.*

class Solution

/**
 * LeetCode page: [1912. Design Movie Rental System](https://leetcode.com/problems/design-movie-rental-system/);
 */
class MovieRentingSystem(
    n: Int,
    entries: Array<IntArray>,
) {
    private val smpHashes = hashMapOf<Long, Long>() // hash(shop,movie) to hash(shop,movie,price)
    private val rented = TreeSet<Long>() // TreeSet of hash(shop,movie,price)
    private val unrented = hashMapOf<Int, TreeSet<Long>>() // movie to TreeSet of hash(shop,movie,price)

    // Complexity:
    // Time O(MLogM) and Space O(M) where M is the length of
    // entries.
    init {
        for ((shop, movie, price) in entries) {
            val smpHash = hashSmp(shop, movie, price)
            smpHashes[hashSm(shop, movie)] = smpHash
            unrented.computeIfAbsent(movie) { TreeSet() }.add(smpHash)
        }
    }

    private fun hashSmp(
        shop: Int,
        movie: Int,
        price: Int,
    ): Long = (price.toLong() shl 36) or (shop.toLong() shl 16) or movie.toLong()

    private fun invertHashSmp(hash: Long): IntArray {
        val price = (hash shr 36).toInt()
        val shop = ((hash shr 16) and 0xF_FFFF).toInt()
        val movie = (hash and 0xFFFF).toInt()
        return intArrayOf(shop, movie, price)
    }

    private fun hashSm(
        shop: Int,
        movie: Int,
    ): Long = (shop.toLong() shl 16) or movie.toLong()

    // Complexity:
    // Time O(LogM) and Space O(1) where M is the length of
    // entries.
    fun search(movie: Int): List<Int> =
        buildList {
            val iter = unrented[movie]?.iterator() ?: return emptyList()
            while (size < 5 && iter.hasNext()) {
                val (shop, _, _) = invertHashSmp(iter.next())
                add(shop)
            }
        }

    // Complexity:
    // Time O(LogM) and Space O(1) where M is the length of
    // entries.
    fun rent(
        shop: Int,
        movie: Int,
    ) {
        val smpHash = checkNotNull(smpHashes[hashSm(shop, movie)])
        checkNotNull(unrented[movie]).remove(smpHash)
        rented.add(smpHash)
    }

    // Complexity:
    // Time O(LogM) and Space O(1) where M is the length of
    // entries.
    fun drop(
        shop: Int,
        movie: Int,
    ) {
        val smpHash = checkNotNull(smpHashes[hashSm(shop, movie)])
        rented.remove(smpHash)
        checkNotNull(unrented[movie]).add(smpHash)
    }

    // Complexity:
    // Time O(LogM) and Space O(1) where M is the length of
    // entries.
    fun report(): List<List<Int>> =
        buildList {
            val iter = rented.iterator()
            while (size < 5 && iter.hasNext()) {
                val (shop, movie, _) = invertHashSmp(iter.next())
                add(listOf(shop, movie))
            }
        }
}

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * var obj = MovieRentingSystem(n, entries)
 * var param_1 = obj.search(movie)
 * obj.rent(shop,movie)
 * obj.drop(shop,movie)
 * var param_4 = obj.report()
 */
