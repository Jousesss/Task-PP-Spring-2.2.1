package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      // Добавил одну лишнюю машину
      carService.add(new Car("HAVAL", 10));

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(new Car("BMW", 4));

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(new Car("Mercedes", 5));

      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      user3.setCar(new Car("VAZ", 2));

      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user4.setCar(new Car("Toyota", 8));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      System.out.println("\n\n");

      // Получение User по модели и номеру машины
      User user = userService.findByCarModelAndSeries("VAZ", 2).orElse(null);
      System.out.println("Id = " + user.getId());
      System.out.println("First Name = " + user.getFirstName());
      System.out.println("Last Name = " + user.getLastName());
      System.out.println("Email = " + user.getEmail());
      System.out.println("Car = " + user.getCar());

      context.close();
   }
}
