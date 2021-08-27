package dev.amroczek

// Every package only gets ONE package object
// Any method or definition added here will be available to the entire package
package object entities {
  def sum(a: Int, b: Int) = a + b

  // Alias of User - must be imported in packages outside of entities pkg
  val AppUser = dev.amroczek.entities.User
}
