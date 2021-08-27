package dev.amroczek.services

//import dev.amroczek.entities._
import dev.amroczek.entities.{Budget, Transaction}
//import dev.amroczek.entities.{Budget, Transaction, User => _} // Makes User unavailable to MonthlySalary
//import dev.amroczek.entities.{Budget, Transaction => BankTransaction} // Renaming import

final case class MonthlySalary(val budget: Budget, val ts: Array[Transaction])