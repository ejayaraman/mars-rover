package org.mars

class Rover(val initialPosition: RoverPosition, plateau: Plateau) {

  def move(movingInstructions: String): RoverPosition = {
    var currentPosition: RoverPosition = initialPosition

    for (instruction <- movingInstructions) {
      currentPosition = instruction.toString match {
        case "L" | "R" =>
          val newDirection = currentPosition.direction.spin(instruction.toString)
          currentPosition.copy(direction = newDirection)
        case "M" => moveForward(currentPosition)
        case _ => throw RoverException(s"Invalid moving instruction: $instruction")
      }
    }
    isNewPositionValid(currentPosition)
  }

  private def moveForward(currentPosition: RoverPosition): RoverPosition = {
    val newPosition = currentPosition.direction match {
      case CardinalDirection("N") => currentPosition.copy(y = currentPosition.y + 1)
      case CardinalDirection("S") => currentPosition.copy(y = currentPosition.y - 1)
      case CardinalDirection("E") => currentPosition.copy(x = currentPosition.x + 1)
      case CardinalDirection("W") => currentPosition.copy(x = currentPosition.x - 1)
    }
    isNewPositionValid(newPosition)
  }

  private def isNewPositionValid(newPosition: RoverPosition): RoverPosition = {
    if (!plateau.isValidGridValue(newPosition.x, newPosition.y)) {
      throw RoverException(s"Invalid Input. Moving to ${(newPosition.x, newPosition.y)} takes Rover outside Plateau")
    }
    newPosition
  }

}

case class RoverPosition(x: Int, y: Int, direction: CardinalDirection)

case class RoverException(message: String) extends RuntimeException(message)
