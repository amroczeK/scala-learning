// Nested package statements differ from changes package statements
// because you can create more than one package within the same file
package dev {
    package amroczek2 {
        package entities {
            case class AnotherUser(f: String, l: String)
        }
    }
}


package main {
    import dev.amroczek2.entities.AnotherUser
    object main extends App {
        val u: AnotherUser = AnotherUser("Tim", "Cook")
        println(u)
    }
}