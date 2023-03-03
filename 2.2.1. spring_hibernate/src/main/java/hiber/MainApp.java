package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("James", "Bond", "noTurnSignals@mail.ru", new Car("bmw",34)));
      userService.add(new User("Oleg", "Johnson", "hookah@mail.ru",new Car("mercedes",124)));
      userService.add(new User("Boris", "Brown", "dontRideJustfix@mail.ru",new Car("bmw",36)));
      userService.add(new User("UserName", "UserSurname", "user@mail.ru",new Car("userCar",00)));

      List<User> users = userService.listUsers();
      System.out.println('\n' + "Все пользователи:" + '\n' + users);

      User userByCar = userService.getUserByCar("bmw",34);
      System.out.println('\n' + "Пользователь c данной машиной:" + '\n' + userByCar);

      context.close();
   }
}
