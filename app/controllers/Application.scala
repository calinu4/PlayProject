package controllers

import play.api._
import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Hello World")).withCookies(Cookie("theme","blue")).withSession("user"->"Calin")
  }

  def secondpage = Action {
    request=>request.session.get("user").map{user=>Ok(views.html.second("Hello "+user))}.getOrElse(Unauthorized{"You are not logged in"})

  }

  def hello(name:String)=Action{
    Ok(views.html.second(name))
  }

  def redir=Action{
    Redirect("/name?name=Calin")
  }

  def index1 =Action{
    Redirect(routes.Application.plannedUpdates())
  }

  def plannedUpdates=Action{
    Ok("Planned techincal updates are in progress")
  }

  def defaultVal(name:String="Default")=Action {
    Ok(views.html.second(name))
  }

def optionalVal(title:Option[String])=Action{
    Ok(views.html.second(title.getOrElse("No value")))

}

  def index2(name:String) = TODO

  def show(id:Long)=Action{
    Ok("The id of the item is: "+id)
  }
}