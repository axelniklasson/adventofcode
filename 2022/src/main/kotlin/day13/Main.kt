package day13

import util.FileUtils

private const val inputName = "day13_input_test.txt"

private fun parsePackets(): List<Pair<String, String>> {
    val lines = FileUtils.readLines(inputName).filter { it.isNotEmpty() }.chunked(2)

    return lines.map { Pair(it[0], it[1]) }
}

private fun getElement(start: Int, chars: CharArray): Pair<String, Int> {
    var running = ""
    var processed = 0
    var openLists = 0
    for (i in start until chars.size - 1) {
        if (chars[i] == ',' && openLists == 0 && processed > 0) {
            processed++
            break
        } else if (chars[i] == ',' && processed == 0) {
           processed++
           continue
        } else if (chars[i] == '[') {
            // we're parsing a list
            openLists++
        } else if (chars[i] == ']' && openLists > 0) {
            running += chars[i]
            processed++
            openLists--
            if (openLists == 0) break
            continue
        }

        running += chars[i]
        processed++
    }
    return Pair(running, processed)
}

private fun isInOrder(left: String, right: String): Boolean {
    return true
}

// https://adventofcode.com/2022/day/12
fun partOne() {
    val packets = parsePackets()
    var sum = 0

    packets.forEachIndexed { idx, packet ->
        if (isInOrder(packet.first, packet.second)) {
            sum += (idx + 1) // list is 1-indexed according to docs
        }
    }

    println("Sum of pairs of packets already in the right other: $sum")
}

// https://adventofcode.com/2022/day/12#part2
fun partTwo() {
}

fun main() {
    partOne()
    partTwo()
}