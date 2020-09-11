package models

import java.time.ZonedDateTime

import scalikejdbc._, jsr310._
import skinny.orm._

/**
 * Books
 */
case class Books(id:Option[Long],
                title:String,
                roll:Option[Int],
                author:String)

object Books extends SkinnyCRUDMapper[Books]{

  override def tableName: String = "books"

  override val columns = Seq("id", "title", "roll", "author")

  override def defaultAlias: Alias[Books] = createAlias("b")

  private def toNamedValues(record: Books):Seq[(Symbol, Any)] = Seq(
    'title      -> record.title,
    'roll      -> record.roll,
    'author  -> record.author
  )

  override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[Books]): Books =
    autoConstruct(rs, n)

  def create(books: Books)(implicit session:DBSession):Long =
    createWithAttributes(toNamedValues(books): _*)

  def update(books: Books)(implicit session:DBSession):Int =
    updateById(books.id.get).withAttributes(toNamedValues(books): _*)
}
