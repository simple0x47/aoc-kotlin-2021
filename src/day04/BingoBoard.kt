package day04

class BingoBoard(private val columns: Int, private val rows: Int) {
    private val numbers: IntArray = IntArray(columns * rows)
    private val numbersState: HashMap<Int, BingoBoardItem> = HashMap()

    fun setNumber(number: Int, column: Int, row: Int) {
        if (column > columns || row < 0) {
            throw IllegalArgumentException("Column ($column) is not within the board's limits [0, $columns).")
        }

        if (row > rows || row < 0) {
            throw IllegalArgumentException("Row ($row) is not within the board's limits [0, $rows).")
        }

        numbersState[number] = BingoBoardItem(number, column, row)
        numbers[mapIndexFromPosition(column, row)] = number
    }

    private fun mapIndexFromPosition(column: Int, row: Int) : Int {
        return column + (row * rows)
    }

    fun hasBingoByDrawnNumber(drawnNumber: Int) : Boolean {
        val item: BingoBoardItem? = numbersState[drawnNumber]

        item?.let {
            it.checked = true

            if (hasBingoByColumn(it) || hasBingoByRow(it)) {
                return true
            }
        }

        return false
    }

    private fun hasBingoByColumn(item: BingoBoardItem) : Boolean {
        var currentItem: BingoBoardItem?
        for (i in 0 until rows) {
            currentItem = numbersState[numbers[mapIndexFromPosition(item.column, i)]]

            currentItem?.let {
                if (!it.checked) {
                    return false
                }
            } ?: throw IllegalStateException("HashMap and Array not synced: missing item #${mapIndexFromPosition(item.column, i)}")
        }

        return true
    }

    private fun hasBingoByRow(item: BingoBoardItem) : Boolean {
        var currentItem: BingoBoardItem?
        for (i in 0 until columns) {
            currentItem = numbersState[numbers[mapIndexFromPosition(i, item.row)]]

            currentItem?.let {
                if (!it.checked) {
                    return false
                }
            } ?: throw IllegalStateException("HashMap and Array not synced: missing item #${mapIndexFromPosition(i, item.row)}")
        }

        return true
    }

    fun sumUncheckedNumbers() : Int {
        var count: Int = 0
        var item: BingoBoardItem?
        for (number in numbers) {
            item = numbersState[number]

            item?.let {
                if (!it.checked) {
                    count += it.number
                }
            } ?: throw IllegalStateException("HashMap and Array not synced: missing item #$number")
        }

        return count
    }
}