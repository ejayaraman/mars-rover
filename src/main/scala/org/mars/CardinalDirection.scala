package org.mars

case class CardinalDirection(direction: String) {

  def spin(spinToDirection: String): CardinalDirection = {
    if ((this.direction == "N" && spinToDirection == "L") || (this.direction == "S" && spinToDirection == "R")) {
      CardinalDirection("W")
    }
    else if ((this.direction == "N" && spinToDirection == "R") || (this.direction == "S" && spinToDirection == "L")) {
      CardinalDirection("E")
    }
    else if ((this.direction == "E" && spinToDirection == "L") || (this.direction == "W" && spinToDirection == "R")) {
      CardinalDirection("N")
    }
    else if ((this.direction == "E" && spinToDirection == "R") || (this.direction == "W" && spinToDirection == "L")) {
      CardinalDirection("S")
    }
    else {
      throw CardinalDirectionException(s"Cannot spin to $spinToDirection from ${this.direction}")
    }
  }
}

case class CardinalDirectionException(message: String) extends RuntimeException(message)