class SelectArchiveScreen(private val archives: MutableList<Archive>) : Screen() {

    private val MENU_WHEN_ARHCHIVES_ARE_EMPTY = "Введите номер:\n1. Создать новый архив\n2. Выйти из программы"
    private val MENU_WHEN_ARCHIEVES_ARE_FILLED = "Введите номер:\n1. Выбрать архив\n2. Создать новый архив\n3. Посмотреть список всех архивов\n4. Выйти из программы"


    override fun show() {

        val input = WorkWithUserInput.getUserInput(emptyMenu = MENU_WHEN_ARHCHIVES_ARE_EMPTY, filledMenu = MENU_WHEN_ARCHIEVES_ARE_FILLED, isEmptyDataSet = archives.isEmpty())

        if (archives.isEmpty()) {
            when (input.toIntOrNull()) {
                1 -> goToNewArchiveCreation()
                2 -> exit()
                else -> checkArchievesForInput(input)
            }
        } else {
            when (input.toIntOrNull()) {
                1 -> {
                    printArchievesNames()
                    chooseArchives()
                }
                2 -> goToNewArchiveCreation()
                3 -> {
                    printArchievesNames()
                    show()
                }
                4 -> exit()
                else -> checkArchievesForInput(input)
            }
        }
    }

    fun checkArchievesForInput(input: String) {
        WorkWithUserInput.checkForErrorIfNotCorrectInteger(input)
        show()
    }

    fun printArchievesNames() {
        println("Названия архивов и их уникальные номера: ${archives.joinToString(prefix = "[", postfix = "]")}")
    }

    fun goToNewArchiveCreation() {
        nextScreen = CreateArchiveScreen(archives)
        nextScreen?.show()
    }

    fun chooseArchives() {
        var archiveNumber: Int? = null

        while (archiveNumber == null) {
            val input = WorkWithUserInput.getUserInput("Введите номер архива:")
            archiveNumber = input.toIntOrNull()

            if (archiveNumber == null) {
                WorkWithUserInput.showError("Некорректный ввод. Введите целое число.")
            }
        }


        val selectedArchive = archives.find { it.uniqueNumber == archiveNumber }
        if (selectedArchive != null) {
            println("Архив ${selectedArchive.name} выбран!")
            nextScreen = SelectNoteScreen(selectedArchive)
            nextScreen?.previousScreen = this
            nextScreen?.show()
        } else {
            WorkWithUserInput.showError("Архива с номером '$archiveNumber' не существует.")
            show()
        }
    }
}
//
