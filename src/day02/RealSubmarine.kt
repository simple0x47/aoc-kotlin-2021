package day02

class RealSubmarine : Submarine {
    private val _position: VerticalInvertedPosition = VerticalInvertedPosition()

    private var aim : Long = 0L

    override val position: VerticalInvertedPosition
        get() = _position.copy()

    override fun forward(amount: Long) {
        _position.x += amount
        _position.invertedY += amount * aim
    }

    override fun down(amount: Long) {
        aim += amount
    }

    override fun up(amount: Long) {
        aim -= amount
    }
}