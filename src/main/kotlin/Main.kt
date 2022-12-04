fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")


    val input = "(add 2 (sub 4 3))"
    val expectedOutput = "add(2, sub(4, 3))"

    LispToJavascriptTranspiler().transpile(input)
}
