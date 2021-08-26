import java.time.LocalDateTime

// Implementations auto generated behind the scenes by default for case classes,
// e.g. 'apply', 'toString', 'equals', 'hash' and 'copy' methods
// Case classes also support PATTERN MATCHING
case class Transaction2(name: String, amount: Double, king: String, when: LocalDateTime)

object TransactionRunner2 extends App {
    private val when = LocalDateTime.now
    // Creating a new instance of a case class doesn't require using 'new' keyword
    // because case classes add a factory method with the name of the class
    // This means you don't have to create an 'apply' method to achieve the same a below
    // Also the paramaters in the case class implicity get a 'val' prefix so they are maintained
    // as fields and immutable
    val t1 = Transaction2("T1", 12.22, "debit", LocalDateTime.now)
    println(t1.toString)

    val t2 = Transaction2("T2", 12.23, "debit", LocalDateTime.now)
    // Case class equals implementation compares the whole tree of class and recursively all of its arguements
    println(t1 == t2) // false

    val t3 = Transaction2("T1", 12.22, "debit", when)
    val t4 = Transaction2("T1", 12.22, "debit", when)
    println(t3 == t4) // true

    val t5 = t1.copy(name = "T5", amount = 100)
    println(t5)

    val amount = t5 match {
        // Replace unused fields with '_' underscore
        //case Transaction2(_, amount, _, _) => amount
        case Transaction2(_, amount, kind, _) if kind == "debit" && amount > 20 => amount - 20
    }
    println(amount)
}