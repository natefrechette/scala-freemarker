package com.queue.freemarker

import java.io.File

import _root_.freemarker.cache._
import _root_.freemarker.template._
import _root_.freemarker.template.Configuration.VERSION_2_3_23
import com.typesafe.scalalogging.LazyLogging

/*!# Default FreeMarker Configuration

The default FreeMarker configuration implies following:

* templates are loaded from `${webapp-root}/templates directory` or,
if failed, from application classpath;
* all template errors result in exception to be thrown to controller;
* character encoding defaults to UTF-8;
* the `ScalaObjectWrapper` is used for Scala core types.

You can alter template loading dynamically using `addLoader` and `setLoaders`
methods, but in general this is only acceptable in initialization code. In any
case make sure you know what you are doing first.
*/
class DefaultConfiguration(templatePath: String) extends Configuration(VERSION_2_3_23) with LazyLogging {

  // Loaders
  protected var _loaders: Seq[TemplateLoader] = Nil
  def loaders = _loaders
  def addLoader(loader: TemplateLoader): this.type = {
    _loaders ++= List(loader)
    setTemplateLoader(new MultiTemplateLoader(loaders.toArray))
    this
  }

  def setLoaders(ldrs: TemplateLoader*): this.type = {
    _loaders = ldrs.toList
    setTemplateLoader(new MultiTemplateLoader(loaders.toArray))
    this
  }

  // Defaults
  setObjectWrapper(new ScalaObjectWrapper())
  setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER)
  setDefaultEncoding("utf-8")
  addLoader(new FileTemplateLoader(new File(templatePath)))

}
