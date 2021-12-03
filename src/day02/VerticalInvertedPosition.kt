package day02

class VerticalInvertedPosition {
    var x: Long = 0L
    var invertedY: Long = 0L

    fun copy() : VerticalInvertedPosition {
        val copy : VerticalInvertedPosition = VerticalInvertedPosition()
        copy.x = this.x
        copy.invertedY = this.invertedY

        return copy
    }
}