class SelectArchiveScreen(private val archives: MutableList<Archive>) : Screen() {


    private val MENU_WHEN_ARCHIVES_ARE_EMPTY = "Введите номер:\n1. Создать новый архив\n2. Выйти из программы"

    override fun show() {
        val input = WorkWithUserInput.getUserInput(
            emptyMenu = MENU_WHEN_ARCHIVES_ARE_EMPTY,
            filledMenu = getMenuWithArchives(),
            isEmptyDataSet = archives.isEmpty()
        )

        if (archives.isEmpty()) {
            when (input.toIntOrNull()) {
                1 -> goToNewArchiveCreation()
                2 -> exit()
                else -> checkArchievesForInput(input)
            }
        } else {
            val selectedArchiveIndex = input.toIntOrNull()
            if (selectedArchiveIndex != null && selectedArchiveIndex >= 3 && selectedArchiveIndex <= archives.size + 2) {
                // The user selected an archive, so go directly to SelectNoteScreen
                val selectedArchive = archives[selectedArchiveIndex - 3]
                println("Архив ${selectedArchive.name} выбран!")
                val nextScreen = SelectNoteScreen(selectedArchive)
                nextScreen.previousScreen = this
                nextScreen.show()
            } else {
                when (selectedArchiveIndex) {
                    1 -> goToNewArchiveCreation()
                    archives.size + 2 -> exit()
                    else -> checkArchievesForInput(input)
                }
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

    private fun getMenuWithArchives(): String {
        val menuBuilder = StringBuilder()
        menuBuilder.append("Введите номер:\n")
        menuBuilder.append("1. Создать новый архив\n")
        for ((index, archive) in archives.withIndex()) {
            menuBuilder.append("${index + 2}. Это мой уже созданный \" ${archive.name}\"\n")
        }
        menuBuilder.append("${archives.size + 2}. Выйти из программы")
        return menuBuilder.toString()
    }

    private fun chooseArchive() {
        var archiveNumber: Int? = null

        while (archiveNumber == null) {
            val input = WorkWithUserInput.getUserInput("Введите номер архива:")
            archiveNumber = input.toIntOrNull()

            if (archiveNumber == null || archiveNumber !in 1..archives.size) {
                WorkWithUserInput.showError("Некорректный ввод. Введите корректный номер архива.")
            }
        }

        val selectedArchive = archives[archiveNumber - 1]
        println("Архив ${selectedArchive.name} выбран!")
        val nextScreen = SelectNoteScreen(selectedArchive)
        nextScreen.previousScreen = this
        nextScreen.show()
    }
}

//
