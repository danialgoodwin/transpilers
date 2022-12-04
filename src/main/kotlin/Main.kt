fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")


    val input = "(add 1 2)"
    val expectedOutput = "add(1, 2);"
    val output = LispToJavascriptTranspiler().transpile(input)
    assert(output == expectedOutput)

    val input1 = "(add 2 (sub 4 3))"
    val expectedOutput1 = "add(2, sub(4, 3));"
    val output1 = LispToJavascriptTranspiler().transpile(input1)
    assert(output1 == expectedOutput1)

    val input2 = "(add 2 (sub (add 1 2) (add 3 4)))"
    val expectedOutput2 = "add(2, sub(add(1, 2), add(3, 4)));"
    val output2 = LispToJavascriptTranspiler().transpile(input2)
    assert(output2 == expectedOutput2)
}
