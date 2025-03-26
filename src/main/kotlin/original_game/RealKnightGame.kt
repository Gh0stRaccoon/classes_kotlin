package org.example.original_game

interface Fighter {
    var health: Int
    val attack: Int
    val isAlive: Boolean
    fun attack(target: Fighter)
}

open class Warrior(
    override var health: Int = 50,
    override val attack: Int = 5,
) : Fighter {
    override val isAlive: Boolean
        get() = health > 0

    override fun attack(target: Fighter) {
        println("Attack!!")
        target.health -= attack
        println("target loose $attack health points!")
        println("target now has ${target.health} health points.")
    }
}

class Knight : Warrior(attack = 7)

fun fight(fighter1: Fighter, fighter2: Fighter): Boolean {
    var fighterA = fighter1
    var fighterB = fighter2

    while (fighterA.isAlive && fighterB.isAlive) {
        fighterA.attack(fighterB)
        fighterA = fighterB.also { fighterB = fighterA }
    }
    return fighter1.isAlive
}

class Army {
    val listOfUnits = mutableListOf<Fighter>()

    val units: Int
        get() = listOfUnits.size

    val soldier: Fighter
        get() = listOfUnits[0]

    fun killUnit() {
        println("Killing the unit")
        listOfUnits.removeFirst()
        println("units remaining: $units")
    }

    fun addUnits(numberOfUnits: Int, typeOfUnit: () -> Fighter) {
        repeat(numberOfUnits) { listOfUnits.add(typeOfUnit()) }
    }
}

fun fight(army1: Army, army2: Army): Boolean {
    while (army1.units > 0 && army2.units > 0) {
        println("Soldier attack to target's soldier")
        val isArmy1Winner = fight(army1.soldier, army2.soldier)
        if (isArmy1Winner) {
            army2.killUnit()
        } else {
            army1.killUnit()
        }
    }
    return army1.units > 0
}

fun main() {
    val myArmy = Army()
    myArmy.addUnits(3) { Knight() }

    val enemyArmy = Army()
    enemyArmy.addUnits(33) { Warrior() }

    val army3 = Army()
    army3.addUnits(20) { Warrior() }
    army3.addUnits(5) { Knight() }

    val army4 = Army()
    army4.addUnits(30) { Warrior() }

    check(!fight(myArmy, enemyArmy))
    check(!fight(army3, army4))
    println("OK")
}