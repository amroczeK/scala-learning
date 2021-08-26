// Functions and local functions
def square(x: Int): Int = {
    return x * x
}
val sq_2 = square(2)

def multiply(x: Int, y: Int): Int = x * y
val mul_10_30 = multiply(10, 30)

def multiply(nums: Int*): Int = {
    var product = 1
    for(num <- nums) product = product * num
    product
}
val mul_10_20_30 = multiply(10, 20, 30)

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


// Named Arguments
def greet(first: String, last: String): Unit = {
  println("Hello! " + first + ", " + last)
}
greet(last = "Stark", first = "Tony")


// Default values
def logTransaction(amount: Double, debit: Boolean = true, currency: String = "USD"){
  val symbol = if(debit) "-" else "+"
  println(symbol + currency + amount)
}
logTransaction(amount = 100.25, debit = true, currency = "USD")
logTransaction(50.25)
logTransaction(89.25, false, "NZD")


// Higher Order Functions
// Is a function that takes another function as the input OR returns another function as the return value

// Function types
def square2(n: Int): Int = n * n // Int => Int
def cube(n: Int): Int = n * n * n // Int => Int
def sum(a: Int, b: Int): Int = a + b // (Int, Int) => Int
def length(a: String): Int = a.length // String => Int


def transform(f: Int => Int, numbers: Int*): Seq[Int] = numbers.map(f)
transform(square, 1, 2, 3, 4)
transform(cube, 1, 2, 3, 4, 5)
transform((n: Int) => n * n, 1, 2, 3)
transform(n => n * n * n, 1, 2, 3)
transform(x => 2 * x, 1, 2, 3)

val numbers = Array(1, 2, 3, 4, 5)
numbers.map(num => num * 2)
// Find even numbers
numbers.filter(num => num % 2 == 0)
// Find odd numbers
numbers.filter(num => num % 2 == 1)

