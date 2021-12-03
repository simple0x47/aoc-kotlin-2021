package day02

class Submarine {
    var x: Long = 0L
        private set
    var invertedY: Long = 0L
        private set

    fun forward(amount: Long) {
        x += amount
    }

    fun down(amount: Long) {
        invertedY += amount
    }

    fun up(amount: Long) {
        invertedY -= amount
    }
}