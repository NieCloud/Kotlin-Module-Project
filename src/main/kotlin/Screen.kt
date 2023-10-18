open class Screen {
    var previousScreen: Screen? = null
    protected var nextScreen: Screen? = null


    open fun show() {
    }


    fun exit() {
        println("Выходим из программы! До свидания!")
        System.exit(0)
    }

}
