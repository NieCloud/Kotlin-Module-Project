class SelectArchiveScreen(private val archives: MutableList<Archive>) : Screen() {
    override fun show() {

        var input: String?

        if (archives.isEmpty()) {
            input = WorkWithUserInput.getUserInput("Введите\n2. Создать новый архив\n4. Выйти из программы")
        } else {
            input = WorkWithUserInput.getUserInput("Введите\n1. Выбрать архив\n2. Создать новый архив\n3. Посмотреть список всех архивов\n4. Выйти из программы")
        }

        if (input?.isEmpty() == true) {
            showError("Ввод не может быть пустым!")
            show()
        }


        when (input?.toIntOrNull()) {
            1 -> {
                val archiveName = WorkWithUserInput.getUserInput("Введите название архива:")
                val selectedArchive = archives.find { it.name == archiveName }
                if (selectedArchive != null) {
                    println("Архив выбран!")
                    nextScreen = SelectNoteScreen(selectedArchive)
                    nextScreen?.previousScreen = this
                    nextScreen?.show()
                } else {
                    showError("Архив '$archiveName' не существует.")
                    show()
                }
            }
            2 -> {
                nextScreen = CreateArchiveScreen(archives)
                nextScreen?.show()
            }
            3 -> {
                println(archives.joinToString(prefix = "[", postfix = "]"))
                show()
            }
            4 -> exit()
            else -> {

                try {
                    input?.toInt()
                    showError("Неверный ввод! Пожалуйста, введите корректную цифру!")
                } catch (e: NumberFormatException) {
                    showError("Неверный ввод! Пожалуйста, введите цифру")
                }
                show()
            }
        }
    }
}
//
