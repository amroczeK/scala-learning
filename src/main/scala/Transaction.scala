// Declaring Traits as Self Type
trait User {
    def getFirstName: String
    def getLastName: String
}

trait Transaction {
    this: User => // This is an alternative to using 'extends User'
    def printAmountWithTransaction(amount: Double): Unit = {
        val fullCustomerName = this.getFirstName + " " + this.getLastName // Declaring self type
        val tax = amount * 0.10 // 10%
        println(s"$fullCustomerName paid $tax tax on amount of $amount")
    }
}

// firstName & lastName are instance variables, compiler generates 
// GETTER methods for these fields
class DebitTransaction(val firstName: String, val lastName: String) extends User with Transaction {
  override def getFirstName: String = firstName
  override def getLastName: String = lastName
}

object TransactionRunner extends App {
    val transaction = new DebitTransaction("Tony", "Stark")
    transaction.printAmountWithTransaction(125)
}