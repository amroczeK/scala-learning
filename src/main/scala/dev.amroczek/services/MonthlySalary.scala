package dev.amroczek.services

//import dev.amroczek.entities._
import dev.amroczek.entities.{Budget, Transaction, User}
//import dev.amroczek.entities.{Budget, Transaction, User => _} // Makes User unavailable to MonthlySalary
//import dev.amroczek.entities.{Budget, Transaction => BankTransaction} // Renaming import
import dev.amroczek.entities.sum

final case class MonthlySalary(val budget: Budget, val ts: Array[Transaction]) {
    val userId: String = User("Nick", "Jones").getId
    def total: Int = sum(1, 2)
}