package day4

import util.FileUtils

private const val inputName = "day4_input.txt"

private fun toPair(s: String): Pair<Int, Int> {
    val parts = s.split("-")
    return Pair(parts[0].toInt(), parts[1].toInt())
}

// https://adventofcode.com/2022/day/4
fun partOne() {
    val lines = FileUtils.readLines(inputName)
    var pairsNeedingReconsideration = 0

    lines.forEach {
        val pairs = it.split(",")
        val first = toPair(pairs[0])
        val second = toPair(pairs[1])

        if (first.contains(second) || second.contains(first)) pairsNeedingReconsideration++
    }

    println("Pairs needing reconsideration: $pairsNeedingReconsideration")
}

// https://adventofcode.com/2022/day/4#part2
fun partTwo() {
    val lines = FileUtils.readLines(inputName)
    var pairsNeedingReconsideration = 0

    lines.forEach {
        val pairs = it.split(",")
        val first = toPair(pairs[0])
        val second = toPair(pairs[1])

        if (first.overlaps(second) || second.overlaps(first)) pairsNeedingReconsideration++
    }

    println("Pairs needing reconsideration: $pairsNeedingReconsideration")
}

fun main() {
    partOne()
    partTwo()
}

fun Pair<Int, Int>.contains(other: Pair<Int, Int>): Boolean {
    return this.first <= other.first && this.second >= other.second
}

fun Pair<Int, Int>.overlaps(other: Pair<Int, Int>): Boolean {
    return this.first <= other.first && other.first <= this.second
}