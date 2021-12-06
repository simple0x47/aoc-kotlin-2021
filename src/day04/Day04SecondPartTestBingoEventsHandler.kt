package day04

class Day04SecondPartTestBingoEventsHandler : BingoEventsHandler {
    var hasBoardWon: Boolean = false
        private set

    override fun onBoardWin(board: BingoBoard, endingNumber: Int) {
        check(board.sumUncheckedNumbers() * endingNumber == 1924)
        hasBoardWon = true
    }

    override fun onEndWithNoWinners() {
        check(false)
    }
}