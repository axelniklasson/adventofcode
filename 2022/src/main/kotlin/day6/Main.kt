package day6

import util.FileUtils

private const val inputName = "day6_input.txt"
private const val markerLength = 4
private const val messageLength = 14

private fun allCharsDifferent(s: String): Boolean {
    val seenChars = HashMap<Char, Boolean>()

    for (i in s.indices) {
        val char = s[i]
        if (seenChars.containsKey(char)) return false
        seenChars[char] = true
    }

    return true
}

// https://adventofcode.com/2022/day/6
fun partOne() {
    val line = FileUtils.readLines(inputName).first()
    var running = ""

    for (i in line.indices) {
        val char = line[i]
        if (i < markerLength) {
            running += char
        } else {
            running = running.substring(1) + char
        }

        if (i >= markerLength && allCharsDifferent(running)) {
            println("Marker is complete after character number ${i + 1}")
            break
        }
    }
}

// https://adventofcode.com/2022/day/6#part2
fun partTwo() {
    val line = FileUtils.readLines(inputName).first()
    var running = ""

    for (i in line.indices) {
        val char = line[i]
        if (i < messageLength) {
            running += char
        } else {
            running = running.substring(1) + char
        }

        if (i >= messageLength && allCharsDifferent(running)) {
            println("Message is complete after character number ${i + 1}")
            break
        }
    }
}

fun main() {
//    partOne()
    partTwo()
}