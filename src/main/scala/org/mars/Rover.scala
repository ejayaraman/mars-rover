package org.mars

class Rover(val initialPosition: RoverPosition, plateau: Plateau) {

  def move(movingInstructions: String): RoverPosition = {
    var currentPosition: RoverPosition = initialPosition

    for (instruction <- movingInstructions) {
      currentPosition = instruction.toString match {
        case "L" => turnLeft(currentPosition)
        case "R" => turnRight(currentPosition)
        case "M" => moveForward(currentPosition)
      }
    }
    currentPosition
  }

  private def turnLeft(currentPosition: RoverPosition): RoverPosition = {
    val newPosition = currentPosition.direction match {
      case "N" => currentPosition.copy(direction = "W")
      case "S" => currentPosition.copy(direction = "E")
      case "E" => currentPosition.copy(direction = "N")
      case "W" => currentPosition.copy(direction = "S")
    }

    isNewPositionValid(newPosition)
  }

  private def turnRight(currentPosition: RoverPosition): RoverPosition = {
    val newPosition = currentPosition.direction match {
      case "N" => currentPosition.copy(direction = "E")
      case "S" => currentPosition.copy(direction = "W")
      case "E" => currentPosition.copy(direction = "S")
      case "W" => currentPosition.copy(direction = "N")
    }
    isNewPositionValid(newPosition)
  }

  private def moveForward(currentPosition: RoverPosition): RoverPosition = {
    val newPosition = currentPosition.direction match {
      case "N" => currentPosition.copy(y = currentPosition.y + 1)
      case "S" => currentPosition.copy(y = currentPosition.y - 1)
      case "E" => currentPosition.copy(x = currentPosition.x + 1)
      case "W" => currentPosition.copy(x = currentPosition.x - 1)
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

case class RoverPosition(x: Int, y: Int, direction: String)

case class RoverException(message: String) extends RuntimeException(message)
