import java.util.concurrent.atomic.AtomicInteger

class LispToJavascriptTranspiler {

    // From `(add 2 (sub 4 3))` to `add(2, sub(4, 3))`
    // 1. Lexical Analysis
    // 2. Syntactic Analysis
    // 3. Transformation
    // 4. Code Generation
    fun transpile(input: String) : String {
        log("transpile(), input: $input")
        val tokens = tokenize(input)
        log("transpile(), tokens: $tokens")
        val ast = parse(tokens, AtomicInteger(0))
        log("transpile(), ast: $ast")

        return ""
    }

    private fun tokenize(input: String) : List<Token> {
        val tokens = mutableListOf<Token>()
        var i = 0
        while (i < input.length) {
            var char = input[i]
            if (char == '(' || char == ')') {
                tokens.add(Token("paren", char.toString()))
                i++
                continue
            }
            if (char.isLetter()) {
                var value = ""
                do {
                    value += char
                    char = input[++i]
                } while (char.isLetter())
                tokens.add(Token("name", value))
                continue
            }
            if (char.isDigit()) {
                var value = ""
                do {
                    value += char
                    char = input[++i]
                } while (char.isDigit())
                tokens.add(Token("number", value))
                continue
            }
            if (char.isWhitespace()) {
                i++
                continue
            }
            throw Exception("Unknown char: '$char'")
        }
        return tokens
    }

    private fun parse(tokens: List<Token>, index: AtomicInteger) : AstNode {
        var token = tokens[index.get()]
        if (token.type == "number") {
            index.incrementAndGet()
            return NumberLiteral(token.value)
        }
        if (token.type == "paren" && token.value == "(") {
            token = tokens[index.incrementAndGet()] // Function name comes after the open paren
            val expression = CallExpression(token.value, mutableListOf())
            token = tokens[index.incrementAndGet()]
            while (token.value != ")") {
                expression.params.add(parse(tokens, index))
                token = tokens[index.get()]
            }
            index.incrementAndGet()
            return expression
        }
        throw Exception("Unknown token: '$token'")
    }

    private fun log(message: String) {
        println(message)
    }

    data class Token(val type: String, val value: String)

    open class AstNode

    data class AstRoot(val nodes: List<AstNode>)
    data class NumberLiteral(val value: String) : AstNode()
    data class CallExpression(val value: String, val params: MutableList<AstNode>) : AstNode()


}