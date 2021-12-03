fun main()
{
    print("Select which day you desire to execute: ")

    val day: Int? = readLine()?.toIntOrNull()

    if (day == null) {
        main()

        return
    }

    when (day) {
        1 -> {
            day01.run()
        }
        2 -> {
            day02.run()
        }
    }
}