package controllers

import play.api._
import play.api.mvc._
import models.Customer
import play.api.data._
import play.api.data.Forms._
import play.api.Play.current
import javax.inject.Inject
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi
class Application @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport{
//Day one exercises mainly implemented here
  def index = Action {
    Ok(views.html.index("Hello World")).withCookies(Cookie("theme","blue")).withSession("user"->"Calin")
  }

  def secondpage = Action {
    request=>request.session.get("user").map{user=>Ok(views.html.second("Hello "+user+" This is a sessions example!"))}.getOrElse(Unauthorized{"You are not logged in"})

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
    Ok("The id of the item is: "+ id)
  }

  def customerForm = Form(mapping("Customer Name" -> nonEmptyText,
    "Credit Limit" -> number(min = 0, max = 100000) )(Customer.apply)(Customer.unapply))

  def index3 = Action {
    Ok(views.html.index3(customerForm))
  }

  def createCustomer = Action { implicit request =>
    customerForm.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.index3(formWithErrors)),
      customer => Ok(views.html.submittedOk(customer)))
  }
}