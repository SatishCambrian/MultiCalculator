package org.example.multicalculator

import getPlatform

class Calculator {

   private val platform = getPlatform()

   fun Add(left: Double, right: Double): Double{
   return left + right
   }

   fun Subtract( left: Double, right: Double): Double {
      return left-right
   }

   fun Multiply(left:Double, right: Double): Double{
      return left * right
   }

   fun Divide(left: Double, right:Double): Double{
      return left / right
   }
}