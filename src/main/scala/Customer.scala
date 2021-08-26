import java.util.UUID

// Use 'final' keyword on base class to restrict inheritence
// NOTE: Best pracitce is to always create classes with 'final' keywords and only relax it/remove it
// When you have a business use case to allow inheritence for this class
final class Customer(firstName: String, lastName: String, email: String) {
    private val _id: UUID = UUID.randomUUID()
    private val _firstName = firstName
    private val _lastName = lastName
    private val _email = email

    def getId: UUID = _id
    def getFirstName: String = _firstName
    def getLastName: String = _lastName
    def getEmail: String =_email

}

object EmailRunner extends App {
    val c1: Customer = new Customer("Tony", "Stark", "tony@stark.com")
    println(c1.getId, c1.getFirstName, c1.getLastName, c1.getEmail)
}