// Converting between different types

case class CurrencyImp(code: String, amount: Double, toUSD: Double)

object Implicits extends App {
    // money "USD 100"
    implicit def stringToCurrency(money: String): CurrencyImp = {
        val Array(code: String, value: String) = money.split("\\s")
        val amountAsDouble = value.toDouble
        code match {
            case "USD" => CurrencyImp("USD", amountAsDouble, amountAsDouble)
            case "NZD" => CurrencyImp("USD", amountAsDouble, (amountAsDouble * (1/1.5)))
            case "CAD" => CurrencyImp("USD", amountAsDouble, (amountAsDouble * (1/1.30)))
        }
    }

    println(stringToCurrency(money = "USD 100"))
    println(stringToCurrency(money = "NZD 100"))

    val cad: CurrencyImp = "CAD 100"
    println(cad)
}