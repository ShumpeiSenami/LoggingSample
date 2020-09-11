package controllers

import javax.inject.{Inject, Singleton}
import jp.t2v.lab.play2.auth.OptionalAuthElement
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._
import service.UserService
import models.Books
import scalikejdbc.AutoSession

class CreateBookController@Inject()(val userService: UserService,
                                    components:ControllerComponents)
  extends AbstractController (components)
    with I18nSupport
    with AuthConfigSupport
    with OptionalAuthElement
    with BookControllerSupport {

  def index:Action[AnyContent] = StackAction{ implicit request =>
    Ok(views.html.create(loggedIn,form))
  }

  def create:Action[AnyContent]  = StackAction{ implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.create(loggedIn,formWithErrors)), {model =>
          implicit val session = AutoSession
          val book  = Books(None,model.title, model.roll,model.author)
          val result  = Books.create(book)
          if(result > 0){
            Redirect(routes.HomeController.index())
          } else{
            InternalServerError(Messages("CreateBookError"))
          }
        }
      )
  }

}
