package hiber.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "users", schema = "public", catalog = "spring_course")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NonNull
   @Column(name = "name", nullable = false)
   private String firstName;

   @NonNull
   @Column(name = "last_name")
   private String lastName;

   @NonNull
   @Column(name = "email", nullable = false, unique = true)
   private String email;

   @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
   @JoinColumn(name = "car_id", unique = true)
   private Car car;
}
