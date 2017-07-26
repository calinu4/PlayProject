package controllers

import play.api._
import play.api.mvc._
import models.Item
import play.api.data._
import play.api.data.Forms._
import play.api.Play.current
import javax.inject.Inject
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi
class App @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport{
  def index = Action {
    Ok(views.html.index(""))
  }

  def listItems = Action { implicit request =>
    Ok(views.html.listItems(Item.items))
  }

  def seeItem=Action{implicit request =>
  Ok(views.html.createitem(Item.createItemForm))
}
  def createItem = Action { implicit request =>
    val formValidationResult = Item.createItemForm.bindFromRequest
    formValidationResult.fold({ formWithErrors =>
      BadRequest(views.html.createitem(formWithErrors))
    }, { item =>
      Item.items.append(item)
      Redirect(routes.App.listItems())
    })
  }

  val deleteItemForm = Form(
    "name" -> nonEmptyText
    )

  def seeDelete=Action{implicit request =>
    Ok(views.html.deleteitem(deleteItemForm))
  }
  def deleteItem = Action { implicit request =>
    val formValidationResult = deleteItemForm.bindFromRequest
    formValidationResult.fold({ formWithErrors =>
      BadRequest(views.html.deleteitem(formWithErrors))
    }, { name =>
      Item.deleteItem(name)
      Redirect(routes.App.listItems())
    })
  }

  def seeUpdate=Action{implicit request =>
    Ok(views.html.updateitem(Item.createItemForm)(Item.items))
   }

  def updateItem(index:Int) = Action { implicit request =>
    val formValidationResult = Item.createItemForm.bindFromRequest
    formValidationResult.fold({ formWithErrors =>
      BadRequest(views.html.updateitem(formWithErrors)(Item.items))
    }, { item =>
      Item.items.update(index,item)
      Redirect(routes.App.listItems())
    })
  }

}