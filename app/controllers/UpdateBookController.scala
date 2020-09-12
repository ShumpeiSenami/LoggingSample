package controllers

import forms.BookForm
import javax.inject.{Inject, Singleton}
import jp.t2v.lab.play2.auth.OptionalAuthElement
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._
import service.UserService
import models.Books
import scalikejdbc.AutoSession

@Singleton
class UpdateBookController@Inject()(val userService: UserService,
                                    components:ControllerComponents)
  extends AbstractController (components)
    with I18nSupport
    with AuthConfigSupport
    with OptionalAuthElement
    with BookControllerSupport {

  def index(bookId:Long):Action[AnyContent] = StackAction{ implicit request =>
    val result = Books.findById(bookId).get
    val filledForm = form.fill(BookForm(result.id, result.title, result.roll, result.author))
    Ok(views.html.edit(loggedIn, filledForm))
  }

  def update:Action[AnyContent] = StackAction{implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.edit(loggedIn, formWithErrors)), {model =>
          implicit val session = AutoSession
          val result = Books
            .updateById(model.id.get)
            .withAttributes(
              'title -> model.title,
              'roll -> model.roll,
              'author -> model.author
            )
          if(result > 0)
            Redirect(routes.HomeController.index())
          else
            InternalServerError(Messages("UpdateBookError"))
        }
      )
  }

}
