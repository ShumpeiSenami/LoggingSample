import com.google.inject.AbstractModule
import service._

class AppModule extends AbstractModule{

  override def configure(): Unit = {
    bind(classOf[UserService]).to(classOf[UserServiceImpl])
  }

}
