import java.util.UUID
import java.time.LocalDateTime

// Account is an abstract class to enable subtyping
// Abstract class is a class where one or more members don't have a concrete value
// Their value is provided by the classes that inherit them
abstract class Account(id: UUID, name: String, dateOpened: LocalDateTime) {
    // Member variables of class
    private val _id: UUID = id
    private var _name: String = name
    private val _dateOpened: LocalDateTime = dateOpened
    val _accountType: String

    // Auxiliary Constructor - each one must have different signature
    def this(name: String) {
        this(UUID.randomUUID(), name, LocalDateTime.now)
    }

    // Getter methods
    def getId: UUID = _id
    def getName: String = _name
    def getDateOpened: LocalDateTime = _dateOpened
    def updateName(newName: String): Unit = _name = newName
    // Getter for when class abstract
    def getAccountType: String = _accountType

    override def toString: String = s"Account id=${_id},name=${_name},dateOpened=${_dateOpened}"
}

// Sub class - Inheritence
// When an instance of CreditAccount is created, 'name' will be passed onto the parent class Account
class CreditAccount(name: String) extends Account(name: String) {
    // private val _accountType = "Credit"
    // def getAccountType: String = _accountType

    // Override behaviour of the super class
    override val _accountType: String = "Credit"

    override def toString: String = s"Credit Account id=${getId},name=${getName},dateOpened=${getDateOpened},accountType=${getAccountType}"
}

class DepositsAccount(name: String) extends Account(name: String) {
    // private val _accountType = "Deposit"
    // def getAccountType: String = _accountType

    // Override behaviour of the super class
    override val _accountType: String = "Deposit"

    override def toString: String = s"Debit Account id=${getId},name=${getName},dateOpened=${getDateOpened},accountType=${getAccountType}"
}

object AccountRunner extends App {
    // These are available when Account class wasn't abstract
    // val a1 = new Account(UUID.randomUUID(), name = "Account 01", LocalDateTime.now)
    // val a2 = new Account(UUID.randomUUID(), name = "Account 02", LocalDateTime.now.plusHours(6))
    // val a3 = new Account(name = "Account 03")

    // a1.updateName("Updated Account 01")
    // println(a1.getId, a1.getName, a1.getDateOpened)
    // println(a2.getId, a2.getName, a2.getDateOpened)
    // println(a3.getId, a3.getName, a3.getDateOpened)

    // val ca1: CreditAccount = new CreditAccount(name = "Travel Mastercard") 
    // println(ca1.getId, ca1.getName, ca1.getDateOpened, ca1.getAccountType)

    // val da1: DepositsAccount = new DepositsAccount("Super Savings")
    // println(da1.getId, da1.getName, da1.getDateOpened, da1.getAccountType)

    // Subtype for abstract class
    // Value of subclass can be used where ever the value of superclass is required
    val ca2: Account = new CreditAccount(name = "Gold Mastercard") 
    println(ca2.getId, ca2.getName, ca2.getDateOpened, ca2.getAccountType)
    println(ca2)

    val da2: Account = new DepositsAccount("Super Duper Savings")
    println(da2.getId, da2.getName, da2.getDateOpened, da2.getAccountType)
    println(da2)
}