package day04

import java.lang.NumberFormatException

class BingoPlayer() {
    private val drawnNumbersLine: Int = 0
    private val bingoBoardNumbersRegex: Regex = "\\D+".toRegex()

    private lateinit var splitRawDrawnNumbers: List<String>
    private lateinit var bingoBoards: ArrayList<BingoBoard>
    // Winner board with the number that made the board win.
    private var winner: BingoBoard? = null

    private var drawnNumber: Int = -1
    private var lastDrawnNumberIndex: Int = -1

    fun playInput(input: List<String>, eventsHandler: BingoEventsHandler) {
        splitRawDrawnNumbers = input[drawnNumbersLine].split(",")

        retrieveBingoBoards(input.subList(2, input.size))

        // Assure correct initial state for several play calls.
        initializeStartingState()

        gameLoop()

        winner?.let {
            eventsHandler.onBoardWin(it, drawnNumber)
            return
        }

        eventsHandler.onEndWithNoWinners()
    }

    private fun retrieveBingoBoards(input: List<String>) {
        bingoBoards = ArrayList()
        val matrixSize: Pair<Int, Int> = detectBingoBoardMatrixSizeFromInput(input)

        var bingoBoard: BingoBoard = BingoBoard(matrixSize.first, matrixSize.second)
        var rowOffset: Int = 0

        for (i in input.indices) {
            val line: String = input[i]

            if (isDelimiterOfBoards(line)) {
                bingoBoards.add(bingoBoard)

                bingoBoard = BingoBoard(matrixSize.first, matrixSize.second)

                rowOffset = 0
                continue
            }

            val columns: List<String> = readColumnsFromLine(line)

            for (c in columns.indices) {
                bingoBoard.setNumber(columns[c].toInt(), c, rowOffset)
            }

            if (i == (input.size - 1)) {
                bingoBoards.add(bingoBoard)
                break
            }

            rowOffset++
        }
    }

    private fun detectBingoBoardMatrixSizeFromInput(input: List<String>) : Pair<Int, Int> {
        var columns: Int = 0
        var rows: Int = 0
        for (i in input.indices) {
            val line: String = input[i]

            if (i == 0) {
                columns = readColumnsFromLine(line).size
            }

            if (isDelimiterOfBoards(line)) {
                rows = i
                break
            }
        }

        return Pair<Int, Int>(columns, rows)
    }

    private fun readColumnsFromLine(line: String) : List<String> {
        val uncheckedNumbers: MutableList<String> = line.split(bingoBoardNumbersRegex).toMutableList()
        val removableItems: ArrayList<Int> = ArrayList()

        for (i in uncheckedNumbers.indices) {
            if (uncheckedNumbers[i].isBlank()) {
                removableItems.add(i)
            }
        }

        for (removableIndex in removableItems.indices.reversed()) {
            uncheckedNumbers.removeAt(removableIndex)
        }

        return uncheckedNumbers
    }

    private fun isDelimiterOfBoards(line: String) : Boolean {
        return line.isBlank()
    }

    private fun initializeStartingState() {
        winner = null
        drawnNumber = -1
        lastDrawnNumberIndex = -1
    }

    private fun gameLoop() {
        while (moreDrawnNumbers()) {
            retrieveNextDrawnNumber()

            if (hasAnyBoardWon()) {
                return
            }
        }
    }

    private fun moreDrawnNumbers() : Boolean {
        return lastDrawnNumberIndex + 1 < splitRawDrawnNumbers.size
    }

    private fun retrieveNextDrawnNumber() {
        if (lastDrawnNumberIndex + 1 >= splitRawDrawnNumbers.size) {
            throw IllegalArgumentException("There are no more numbers available to be drawn.")
        }

        val rawIndex = lastDrawnNumberIndex + 1
        drawnNumber = splitRawDrawnNumbers[rawIndex].toInt()

        lastDrawnNumberIndex++
    }

    /**
     * Sets the winner property to the first detected winner board and returns whether any board has won.
     */
    private fun hasAnyBoardWon() : Boolean {
        for (board in bingoBoards) {
            // Boolean: whether is bingo.
            val bingoAndNumberIfApplicable: Boolean = board.hasBingoByDrawnNumber(drawnNumber)

            if (bingoAndNumberIfApplicable) {
                winner = board
                return true
            }
        }

        return false
    }
}