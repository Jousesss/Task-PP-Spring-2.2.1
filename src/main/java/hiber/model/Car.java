package hiber.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "car", schema = "public", catalog = "spring_course",
        uniqueConstraints = @UniqueConstraint(columnNames = {"model", "series"}))
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NonNull
    @Column(name = "model", nullable = false)
    private String model;

    @NonNull
    @Column(name = "series", nullable = false)
    private int series;
}
