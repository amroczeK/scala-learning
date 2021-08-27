import java.time.LocalDateTime

// Sealed trait is only accessible within the file it's declared
// This will tell the Scala compiler to only look for 'kinds' in this file
// Line 6 to 10 limits the posibilities of what 'Kind' can have, this is known as SUM TYPE - ADDT category
// Debit, Credit and Refund are the ONLY possible cases for the base type 'Kind', isA relationship
sealed trait Kind
case object Debit extends Kind // Case object of Debit, makes Debit a SINGLETON instance
case object Credit extends Kind
case object Refund extends Kind

// Implementations auto generated behind the scenes by default for case classes,
// e.g. 'apply', 'toString', 'equals', 'hash' and 'copy' methods
// Case classes also support PATTERN MATCHING
case class Transaction2(name: String, amount: Double, kind: Kind, when: LocalDateTime)

object TransactionRunner2 extends App {
    private val when = LocalDateTime.now
    // Creating a new instance of a case class doesn't require using 'new' keyword
    // because case classes add a factory method with the name of the class
    // This means you don't have to create an 'apply' method to achieve the same a below
    // Also the paramaters in the case class implicity get a 'val' prefix so they are maintained
    // as fields and immutable
    val t1 = Transaction2("T1", 12.22, Debit, LocalDateTime.now)
    println(t1.toString)

    val t2 = Transaction2("T2", 12.23, Credit, LocalDateTime.now)
    // Case class equals implementation compares the whole tree of class and recursively all of its arguements
    println(t1 == t2) // false

    val t3 = Transaction2("T1", 12.22, Debit, when)
    val t4 = Transaction2("T1", 12.22, Debit, when)
    println(t3 == t4) // true

    val t5 = t1.copy(name = "T5", amount = 100)
    println(t5)

    val t6 = Transaction2("T6", 155.22, Refund, when)

    val amount = t5 match {
        // Replace unused fields with '_' underscore
        //case Transaction2(_, amount, _, _) => amount
        case Transaction2(_, amount, kind, _) if kind == Debit && amount > 20 => amount - 20
        case t: Transaction2 => t.amount // default case
    }
    println(amount)

    def getMessage(kind: Kind): String = kind match {
        case Debit => "Debit Transaction"
        case Credit => "Credit Transaction"
        case Refund => "Refund Transaction"
    }
    println(getMessage(t1.kind))
    println(getMessage(t6.kind))
}