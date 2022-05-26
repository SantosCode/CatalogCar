package br.com.fiap.catalogcar.route

sealed class NavRoute(val path: String) {
    object Auth: NavRoute("authentication")
    object Login: NavRoute("login")
    object CarList: NavRoute("carList")
    object CarForm: NavRoute("carForm")
    object CarFormEdit: NavRoute("carFormEdit") {
        const val id: String = "id"
    }

    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    fun withArgsFormat(vararg args: String) : String {
        return buildString {
            append(path)
            args.forEach{ arg ->
                append("/{$arg}")
            }
        }
    }
}