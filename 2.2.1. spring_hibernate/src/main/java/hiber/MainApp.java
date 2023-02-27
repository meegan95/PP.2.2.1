package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDao;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      userService.add(new User("Bmw", "Owner", "noTurnSignals@mail.ru", new Car("bmw",34)));
      userService.add(new User("Mercedes", "Owner", "hookah@mail.ru",new Car("mercedes",124)));
      userService.add(new User("Lada", "Owner", "dontRideJustfix@mail.ru",new Car("lada",2121)));
      userService.add(new User("Gaz", "Owner", "boyarin@mail.ru",new Car("gaz",24)));

      List<User> users = userService.listUsers();

      for (User user : users) {

         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("User car = "+user.getUserCar());
         System.out.println();
      }

      User carOwner = userService.getUserByCar("bmw",34);
      System.out.println("-------------------------");
      System.out.println(carOwner);

      context.close();
   }
}
