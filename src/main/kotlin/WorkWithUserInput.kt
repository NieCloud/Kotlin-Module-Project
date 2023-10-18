class WorkWithUserInput {




    companion object {
        fun getUserInput(emptyMenu: String = "", filledMenu:String = "", isEmptyDataSet: Boolean = true): String {

            while (true) {
                if (isEmptyDataSet) {
                    println(emptyMenu)
                } else {
                    println(filledMenu)
                }

                val userInput = readlnOrNull()

                if (userInput.isNullOrBlank()) {
                    showError("Ввод не может быть пустым!")
                } else {
                    return userInput
                }
            }
        }

        fun checkForErrorIfNotCorrectInteger(input: String) {
            try {
                input.toInt()
                showError("Неверный ввод! Пожалуйста, введите корректную цифру!")
            } catch (e: NumberFormatException) {
                showError("Неверный ввод! Пожалуйста, введите цифру")
            }
        }

        fun showError(message: String) {
            println("Ошибка: $message")
        }


    }
}