package org.example

class History(val parts: List<String>) {
    private val knight: Fighter = Warrior(name = "Thor")
    private val warrior: Fighter = Knight(name = "Hollow")

    val game = Game(knight, warrior)

    fun play() {
        for (part in parts) {
            println(part)
            println("[ Press Enter to continue... ]")
            readlnOrNull()
        }
        game.fight()
    }
}

interface Fighter {
    val name: String
    var health: Int
    val attack: Int
    val isAlive: Boolean
    fun attackAction(target: Fighter) {
        val attackSuccess = Math.random() > 0.3
        val isCriticalHit = Math.random() > 0.7
        val calculatedAttack = if (isCriticalHit) (attack * 1.3).toInt() else attack
        println("------- $name Attack -------")
        if (!attackSuccess) {
            println("Oops, $name's attack missed!")
            println("${target.name} didn't take any damage")
        } else {
            println("$name attacks! ${if (isCriticalHit) "¡is a Critical hit!" else ""}")
            target.health -= calculatedAttack
            println("${target.name} receive $calculatedAttack point of damage")
        }
        println("${target.name} has ${if (target.health < 0) 0 else target.health} health points left")
        println("------- End of turn -------")
    }
}

open class Warrior(
    override val name: String,
    override var health: Int = 50,
    override val attack: Int = 5
) : Fighter {
    override val isAlive: Boolean
        get() = health > 0
}

class Knight(name: String) : Warrior(name, attack = 7)

class Game(private var player1: Fighter, private var player2: Fighter) {
    fun fight() {
        while (player1.isAlive && player2.isAlive) {
            player1.attackAction(player2)
            if (player2.isAlive) player2.attackAction(player1)
            check()
        }
    }

    private fun check() {
        if (player1.health <= 0 || player2.health <= 0) {
            println("${if (player1.isAlive) player1.name else player2.name} wins")
        } else {
            println("Both can still continue...")
            println("[Press Enter for next round...]")
            readlnOrNull()
        }
    }
}

fun main() {
    val historyParts = listOf(
        """
        One day, on a typical spring afternoon, Sir Ronald has been looking around his land, riding a horse. Nothing foretold troubles, when suddenly Sir Ronald heard a scream for help, coming from somewhere nearby:
        - "Help! Help!" - shouted a piercing young girl's voice.
    """.trimIndent(),
        """
        As a true knight, Sir Ronald couldn’t stay away and went to the lady’s rescue. Rushing in the direction from which the cry came, he saw a carriage. The girl peered out the window hoping to see someone who could help her.
    """.trimIndent(),
        """
        - "Stop!"- ordered Sir Ronald to the coachman. - "By what right are you on my land?"
        The coachman didn’t get a chance to open his mouth, as his master came out of the carriage.
    """.trimIndent(),
        """
        - "My respects, noble sir. I had no idea that this land is yours. My bride and I were just going to my estate, not wanting to give anyone any trouble. "
        - "A flat-out lie! I'm not his bride!" - the girl exclaimed from the window.
    """.trimIndent(),
        """
        - "Explain yourself, Sir. What does that mean?",- said Sir Ronald.
        - "Of course. The king of a neighboring country has promised his daughter and half his kingdom to the one who’ll save her from the outlaws who took her. I’ve defeated those bastards and now I’m taking the princess to her father. "
    """.trimIndent(),
        """
        - "It's not true! They were in on it together They’ve kidnapped me on his order! I saw how he paid them a bag of gold!" - The princess didn’t stop taking for a second, trying to quickly describe the situation to the miraculously appeared savior.
    """.trimIndent(),
        """
        - "Such behavior is unworthy of a knight! Prepare to die!",- exclaimed Sir Ronald, drawing his sword. 
        - "Ha-ha-ha, simple-hearted nobleman! We’ll see about that..."
    """.trimIndent(),
    )
    val history = History(historyParts)

    history.play()
}