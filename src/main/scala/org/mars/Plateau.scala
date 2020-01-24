package org.mars

class Plateau(val gridSizeX: Int, val gridSizeY:Int) {
  def isValidGridValue(xValue: Int, yValue: Int): Boolean = xValue <= gridSizeX && yValue <= gridSizeY
}
