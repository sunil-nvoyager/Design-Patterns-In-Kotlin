import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

//Based on: http://stackoverflow.com/a/13030163/361832

interface Plant

class OrangePlant : Plant

class ApplePlant : Plant

abstract class PlantFactory {

    abstract fun makePlant(): Plant

    companion object {
        
        inline fun <reified T : Plant> createFactory(): PlantFactory =
            when (T::class) {
                OrangePlant::class -> OrangeFactory()
                ApplePlant::class -> AppleFactory()
                else -> throw IllegalArgumentException()
            }
    }
}

class AppleFactory : PlantFactory() {
    /**
     * Creates an instance of a Plant, specifically an ApplePlant.
     *
     * This method overrides the base implementation to provide a specific type of Plant.
     *
     * @return An instance of [ApplePlant].
     *
     * @throws UnsupportedOperationException if the operation is not supported.
     *
     * ## Example
     * ```
     * val plantFactory = PlantFactory()
     * val applePlant = plantFactory.makePlant()
     * println(applePlant) // Output: ApplePlant instance
     * ```
     */
    
    override fun makePlant(): Plant = ApplePlant()
}

class OrangeFactory : PlantFactory() {
    /**
     * Creates an instance of the `OrangePlant` class.
     *
     * This method overrides the `makePlant` function to return a specific type of plant,
     * which in this case is an `OrangePlant`. The `OrangePlant` is a subclass of `Plant`
     * and represents a specific type of plant that can be created by this method.
     *
     * @return An instance of `OrangePlant`.
     *
     * @throws IllegalStateException if the plant cannot be created due to some internal state.
     *
     * ## Example
     * ```
     * val plantFactory: PlantFactory = MyPlantFactory()
     * val orangePlant: Plant = plantFactory.makePlant()
     * println(orangePlant) // Outputs the details of the created OrangePlant
     * ```
     */
    
    override fun makePlant(): Plant = OrangePlant()
}


class AbstractFactoryTest {
    /**
     * Test the Abstract Factory pattern by creating an instance of `OrangePlant`
     * using the `PlantFactory`. This method verifies that the factory correctly
     * produces an instance of the specified plant type.
     *
     * @throws IllegalArgumentException if the factory cannot create an instance
     *         of the specified plant type.
     *
     * Example usage:
     * ```
     * @Test
     * fun testAbstractFactory() {
     *     val plantFactory = PlantFactory.createFactory<OrangePlant>()
     *     val plant = plantFactory.makePlant()
     *     println("Created plant: $plant")
     *
     *     assertThat(plant).isInstanceOf(OrangePlant::class.java)
     * }
     * ```
     */

    @Test
    fun `Abstract Factory`() {
        
        val plantFactory = PlantFactory.createFactory<OrangePlant>()
        val plant = plantFactory.makePlant()
        println("Created plant: $plant")

        assertThat(plant).isInstanceOf(OrangePlant::class.java)
    }
}
