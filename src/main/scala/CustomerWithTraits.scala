import java.util.UUID

// Use 'final' keyword on base class to restrict inheritence
// NOTE: Best pracitce is to always create classes with 'final' keywords and only relax it/remove it
// When you have a business use case to allow inheritence for this class
class CustomerWithTraits(firstName: String, lastName: String, email: String) {
    private val _id: UUID = UUID.randomUUID()
    private val _firstName = firstName
    private val _lastName = lastName
    private val _email = email

    def getId: UUID = _id
    def getFirstName: String = _firstName
    def getLastName: String = _lastName
    def getEmail: String =_email

}

// Traits are pieces of code that can be re-used across the application
// Having trait Address increases reusability of code and logic between 
// different types of Customers
trait Address {
    // 'protected' keyword - only classes using this trait has access to these fields
    protected var _zip: Int
    protected var _street: String
    protected var _city: String
    protected var _state: String

    def setZip(zip: Int): Unit
    def setStreet(street: String): Unit
    def setCity(city: String): Unit
    def setState(state: String): Unit

    def printAddress(): Unit = println(s"${_street}, ${_city} ${_state} ${_zip}")
}

trait SecretCodeGenerator {
    def getOTP: String = UUID.randomUUID().toString
}

// Traits cannot be instantiated using 'new' keyword or take any parameters
// To use a trait you need to mix in a trait with a class
// Use 'with' keyword to use the trait
class CustomerWithAddress(firstName: String, lastName: String, email: String) 
extends CustomerWithTraits(firstName, lastName, email) 
with Address 
with SecretCodeGenerator {
  override protected var _zip: Int = -1
  override protected var _street: String = ""
  override protected var _city: String = ""
  override protected var _state: String = ""

  override def setZip(zip: Int): Unit = _zip = zip

  override def setStreet(street: String): Unit = _street = street

  override def setCity(city: String): Unit = _city = city

  override def setState(state: String): Unit = _state = state

}

class DepositBoxContainer extends SecretCodeGenerator

object EmailRunnerWithTraits extends App {
  val c1 = new CustomerWithAddress("Tony", "Stark", "tony.stark@gmail.com")
  c1.setZip(6000)
  c1.setStreet("26 Saint Georges Tce")
  c1.setCity("Perth")
  c1.setState("Western Australia")

  println(c1.getFirstName, c1.getLastName, c1.getEmail)
  c1.printAddress()
  println(c1.getOTP)

  val depositBoxContainer = new DepositBoxContainer
  println("DepositBoxCotnainer OTP: " + depositBoxContainer.getOTP)
}