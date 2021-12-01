import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input for a specific day, indicating whether to retrieve the test input.
 */
fun readInputForDay(day: Int, test: Boolean) : List<String> {
    val name: String = String.format("Day%02d", day)

    return readInput(name)
}

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/${name.lowercase()}", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
