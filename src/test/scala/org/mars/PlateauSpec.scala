package org.mars

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlateauSpec extends AnyWordSpec with Matchers {

  "Plateau" should {
    "initialize a new plateau with the provided grid values" in {
      val plateau = new Plateau(5, 5)
      plateau.gridSizeX shouldBe 5
      plateau.gridSizeY shouldBe 5
    }

    "return false if the provided grid value is outside the grid size" in {
      val plateau = new Plateau(5, 5)
      plateau.isValidGridValue(6, 6) shouldBe false
    }

    "return true if the provided grid value is within the grid size" in {
      val plateau = new Plateau(5, 5)
      plateau.isValidGridValue(4, 4) shouldBe true
    }
  }
}
