package com.example.kotlinstarted.kt

open class Person(val name: String, val age: Int ) {
//    var name = ""
//    var age = 0

    fun eat() {
        println("$name is eating. He is $age years old.")
    }

    override fun toString(): String {
        println("Person(name='$name', age=$age)")
        return "Person(name='$name', age=$age)"
    }

}