object Main extends App {
  def sumOdd(n: Int): Int = {
    // Local Function
    def getOdd(x: Int): Array[Int] = {
      var result = Array[Int]()
      var current = 1
      while (current <= x) {
        if(current % 2 == 1) result = result :+ current
        current = current + 1
      }
      result
    }

    val odds = getOdd(n)
    println(odds.mkString(","))
    odds.sum
  }
  sumOdd(20)
}