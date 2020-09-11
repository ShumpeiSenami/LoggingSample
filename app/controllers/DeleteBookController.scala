package controllers

import javax.inject.{Inject, Singleton}
import jp.t2v.lab.play2.auth.OptionalAuthElement
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._
import service.UserService
import models.Books
import scalikejdbc.AutoSession

@Singleton
class DeleteBookController @Inject()(val userService: UserService,
                                     components:ControllerComponents)
  extends AbstractController (components)
    with I18nSupport
    with AuthConfigSupport
    with OptionalAuthElement{

  def delete(bookId:Long):Action[AnyContent] = StackAction{implicit request =>
    implicit val session = AutoSession
    val result  =  Books.deleteById(bookId)
    if(result > 0){
      Redirect(routes.HomeController.index())
    } else {
      InternalServerError(Messages("DeleteBookError"))
    }
  }

}
