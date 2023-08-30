class WorkWithUserInput {

    companion object {
        fun getUserInput(input: String): String? {
            println(input)
            return readLine()

        }

        fun showError(message: String) {
            println("Ошибка: $message")
        }
    }



}