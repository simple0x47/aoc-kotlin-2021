package day02

import readInputForDay

fun run() {
    fun applyCommandToSubmarine(submarine: Submarine, command: String) {
        val commandParts: List<String> = command.split(" ")

        val commandType: String = commandParts[0]
        val commandValue: Long = commandParts[1].toLong()

        when (commandType) {
            "forward" -> submarine.forward(commandValue)
            "down" -> submarine.down(commandValue)
            "up" -> submarine.up(commandValue)
        }
    }

    fun calculateFinalPositionProduct(submarine: Submarine, input: List<String>): Long {
        for (command in input) {
            applyCommandToSubmarine(submarine, command)
        }

        val position: VerticalInvertedPosition = submarine.position
        return position.x * position.invertedY
    }

    val testInput = readInputForDay(2, true)
    check(calculateFinalPositionProduct(NewbieSubmarine(), testInput) == 150L)
    check(calculateFinalPositionProduct(RealSubmarine(), testInput) == 900L)

    val input = readInputForDay(2)
    println(calculateFinalPositionProduct(NewbieSubmarine(), input))
    println(calculateFinalPositionProduct(RealSubmarine(), input))
}
