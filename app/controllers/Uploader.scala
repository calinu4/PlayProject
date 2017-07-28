package controllers

import java.io.{ByteArrayOutputStream, File}
import javax.imageio.ImageIO
import javax.inject.Inject

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64
import models.Item
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._

class Uploader @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {
  val initialpath="C:/Users/Administrator/Desktop/toUploadTo/bot.png"
  def index(result:String) = Action {
    Ok(views.html.image(returnBytedImage(result)))
  }


  def upload = Action(parse.multipartFormData) { implicit request =>
    val uploadService: UploadService = UploadService
    val result = uploadService.uploadFile(request)
    Redirect (routes.Uploader.index (result) )
  }


  def returnBytedImage(path:String): String = {
    try {
      val image = ImageIO.read(new File(path))
      val baos = new ByteArrayOutputStream
      ImageIO.write(image, "png", baos)
      val res = baos.toByteArray
      val encodedImage = Base64.encode(baos.toByteArray)
      System.out.println("encoded image " + encodedImage)
      return encodedImage
    } catch {
      case e: Exception =>
        e.printStackTrace()
        System.out.println("Error occured")
    }
    "Byting failed"
  }


}