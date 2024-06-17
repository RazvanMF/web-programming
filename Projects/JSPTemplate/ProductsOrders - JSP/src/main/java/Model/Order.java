package Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {
    int id;
    String user;
    int productId;
    int quantity;
}
