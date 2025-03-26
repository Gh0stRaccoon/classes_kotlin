package org.example

class Student(val name: String, val lastname: String, var age: String, var grade: String) {
    val fullName: String
        get() {
            val fullName: String = "$name $lastname"
            return fullName
        }

    fun incrementAge() {
        var newAge = age.toInt()
        newAge++
        age = newAge.toString()
    }

    fun updateGrade(newGrade: String) {
        grade = newGrade
        println("$name's grade is: $grade")
    }
}

fun main() {
    val student = Student(name = "Pepe", lastname = "Gutierrez", age = "15", grade = "3th B")

    println("${student.fullName} have ${student.age} years old.")
    student.incrementAge()
    student.incrementAge()
    student.incrementAge()
    student.incrementAge()
    println("${student.fullName} have ${student.age} years old.")
}

// package testing
fun Student.validateStudent() {

    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Student is invalid: field $fieldName is empty")
        }
    }

    validate(name, "name")
    validate(lastname, "lastname")
}

fun saveStudent(student: Student) {
    student.validateStudent()

    // logic to save on DB
}

//fun saveStudent(student: Student) {
////    if (student.name != "" && student.age >= 0 && student.grade != "" && student.lastname != "") {
////        //logic to save on DB
////        println("Saving student...")
////    } else {
////        throw Exception("Invalid student")
////    }
//
//    fun validateStudent(value: String, fieldName: String) {
//        if(value.isEmpty()) {
//            throw IllegalArgumentException("Can't save student: empty field $fieldName")
//        }
//    }
//
//    validateStudent(student.grade, fieldName = "grade")
//    validateStudent(student.name, fieldName = "name")
//}
