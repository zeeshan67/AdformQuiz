
package com.adform.quiz

object Main {
  
 def main(args:Array[String]):Unit = { 
 
  if (args.isEmpty) throw new IllegalArgumentException("Please provide both harvest.csv and prices.csv path for deriving the result.")
   
  val harvestFileSrc = io.Source.fromFile(args(0))
  val pricesFileSrc = io.Source.fromFile(args(1))

  val harvestInput = harvestFileSrc.getLines().drop(1).map(x => (x.split(",")(0),x.split(",")(1),x.split(",")(2),x.split(",")(3).toDouble)).toList
  val priceInput = pricesFileSrc.getLines().drop(1).map(x => (x.split(",")(0),x.split(",")(1),x.split(",")(2).toDouble)).toList
  
  val combinedList = harvestInput.map(value => 
    (value._1,s"${value._2.split("-")(1)}${value._2.split("-")(0)}",value._3,value._4,priceInput.filter(price => price._1 == value._3 && price._2 == value._2).headOption.map(_._3).getOrElse(0.0)))
  
  println("*"*150)  
  for (result <- combinedList.groupBy(x => (x._1,x._2)).map(x => (x._1._1,x._1._2,x._2.map(_._4).reduce(_+_))).groupBy(_._2).map(value => value._2.maxBy(_._3))) {
      println(s"Best gatherer in terms of amount of fruits gathered in the month ${result._2} is ${result._1}")
  }
  
  println("*"*150)
  
  for (result <- combinedList.groupBy(x => (x._1,x._3)).map(x => (x._1._1,x._1._2,x._2.map(_._4).reduce(_+_))).groupBy(_._2).map(value => value._2.maxBy(_._3))) {
      println(s"Employee ${result._1} better at gathering ${result._2} ")
  }
  
  println("*"*150)
  
  println(s"Best earning fruit overall is - ${combinedList.map(x => (x._3,x._4*x._5)).groupBy(_._1).map(values => (values._1,values._2.map(_._2).reduce(_+_))).maxBy(_._2)._1}")
  
  println("*"*150)  
  
  for (result <- combinedList.map(x => (x._3,x._2,x._4*x._5)).groupBy(x => (x._1,x._2)).map(value => (value._1._1,value._1._2,value._2.map(_._3).reduce(_+_))).groupBy(_._2).map(x => x._2.maxBy(_._3))) {
     println(s"Best earning fruit for the month of ${result._2} is - ${result._1}")
  }
  
  println("*"*150)
  println(s"Least earning fruit overall is - ${combinedList.map(x => (x._3,x._4*x._5)).groupBy(_._1).map(values => (values._1,values._2.map(_._2).reduce(_+_))).minBy(_._2)._1}")

  println("*"*150)
  for (result <- combinedList.map(x => (x._3,x._2,x._4*x._5)).groupBy(x => (x._1,x._2)).map(value => (value._1._1,value._1._2,value._2.map(_._3).reduce(_+_))).groupBy(_._2).map(x => x._2.minBy(_._3))) {
     println(s"Least earning fruit for the month of ${result._2} is - ${result._1}")
  }
  
  
  println("*"*150)
  println(s"Gatherer contributed most income is - ${combinedList.map(x => (x._1,x._4*x._5)).groupBy(_._1).map(values => (values._1,values._2.map(_._2).reduce(_+_))).maxBy(_._2)._1}")
  
  println("*"*150)
  for (result <- combinedList.map(x => (x._1,x._2,x._4*x._5)).groupBy(x => (x._1,x._2)).map(value => (value._1._1,value._1._2,value._2.map(_._3).reduce(_+_))).groupBy(_._2).map(x => x._2.maxBy(_._3))) {
     println(s"Gatherer contributed most income for the month of ${result._2} is - ${result._1}")
  }
  
  println("*"*150)
  
  println(s"Gatherer contributed least income is - ${combinedList.map(x => (x._1,x._4*x._5)).groupBy(_._1).map(values => (values._1,values._2.map(_._2).reduce(_+_))).minBy(_._2)._1}")
  
  println("*"*150)
  for (result <- combinedList.map(x => (x._1,x._2,x._4*x._5)).groupBy(x => (x._1,x._2)).map(value => (value._1._1,value._1._2,value._2.map(_._3).reduce(_+_))).groupBy(_._2).map(x => x._2.minBy(_._3))) {
     println(s"Gatherer contributed least income for the month of ${result._2} is - ${result._1}")
  }
  
  println("*"*150)
 } 
}