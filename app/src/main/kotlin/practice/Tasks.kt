package practice

import java.math.BigDecimal
import java.math.RoundingMode

fun main() {
    val book1 = Book(30, 444)
    val book2 = Book(60.09, 3030)
    val book3 = Book(100.99, 7501)
    val magazine = Magazine(15.9, 30)

    /***Вывести в лог для каждого объекта тип, количество строк и цену в евро в отформатированном виде.***/

    println(
        "Book 1: Type - ${book1.getType()}, Word Count - ${book1.wordCount}, Price - ${book1.price} EUR"
    )

    println(
        "Book 2: Type - ${book2.getType()}, Word Count - ${book2.wordCount}, Price - ${book2.price} EUR"
    )

    println(
        "Book 3: Type - ${book3.getType()}, Word Count - ${book3.wordCount}, Price - ${book3.price} EUR"
    )

    println(
        "Magazine: Type - ${magazine.getType()}, Word Count - ${magazine.wordCount}, Price - ${magazine.price} EUR"
    )

    println("\n-------------Task equals-------------\n")

    /***У класса Book переопределить метод equals и произвести сравнение сначала по ссылке,
    затем используя метод equals. Результаты сравнений вывести в лог.***/

    val book4 = Book(15.0, 300)
    val book5 = Book(15.0, 300)
    val book6 = book1

    println("book1 == book6: ${book1 == book6}")
    println("book1 == book2: ${book1 == book2}")
    println("book1 == book4: ${book1 == book4}")
    println("book4 == book5: ${book4 == book5}")

    println("\n-------------Task buy-------------\n")

    val book7: Book? = null
    val book8 = Book(4.0, 10)
    book7?.let {
        buy(it)
    }
    book8?.let {
        buy(it)
    }

    println("\n-------------Task lambda-------------\n")

    sum(5.0, 3.5564)
    sum(12, 3.363)
    sum(2222.0, 333)
    sum(2, 2)
}


/***Создать метод buy, который в качестве параметра принимает Publication (notnull - значения)
и выводит в лог “The purchase is complete. The purchase amount was [цена издания]”.
Создать две переменных класса Book, в которых могут находиться null значения. Присвоить одной null,
а второй любое notnull значение. Используя функцию let, попробуйте вызвать метод
buy с каждой из переменных.***/

fun buy(publication: Publication) {
    println("The purchase is complete. The purchase amount was ${publication.price}.")
}


/***Создать переменную sum и присвоить ей лямбда-выражение, которое будет складывать
два переданных ей числа и выводить результат в лог. Вызвать данное лямбда-выражение
с произвольными параметрами.***/

val sum = { x: Number, y: Number -> println("Sum: ${x.toDouble() + y.toDouble()}") }


/***
 * Необходимо создать интерфейс Publication, у которого должно быть свойства – price и wordCount,
 * а также метод getType, возвращающий строку. Создать два класса, реализующих данный
 * интерфейс – Book и Magazine. В методе getType для класса Book возвращаем строку с зависимости
 * от количества слов. Если количество слов не превышает 1000, то вывести “Flash Fiction”,
 * 7,500 –“Short Story”, всё, что выше – “Novel”. Для класса Magazine возвращаем строку “Magazine”.
 */

interface Publication {
    val price: BigDecimal
    val wordCount: Int

    fun getType(): String
}

class Book(private val _price: Number, override val wordCount: Int) : Publication {
    override val price: BigDecimal
        get() = BigDecimal(_price.toDouble()).setScale(2, RoundingMode.HALF_EVEN)


    override fun getType(): String {
        return when (this.wordCount) {
            in 0..1_000 -> "Flash Fiction"
            in 1_001..7_500 -> "Short Story"
            else -> "Novel"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Book) return false
        return this.price == other.price && this.wordCount == other.wordCount
    }
}

class Magazine(private val _price: Number, override val wordCount: Int) : Publication {
    override val price: BigDecimal
        get() = BigDecimal(_price.toDouble()).setScale(2, RoundingMode.HALF_EVEN)

    override fun getType(): String {
        return "Magazine"
    }
}