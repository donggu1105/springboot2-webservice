package jpql;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Table
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;
    private int stockAmount;
}
