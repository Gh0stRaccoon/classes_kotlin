package org.example

/*
* Clickable interface
*/

interface Clickable {
    fun click()
    fun show() = println("I'm clickable")
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "have" else "don't have"} focus")
    fun show() = println("I'm focusable")
}

class Button : Clickable {
    override fun click() {
        println("Click on button")
    }
}

open class RichButton : Clickable, Focusable {
    override fun click() {
        println("I'm a ${this.javaClass.simpleName} button")
    }

    override fun show() {
        println("I'm a rick button showing")
        super<Clickable>.show() // getting behavior of clickable interface
        super<Focusable>.show() // getting behavior of focusable interface
    }

    fun disable() {}

    /*
        * if you want to children animate on different way, you should declare explicitly this function such "open"
        * this make the function Overridable
    */
    open fun animate() {}
}

class ThemedButton : RichButton() {
    override fun animate() {
//        super.animate() // this is for keep the original behavior
        println("My custom animation after parent animation") // adding a new behavior
    }
}

fun main() {
//    Button().click()
//    Button().show()

    RichButton().animate()
    RichButton().show()
    RichButton().disable()
}