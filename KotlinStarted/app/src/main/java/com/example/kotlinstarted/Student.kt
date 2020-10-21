package com.example.kotlinstarted

class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age), Study {
//    var sno = ""
//    var grade = 0

    init {
        println("sno is $sno")
        println("grade is $grade")
        println("name is $name")
        println("age is $age")
    }

    constructor() : this("", 0)
    constructor(name: String, age: Int) : this("", 0, name, age)

    override fun readBooks() {
        println("$name is reading.")
    }

    override fun doHomework() {
        println("$name is doHomeworking.")
    }

}