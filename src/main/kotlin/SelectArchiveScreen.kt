class SelectArchiveScreen(private val archives: MutableList<Archive>) : Screen() {

    private val MENU_WHEN_ARHCHIVES_ARE_EMPTY = "Введите\n2. Создать новый архив\n4. Выйти из программы"
    private val MENU_WHEN_ARCHIEVES_ARE_FILLED = "Введите\n1. Выбрать архив\n2. Создать новый архив\n3. Посмотреть список всех архивов\n4. Выйти из программы"


    override fun show() {

        val input = WorkWithUserInput.getUserInput(emptyMenu = MENU_WHEN_ARHCHIVES_ARE_EMPTY, filledMenu = MENU_WHEN_ARCHIEVES_ARE_FILLED, isEmptyDataSet = archives.isEmpty())

        when (input.toIntOrNull()) {
            1 -> {
                val archiveName = WorkWithUserInput.getUserInput("Введите название архива:")
                val selectedArchive = archives.find { it.name == archiveName }
                if (selectedArchive != null) {
                    println("Архив выбран!")
                    nextScreen = SelectNoteScreen(selectedArchive)
                    nextScreen?.previousScreen = this
                    nextScreen?.show()
                } else {
                    WorkWithUserInput.showError("Архив '$archiveName' не существует.")
                    show()
                }
            }
            2 -> {
                nextScreen = CreateArchiveScreen(archives)
                nextScreen?.show()
            }
            3 -> {
                println("Названия архивов: ${archives.joinToString(prefix = "[", postfix = "]")}")
                show()
            }
            4 -> exit()
            else -> {
                WorkWithUserInput.checkForErrorIfNotCorrectInteger(input)
                show()
            }
        }
    }
}
//
