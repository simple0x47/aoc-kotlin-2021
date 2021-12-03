package day02

import readInputForDay

fun run() {
    // First part.
    fun calculateFinalPositionProduct(input: List<String>): Long {
        val submarine: Submarine = Submarine()

        for (command in input) {
            val commandParts: List<String> = command.split(" ")

            val commandType: String = commandParts[0]
            val commandValue: Long = commandParts[1].toLong()

            when (commandType) {
                "forward" -> submarine.forward(commandValue)
                "down" -> submarine.down(commandValue)
                "up" -> submarine.up(commandValue)
            }
        }

        return submarine.x * submarine.invertedY
    }

    val testInput = readInputForDay(2, true)
    check(calculateFinalPositionProduct(testInput) == 150L)

    val input = readInputForDay(2)
    println(calculateFinalPositionProduct(input))
}
