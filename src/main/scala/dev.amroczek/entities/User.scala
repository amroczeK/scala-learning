package dev.amroczek.entities

final case class User(val firstName: String, lastName: String) {
    // Access controls using qualifier
    // Makes member accessible only to classes in entities package
    // Can add exposing class, package or singleton object
    private[amroczek] def getId: String = ???
    //private[entities] def getId: String = ???
    //private[this] def getId: String = ??? // Object private
}