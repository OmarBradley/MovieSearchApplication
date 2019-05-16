package omarbradley.com.domain

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class Runner : StringSpec({


    "same"{
        Item(1, "1") shouldBe Item(1, "1")

    }


})


data class Item(
    val a: Int,
    val b: String
)
