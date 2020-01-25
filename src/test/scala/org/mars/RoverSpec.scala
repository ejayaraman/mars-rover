package org.mars

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class RoverSpec extends AnyWordSpec with Matchers {

  "Rover" should {
    "be available in the provided position" in {
      val rover = new Rover(RoverPosition(1, 2, CardinalDirection("N")), new Plateau(5, 5))
      rover.initialPosition shouldBe RoverPosition(1, 2, CardinalDirection("N"))
    }

    "move to the new position following the directions" in {
      val rover = new Rover(RoverPosition(1, 2, CardinalDirection("N")), new Plateau(5, 5))
      val newRoverPosition = rover.move("LMLMLMLMM")
      newRoverPosition shouldBe RoverPosition(1, 3, CardinalDirection("N"))
    }

    "move to the new position following another directions" in {
      val rover = new Rover(RoverPosition(3, 3, CardinalDirection("E")), new Plateau(5, 5))
      val newRoverPosition = rover.move("MMRMMRMRRM")
      newRoverPosition shouldBe RoverPosition(5, 1, CardinalDirection("E"))
    }

    "throw exception when the new position is outside grid" in {
      val rover = new Rover(RoverPosition(5, 5, CardinalDirection("E")), new Plateau(5, 5))
      intercept[RoverException](rover.move("M"))
    }

    "throw exception when the direction to move is not one of L, R, M" in {
      val rover = new Rover(RoverPosition(5, 5, CardinalDirection("E")), new Plateau(6, 6))
      intercept[RoverException](rover.move("A"))
    }

  }
}
