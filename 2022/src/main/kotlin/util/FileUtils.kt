package util

import java.io.BufferedReader
import java.io.InputStreamReader

object FileUtils {
    fun readLines(path: String): List<String> {
        val classloader = Thread.currentThread().contextClassLoader
        val inputStream = classloader.getResourceAsStream(path)
        val reader = BufferedReader(InputStreamReader(inputStream))
        return reader.readLines()
    }
}