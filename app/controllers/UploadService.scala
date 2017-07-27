package controllers

import play.api.libs.Files.TemporaryFile
import play.api.mvc.MultipartFormData
import play.api.mvc.Request

object UploadService extends UploadService

trait UploadService {

  def uploadFile(request: Request[MultipartFormData[TemporaryFile]]): String = {
    println( request.body.file("file").get.ref)
    request.body.file("file").map { picture =>
      println(picture.ref.file.getAbsoluteFile)
      import java.io.File
      val filename = picture.filename
      val contentType = picture.contentType
      picture.ref.moveTo(new File(s"C:/Users/Administrator/Desktop/toUploadTo/$filename"))
      s"C:/Users/Administrator/Desktop/toUploadTo/$filename"
    }.getOrElse {
      "Missing file"
    }
  }

}