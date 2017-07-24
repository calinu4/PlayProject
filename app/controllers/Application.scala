package controllers

import play.api._
import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Hello World"))
  }

  def secondpage = Action {
    Ok(views.html.second("World"))
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
  title match{
    case s=>Ok(views.html.second(s.toString))
    case None=>Ok(views.html.second("No values"))
  }
}

  def index2(name:String) = TODO
}