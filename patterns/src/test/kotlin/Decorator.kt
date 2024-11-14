import org.junit.jupiter.api.Test

interface CoffeeMachine {
    fun makeSmallCoffee()
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
    /**
     * Tests the functionality of the Decorator design pattern by creating an enhanced coffee machine
     * that decorates a normal coffee machine with additional features.
     *
     * This test demonstrates the following behaviors:
     * - Non-overridden behavior: The enhanced coffee machine can make small coffee using the normal machine's implementation.
     * - Overridden behavior: The enhanced coffee machine can make large coffee with its own implementation.
     * - Extended behavior: The enhanced coffee machine can make coffee with milk, which is an additional feature.
     *
     * @throws IllegalArgumentException if the coffee size is invalid or if the machine is not properly initialized.
     * @throws UnsupportedOperationException if the requested operation is not supported by the enhanced machine.
     *
     * Example usage:
     * ```
     * val normalMachine = NormalCoffeeMachine()
     * val enhancedMachine = EnhancedCoffeeMachine(normalMachine)
     *
     * // Making small coffee using normal machine's implementation
     * enhancedMachine.makeSmallCoffee()
     *
     * // Making large coffee using enhanced machine's implementation
     * enhancedMachine.makeLargeCoffee()
     *
     * // Making coffee with milk using enhanced machine's extended behavior
     * enhancedMachine.makeCoffeeWithMilk()
     * ```
     */
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
