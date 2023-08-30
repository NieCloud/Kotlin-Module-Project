class WorkWithUserInput {




    companion object {
        fun getUserInput(emptyMenu: String = "", filledMenu:String = "", isEmptyDataSet: Boolean = true): String {
            if (isEmptyDataSet) {
                println(emptyMenu)
            } else {
                println(filledMenu)
            }
            val userInput = readLine()
            if (userInput?.isEmpty() == true) {
                showError("Ввод не может быть пустым!")
                getUserInput(emptyMenu, filledMenu, isEmptyDataSet)
            }
            return userInput.toString()
        }

        fun showError(message: String) {
            println("Ошибка: $message")
        }

        fun workWithSelectingArchives(
            archives: MutableList<Archive>) {

        }
    }
}