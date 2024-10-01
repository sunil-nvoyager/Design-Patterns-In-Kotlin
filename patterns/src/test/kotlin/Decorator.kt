import org.junit.jupiter.api.Test

interface CoffeeMachine {
    
    /**
     * Overrides the default behavior to make a large coffee.
     * This method provides an enhanced implementation that outputs a message
     * indicating that a large coffee is being made.
     *
     * @throws IllegalStateException if the coffee machine is not ready to make coffee.
     *
     * Example usage:
     * ```
     * val coffeeMaker = CoffeeMaker()
     * try {
     *     coffeeMaker.makeLargeCoffee()
     * } catch (e: IllegalStateException) {
     *     println("Error: ${e.message}")
     * }
     * ```
     */
    fun makeLargeCoffee()
    
}

class NormalCoffeeMachine : CoffeeMachine {
    
    override fun makeSmallCoffee() = println("Normal: Making small coffee")

    override fun makeLargeCoffee() = println("Normal: Making large coffee")
    
}

//Decorator:
class EnhancedCoffeeMachine(private val coffeeMachine: CoffeeMachine) : CoffeeMachine by coffeeMachine {

    // overriding behaviour
    override fun makeLargeCoffee() {
        println("Enhanced: Making large coffee")
    }

    // extended behaviour
    fun makeCoffeeWithMilk() {
        println("Enhanced: Making coffee with milk")
        coffeeMachine.makeSmallCoffee()
        addMilk()
    }

    private fun addMilk() {
        println("Enhanced: Adding milk")
    }
    
}

class DecoratorTest {

    @Test
    fun Decorator() {
        val normalMachine = NormalCoffeeMachine()
        val enhancedMachine = EnhancedCoffeeMachine(normalMachine)

        // non-overridden behaviour
        enhancedMachine.makeSmallCoffee()
        // overridden behaviour
        enhancedMachine.makeLargeCoffee()
        // extended behaviour
        enhancedMachine.makeCoffeeWithMilk()
        
    }
    
}
