// Companion Object
object Currency {

}

class Currency(code: String, amount: Double) {
    private val conversionTable: Map[String, Double] = Map("CAD" -> 1/1.30, "NZD" -> 1/1.50)
    private val _code = code
    private val _amount = amount
    private val _inUSD: Double = getCostInUsd

    private def getCostInUsd: Double = {
        _code match {
            case "USD" => _amount
            case "NZD" => _amount * conversionTable("NZD")
            case "CAD" => _amount * conversionTable("CAD")
            case _ => throw new IllegalArgumentException(s"No conversion available for ${_code}")
        }
    }

    override def toString: String = s"${code} ${_amount} = USD ${_inUSD}"
}

object CurrencyRunner extends App {
    val usd1 = new Currency(code = "USD", amount = 100.12)
    println(usd1)

    val nzd1 = new Currency(code = "NZD", amount = 90.12)
    println(nzd1)

    val cad1 = new Currency(code = "CAD", amount = 80.12)
    println(cad1)
}