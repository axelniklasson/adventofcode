package day3

import util.FileUtils

private const val inputName = "day3_input.txt"
private const val lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz"
private val upperCaseLetters = lowerCaseLetters.uppercase()

private fun getPriorityForItem(char: Char): Int {
    return if (char.isLowerCase()) lowerCaseLetters.indexOf(char) + 1 else upperCaseLetters.indexOf(char) + 1 + lowerCaseLetters.length
}

private fun getCommonItem(compartments: Pair<String, String>): Char {
    val seen = HashMap<Char, Boolean>()
    compartments.first.toCharArray().forEach() { seen[it] = true }

    compartments.second.toCharArray().forEach {
        if (seen.containsKey(it)) return it
    }

    throw IllegalStateException("Could not find a common item in $compartments")
}

private fun getCommonItem(groups: Triple<String, String, String>): Char {
    val seen = HashMap<Char, Triple<Boolean, Boolean, Boolean>>()
    groups.first.toCharArray().forEach() { seen[it] = Triple(true, false, false) }
    groups.second.toCharArray().forEach {
        if (seen.containsKey(it)) seen[it] = Triple(true, true, false)
    }

    groups.third.toCharArray().forEach {
        if (seen.containsKey(it)) {
            if (seen[it]!!.first && seen[it]!!.second) {
                return it
            }
        }
    }

    throw IllegalStateException("Could not find a common item in $groups")
}

// https://adventofcode.com/2022/day/3
fun partOne() {
    val lines = FileUtils.readLines(inputName)
    var totalSum = 0

    lines.forEach {
        val compartments = Pair(it.substring(0, it.length / 2), it.substring(it.length / 2))
        val commonItem = getCommonItem(compartments)
        totalSum += getPriorityForItem(commonItem)
    }

    println("Total sum of priorities is $totalSum")
}

// https://adventofcode.com/2022/day/3#part2
fun partTwo() {
    val lines = FileUtils.readLines(inputName)
    var totalSum = 0

    for (i in lines.indices step 3) {
        val commonItem = getCommonItem(Triple(lines[i], lines[i+1], lines[i+2]))
        totalSum += getPriorityForItem(commonItem)
    }

    println("Total sum of priorities is $totalSum")
}

fun main() {
    partOne()
    partTwo()
}