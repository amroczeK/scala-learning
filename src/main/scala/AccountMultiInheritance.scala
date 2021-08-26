import java.util.UUID
import java.time.LocalDateTime

abstract class AccountMultiInheritance(id: UUID, name: String, dateOpened: LocalDateTime) {
    private val _id: UUID = id
    private var _name: String = name
    private val _dateOpened: LocalDateTime = dateOpened
    val _accountType: String

    def this(name: String) {
        this(UUID.randomUUID(), name, LocalDateTime.now)
    }

    def getId: UUID = _id
    def getName: String = _name
    def getDateOpened: LocalDateTime = _dateOpened
    def updateName(newName: String): Unit = _name = newName
    def getAccountType: String = _accountType

    override def toString: String = s"Account id=${_id}, name=${_name}, dateOpened=${_dateOpened}"
}

class CreditAccount2(name: String) extends AccountMultiInheritance(name: String) {
    override val _accountType: String = "Credit"

    override def toString: String = s"Credit Account id=${getId},name=${getName},dateOpened=${getDateOpened},accountType=${getAccountType}"
}

class DepositAccount(name: String) extends AccountMultiInheritance(name: String) {
    override val _accountType: String = "Deposit"

    override def toString: String = s"Deposit Account id=${getId},name=${getName},dateOpened=${getDateOpened},accountType=${getAccountType}"
}

trait Balance {
    private var _balance: Double = 0
    def getBalance: Double = _balance
    def setBalance(newBalance: Double): Unit = _balance = newBalance
    override def toString: String = s"Balance=${getBalance}"
}

trait AnnualFees extends Balance {
    override def setBalance(newBalance: Double): Unit = super.setBalance(newBalance - 100)
}

trait HighSavings extends Balance {
    override def setBalance(newBalance: Double): Unit = super.setBalance((newBalance + 500) * (1 + 0.50))
}


// Scala compiler puts inherited classes and traits in the LINEAR ORDER e.g. furthest on right first
// When a1.setBalance is called, it's call goes to the furthest on the right e.g. AnnualFees first
// setBalance in AnnualFees gets called first, deducting 100, then because AnnualFees extends Balance
// setBalance in Balance is called after, setting the final balance
class PremiumSavingsAccount(name: String) extends DepositAccount(name) with AnnualFees

// When you open a new account with balance using promotion, you get HighSavings bonus and pay AnnualFees
class PremiumPromotionalSavingsAccount(name: String) extends DepositAccount(name) with AnnualFees with HighSavings

// If you swap the traits you will get a different result, this is known as Stackable Modifications
//class PremiumPromotionalSavingsAccount(name: String) extends DepositAccount(name) with HighSavings with AnnualFees 

object AccountRunner2 extends App {
    val a1 = new PremiumSavingsAccount("Premium Savings Account")
    a1.setBalance(999)
    println(a1.getBalance) // Should return 899.0

    val a2 = new PremiumPromotionalSavingsAccount("Premium Promotional Savings Account")
    a2.setBalance(999)
    println(a2.getBalance)
}