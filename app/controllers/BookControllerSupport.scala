package controllers

import forms.BookForm
import play.api.data.Forms._
import play.api.data._
import play.api.mvc.AbstractController

trait BookControllerSupport { this:AbstractController =>

  protected val form = Form(
    mapping(
      "id"      -> optional(longNumber),
      "title"   -> nonEmptyText,
      "roll"    -> optional(number),
      "author"  -> nonEmptyText
    )(BookForm.apply)(BookForm.unapply)
  )

}
