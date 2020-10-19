package com.example.kotlinstarted

import kotlin.math.max

fun main() {
    val a = 10
    val b = 40
    val max = maxValue2(a, b)
    //forin()
    //println(max)
    var p = Person()
    p.age = 23
    p.name = "yaoyifei"
    p.toString()
    p.eat()
}

private fun maxValue(a: Int, b: Int): Int {
    return max(a, b)
}


private fun maxValue1(a: Int, b: Int): Int {
    var value = 0
    if (a > b) {
        value = a
    } else {
        value = b
    }
    return value
}

private fun maxValue2(a: Int, b: Int): Int {
    val value = if (a > b) {
        a
    } else {
        b
    }
    return value
}

fun getScore(name: String) = if (name == "Tom") {
    86
} else if (name == "Jim") {
    77
} else if (name == "Jack") {
    95
} else if (name == "Lily") {
    100
} else {
    0
}

fun forin() {
    for (i in 0..10) {
        println(i)
    }

    for (i in 0 until 10) {
        println(i)
    }

    for (i in 0 until 11 step 2) {
        println(i)
    }

    for (i in 10 downTo 0) {
        println(i)
    }
}