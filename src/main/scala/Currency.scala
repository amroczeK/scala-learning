// Companion Object
// Add variables or methods which do not depend on instance variables
object Currency {
    private val conversionTable: Map[String, Double] = Map("CAD" -> 1/1.30, "NZD" -> 1/1.50)
    
    private def getCostInUsd(code: String, amount: Double): Double = {
        code match {
            case "USD" => amount
            case "NZD" => amount * conversionTable("NZD")
            case "CAD" => amount * conversionTable("CAD")
            case _ => throw new IllegalArgumentException(s"No conversion available for ${code}")
        }
    }
    
    // Change default apply method
    def apply(code: String, amount: Double) = new Currency(code, amount, getCostInUsd(code, amount))

    // def createUSD(amount: Double) = new Currency("USD", amount)
    // def createNZD(amount: Double) = new Currency("NZD", amount)
    // def createCAD(amount: Double) = new Currency("CAD", amount)

    def createUSD(amount: Double): Currency = Currency("USD", amount)
    def createNZD(amount: Double): Currency = Currency("NZD", amount)
    def createCAD(amount: Double): Currency = Currency("CAD", amount)

    // Implicit conversions
    implicit def string2Currency(money: String): Currency = {
        val Array(code: String, value: String) = money.split("\\s")
        val requestedAmount: Double = value.toDouble
        Currency(code, requestedAmount)
    }
}

class Currency(code: String, amount: Double, inUSD: Double) {
    import Currency.conversionTable // Import variable from companion object
    private val _code = code
    private val _amount = amount
    //private val _inUSD: Double = getCostInUsd
    private val _inUSD: Double = inUSD

    // private def getCostInUsd: Double = {
    //     _code match {
    //         case "USD" => _amount
    //         case "NZD" => _amount * conversionTable("NZD")
    //         case "CAD" => _amount * conversionTable("CAD")
    //         case _ => throw new IllegalArgumentException(s"No conversion available for ${_code}")
    //     }
    // }

    override def toString: String = s"${_code} ${_amount} = USD ${_inUSD}"
}

object CurrencyRunner extends App {
    // 'new' keyword can be ommited due to edited 'apply' function in companion object
    val usd1 = Currency(code = "USD", amount = 100.12)
    println(usd1)

    val nzd1 = Currency(code = "NZD", amount = 100.12)
    println(nzd1)

    // val cad1 = new Currency(code = "CAD", amount = 100.12)
    // println(cad1)

    // Using methods in companion object
    import Currency._
    val usd2 = createUSD(25)
    val nzd2 = createNZD(amount = 25)
    val cad2 = createCAD(25)
    println(usd2)
    println(nzd2)
    println(cad2)

    // Using implicit conversion
    import Currency.string2Currency
    val nzd3: Currency = "NZD 25"
    println(nzd3)
}