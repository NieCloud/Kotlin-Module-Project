class SelectArchiveScreen(private val archives: MutableList<Archive>) : Screen() {

    private val MENU_WHEN_ARCHIVES_ARE_EMPTY = "Введите номер:\n1. Создать новый архив\n2. Выйти из программы"

    override fun show() {
        val input = WorkWithUserInput.getUserInput(
            emptyMenu = MENU_WHEN_ARCHIVES_ARE_EMPTY,
            filledMenu = getMenuWithArchives(),
            isEmptyDataSet = archives.isEmpty()
        )

        val userInput = input.toIntOrNull()

        if (archives.isEmpty()) {
            when (userInput) {
                1 -> goToNewArchiveCreation()
                2 -> exit()
                else -> checkArchivesForInput(input)
            }
        } else {
            val exitNumber = archives.size + 2
            when (userInput) {
                1 -> goToNewArchiveCreation()
                exitNumber -> exit()
                in 2 until exitNumber -> {
                    val selectedArchive = archives[userInput!! - 2] // Use not-null assertion
                    println("Архив ${selectedArchive.name} выбран!")
                    val nextScreen = SelectNoteScreen(selectedArchive)
                    nextScreen.previousScreen = this
                    nextScreen.show()
                }
                else -> checkArchivesForInput(input)
            }

        }
    }

    private fun checkArchivesForInput(input: String) {
        WorkWithUserInput.checkForErrorIfNotCorrectInteger(input)
        show()
    }

    private fun goToNewArchiveCreation() {
        val nextScreen = CreateArchiveScreen(archives)
        nextScreen.previousScreen = this
        nextScreen.show()
    }

    private fun getMenuWithArchives(): String {
        val menuBuilder = StringBuilder()
        menuBuilder.append("Введите номер:\n")
        menuBuilder.append("1. Создать новый архив\n")
        for ((index, archive) in archives.withIndex()) {
            menuBuilder.append("${index + 2}. Это мой уже созданный архив с названием: \"${archive.name}\"\n")
        }
        menuBuilder.append("${archives.size + 2}. Выйти из программы")
        return menuBuilder.toString()
    }
}
