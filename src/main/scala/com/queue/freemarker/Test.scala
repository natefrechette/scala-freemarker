package com.queue.freemarker

import java.io.StringWriter

object Test extends App {

  val config = new DefaultConfiguration("/Developer/dropsource/scala-freemarker/templates")

  val template = config.getTemplate("template.ftl")

  val data = scala.collection.mutable.Map[String, Object]()
  val json = Map(
    "title" -> "Custom AlertView",
    "message" -> "alert message",
    "value" -> "1",
    "actions" -> Map(
      "button1 label" -> "actionId1",
      "button2 label" -> "actionId2"
    )
  )

  data += ("json" -> json)
  data += ("obj" -> MyObject("nate"))


  // write to string
  val output = new StringWriter
  template.process(data, output)

  val stringResult = output.toString

  println(stringResult)
}

case class MyObject(name: String) {
  def upper = name.toUpperCase

  def lower(name: String) = name.toLowerCase
}
