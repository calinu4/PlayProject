package controllers

import play.api._
import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Hello world"))
  }

  def page = Action {
    Ok(views.html.second())
  }

}