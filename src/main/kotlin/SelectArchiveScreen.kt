class SelectArchiveScreen(private val archives: MutableList<Archive>) : Screen() {
    override fun show() {
        val input = getUserInput("Введите\n1. Выбрать архив\n2. Создать новый архив\n3. Посмотреть список всех архивов\n4. Выйти из программы")
        when (input?.toIntOrNull()) {
            1 -> {
                val archiveName = getUserInput("Введите название архива:")
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
                print("Архивы: [ ")
                for (item in archives) {
                    print("${item.name},")
                }
                println(" ]")
                show()
            }
            4 -> exit()
            else -> {
                showError("Неверный ввод! Пожалуйста, введите корректную команду!")
                show()
            }
        }
    }
}
//
