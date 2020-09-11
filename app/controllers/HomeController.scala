package controllers

import javax.inject._

import jp.t2v.lab.play2.auth.OptionalAuthElement
import play.api.i18n.I18nSupport
import play.api.mvc._
import service.UserService
import models.Books

@Singleton
class HomeController @Inject()(val userService: UserService, components: ControllerComponents)
  extends AbstractController(components)
    with I18nSupport
    with AuthConfigSupport
    with OptionalAuthElement {

  def index: Action[AnyContent] = StackAction { implicit request =>
    val result = Books.findAll()
    Ok(views.html.index(loggedIn, result))
  }

}