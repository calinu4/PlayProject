package models

import play.api.data._
import play.api.data.Forms._

import scala.collection.mutable.ArrayBuffer

case class Item(name: String, description: String,maker:String,warranty:String,price:Int,discount:Int,seller:String)

object Item {

  val createItemForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "description"->nonEmptyText,
      "maker"->nonEmptyText,
      "warranty"->nonEmptyText,
      "price" -> number,
      "discount"->number(min=0,max=20),
      "seller"->nonEmptyText
    )(Item.apply)(Item.unapply)
  )

  val items = ArrayBuffer(
    Item("Item 1", "Description 1","Sony","1 Year",25,2,"PcWorld"),
    Item("Item 2", "Description 2","LG","2 Years",356,25,"Maplin"),
    Item("Item 3", "Description 3","Apple","1 Year",700,30,"Apple Store")
  )

  def getItem(name:String):Int ={
    for(item<-items)if(item.name==name)return items.indexOf(item)
    return -1
  }
  def deleteItem(name:String)={
    val index=getItem(name)
    if(index>=0)items.remove(index)
  }

}