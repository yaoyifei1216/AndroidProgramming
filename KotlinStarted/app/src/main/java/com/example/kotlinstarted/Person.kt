package com.example.kotlinstarted

class Person {
    var name = ""
    var age = 0

    fun eat() {
        println(name + " is eating. He is " + age + " years old.")
    }

    override fun toString(): String {
        println("Person(name='$name', age=$age)")
        return "Person(name='$name', age=$age)"
    }

}