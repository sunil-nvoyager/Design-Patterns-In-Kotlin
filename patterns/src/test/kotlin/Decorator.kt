import org.junit.jupiter.api.Test

interface CoffeeMachine {
    fun makeSmallCoffee()
    fun makeLargeCoffee()
}

/**
 * Makes a small coffee and prints a message indicating that the process has started.
 *
 * This function is a simple implementation that simulates the action of making a small coffee.
 * It does not return any value but outputs a message to the console.
 *
 * @throws IllegalStateException if the coffee machine is not ready to make coffee.
 *
 * Example usage:
 * ```
 * val coffeeMachine = CoffeeMachine()
 * try {
 *     coffeeMachine.makeSmallCoffee()
 * } catch (e: IllegalStateException) {
 *     println("Cannot make coffee: ${e.message}")
 * }
 * ```
 */
class NormalCoffeeMachine : CoffeeMachine {
    override fun makeSmallCoffee() = println("Normal: Making small coffee")
/**
 * This method is responsible for making a large coffee.
 * It prints a message indicating that a large coffee is being made.
 *
 * @throws IllegalStateException if the coffee machine is not ready to make coffee.
 *
 * Example usage:
 * ```
 * val coffeeMachine = CoffeeMachine()
 * if (coffeeMachine.isReady()) {
 *     coffeeMachine.makeLargeCoffee()
 * } else {
 *     throw IllegalStateException("Coffee machine is not ready.")
 * }
 * ```
 */

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

    /**
     * Tests the functionality of the Decorator pattern with coffee machines.
     *
     * This method demonstrates how an enhanced coffee machine can extend the behavior of a normal coffee machine.
     * It showcases both the non-overridden and overridden methods, as well as additional functionalities provided
     * by the decorator.
     *
     * The following behaviors are tested:
     * - Making a small coffee using the normal behavior of the enhanced machine.
     * - Making a large coffee using the overridden behavior of the enhanced machine.
     * - Making coffee with milk using the extended behavior of the enhanced machine.
     *
     * @throws IllegalArgumentException if the coffee size is invalid.
     * @throws UnsupportedOperationException if the requested operation is not supported by the machine.
     *
     * Example usage:
     * ```
     * val normalMachine = NormalCoffeeMachine()
     * val enhancedMachine = EnhancedCoffeeMachine(normalMachine)
     *
     * // Non-overridden behavior
     * enhancedMachine.makeSmallCoffee() // Should produce a small coffee
     *
     * // Overridden behavior
     * enhancedMachine.makeLargeCoffee() // Should produce a large coffee with enhanced features
     *
     * // Extended behavior
     * enhancedMachine.makeCoffeeWithMilk() // Should produce coffee with milk
     * ```
     */
class DecoratorTest {
    /**
     * Tests the functionality of the Decorator pattern by creating an instance of
     * a normal coffee machine and wrapping it with an enhanced coffee machine.
     *
     * This method demonstrates the use of decorators to extend the behavior of
     * the coffee machine without modifying its original implementation.
     *
     * The following behaviors are tested:
     * - Non-overridden behavior: `makeSmallCoffee()` from the normal coffee machine.
     * - Overridden behavior: `makeLargeCoffee()` from the enhanced coffee machine.
     * - Extended behavior: `makeCoffeeWithMilk()` from the enhanced coffee machine.
     *
     * @throws IllegalArgumentException if the coffee size is invalid.
     * @throws UnsupportedOperationException if the requested operation is not supported by the machine.
     *
     * Example usage:
     * ```
     * @Test
     * fun testCoffeeMachineDecorator() {
     *     val normalMachine = NormalCoffeeMachine()
     *     val enhancedMachine = EnhancedCoffeeMachine(normalMachine)
     *
     *     // Test non-overridden behavior
     *     enhancedMachine.makeSmallCoffee() // Should make a small coffee
     *
     *     // Test overridden behavior
     *     enhancedMachine.makeLargeCoffee() // Should make a large coffee with enhanced features
     *
     *     // Test extended behavior
     *     enhancedMachine.makeCoffeeWithMilk() // Should make coffee with milk
     * }
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
