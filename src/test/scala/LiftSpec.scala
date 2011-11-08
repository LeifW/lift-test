import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

import net.liftweb.json._

case class Person[T](
  name: String,
  age: T
)

case class Err(msg:String = "You broke it.")

class LiftSpec extends Spec with ShouldMatchers {

  type Chance[T <: Object] = Either[Err, Person[T]]

  describe ("lift-json") {
    it("should return deserialized Left from JSON") {
      implicit val formats = DefaultFormats withHints new ShortTypeHints(List(classOf[Left[_,_]], classOf[Right[_,_]]))

      val left: Chance[String] = Left( Err("Oops") )
      val json = Serialization.write(left)
      println(json)
      val result: Chance[String] = Serialization.read[Chance[String]](json)

      result should equal (left)
    }
    it("should return deserialized Right from JSON") {
      implicit val formats = DefaultFormats withHints new ShortTypeHints(List(classOf[Left[_,_]], classOf[Right[_,_]]))

      val right: Chance[String] = Right( Person("Bob", "30") )
      val json = Serialization.write(right)
      println(json)
      val result: Chance[String] = Serialization.read[Chance[String]](json)
      result should equal (right)
    }
  }
}
