package dev.amroczek.entities

case class Transaction(val user: User) {
    val userId: String = User("Tom", "Hanks").getId
    def total: Int = sum(1, 2)
}