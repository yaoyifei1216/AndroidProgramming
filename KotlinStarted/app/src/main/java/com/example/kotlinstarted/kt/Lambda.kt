package com.example.kotlinstarted.kt

fun main() {
    testList()
}

fun testList() {
    val list = listOf("hello", "world", "kotlin") //listOf创建一个不可变集合,该集合只能用于读取，无法对集合进行添加、修改或删除操作。
    for (str in list) {
        println(str)
    }

    val list1 = mutableListOf("hello", "world", "kotlin") //mutableListOf创建一个可变集合,可以对集合进行添加、修改或删除操作。
    list1.add("java")
    for (str1 in list1) {
        println(str1)
    }

    val set = setOf("Apple", "Banana", "Orange", "Pear", "Grape")//set和list用法一样,只是set是无序的
    for (fruit in set) {
        println(fruit)
    }

    val map = HashMap<String, Int>()//声明map的方法
    map["Apple"] = 1
    map["Banana"] = 2
    map["Orange"] = 3

    val map1 = mapOf("Apple" to 1, "Banana" to 2, "Orange" to 3)//kotlin式声明map
    for ((fruit, price) in map1) {
        println("Fruit is $fruit Price is $price")
    }
}