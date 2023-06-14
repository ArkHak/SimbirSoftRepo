package practice2

import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Exception
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    val user1 = User(id = 1, name = "Oleg", age = 26, type = Type.DEMO)
    val user2 = User(id = 2, name = "Julia", age = 25, type = Type.FULL)
    val user3 = User(id = 3, name = "Ivan", age = 17, type = Type.DEMO)

    // 4. Создать объект класса User, вывести в лог startTime данного юзера,
    // после вызвать Thread.sleep(1000) и повторно вывести в лог startTime.
    println("-----------------------Task4--------------------------------")
    println(user1.startTime)
    Thread.sleep(1000)
    println(user1.startTime)

    // 5. Создать список пользователей, содержащий в себе один объект класса User.
    // Используя функцию apply, добавить ещё несколько объектов класса User в список пользователей.
    val listUsers = mutableListOf(user1).apply {
        addAll(listOf(user2, user3))
    }

    // 6. Получить список пользователей, у которых имеется полный доступ (поле type имеет значение FULL).
    println("-----------------------Task6--------------------------------")
    println(listUsers.filter { user -> user.type == Type.FULL })

    // 7. Преобразовать список User в список имен пользователей.
    // Получить первый и последний элементы списка и вывести их в лог.
    println("-----------------------Task7--------------------------------")
    val usersNameList = listUsers.map { it.name }
    println("first = ${usersNameList.firstOrNull()}, last = ${usersNameList.lastOrNull()}")

    // 8.
    println("-----------------------Task8--------------------------------")
    for (user in listUsers) {
        try {
            println(user.checkAge())
        } catch (e: Exception) {
            println(e.message)
        }
    }

    // 10. 11.
    println("-----------------------Task10 Task11--------------------------------")
    user1.auth(updateCache)
    user2.auth(updateCache)
    user3.auth(updateCache)

    // 12. 13.
    println("-----------------------Task13--------------------------------")
    doAction(Action.Registration)
    doAction(Action.Login(user1))
    doAction(Action.Logout)
}

// 2. Создать enum Type с константами DEMO и FULL.
enum class Type {
    DEMO,
    FULL,
}

// 3. Реализовать класс данных User с полями id, name, age и type. У класса User создать ленивое
// свойство startTime, в котором получаем текущее время.
@RequiresApi(Build.VERSION_CODES.O)
data class User(
    val id: Int,
    val name: String,
    val age: Int,
    val type: Type,
) {
    val startTime: LocalDateTime by lazy { LocalDateTime.now() }
}

// 8. Создать функцию-расширение класса User, которая проверяет, что юзер старше 18 лет,
// и в случае успеха выводит в лог, а в случае неуспеха возвращает ошибку.
private fun User.checkAge(): String {
    return if (this.age > 18) "Совершеннолетний" else throw Exception("Несовершеннолетний")
}

private fun User.checkAgeBoolean(): Boolean {
    return this.age > 18
}

// 9. Создать интерфейс AuthCallback с методами authSuccess, authFailed и
// реализовать анонимный объект данного интерфейса. В методах необходимо вывести
//  в лог информацию о статусе авторизации.
private interface AuthCallback {
    fun authSuccess()
    fun authFailed()
}

private val authCallback = object : AuthCallback {
    override fun authSuccess() {
        println("Success")
    }

    override fun authFailed() {
        println("Failed")
    }
}

// 10. Реализовать inline функцию auth, принимающую в качестве параметра функцию updateCache.
// Функция updateCache должна выводить в лог информацию об обновлении кэша.
// 11. Внутри функции auth вызвать метод коллбека authSuccess и переданный updateCache,
// если проверка возраста пользователя произошла без ошибки. В случае получения ошибки вызвать authFailed.
private inline fun User.auth(updateCache: () -> Unit) {
    if (this.checkAgeBoolean()) {
        authCallback.authSuccess()
        updateCache.invoke()
    } else {
        authCallback.authFailed()
    }
}

private val updateCache = {
    println("Cache update")
}

// 12. Реализовать изолированный класс Action и его наследников – Registration,
// Login и Logout. Login должен принимать в качестве параметра экземпляр класса User.

sealed class Action {
    object Registration : Action()
    data class Login(val user: User) : Action()
    object Logout : Action()
}

// 13. Реализовать метод doAction, принимающий экземпляр класса Action.
// В зависимости от переданного действия выводить в лог текст, к примеру “Auth started”.
// Для действия Login вызывать метод auth.
private fun doAction(action: Action) {
    when (action) {
        is Action.Login -> {
            println("Auth started")
            action.user.auth(updateCache)
        }

        is Action.Logout -> println("Action Logout")
        is Action.Registration -> println("Action Registration")
    }
}
