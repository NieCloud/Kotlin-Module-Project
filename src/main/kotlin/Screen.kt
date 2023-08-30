open class Screen {
    var previousScreen: Screen? = null
    protected var nextScreen: Screen? = null

    open fun show() {

    }

    fun getUserInput(input: String): String? {
        println(input)
        return readLine()
    }

    fun showError(message: String) {
        println("Ошибка: $message")
    }

    fun exit() {
        println("Выходим из программы! До свидания!")
        System.exit(0)
    }
}
