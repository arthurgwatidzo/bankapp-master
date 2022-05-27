package za.co.bank.atm.app.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
@Entity
@Table(name = "currency")
@Getter
@Setter
@NoArgsConstructor
public class Currency implements Serializable {

    @Id
    @Column(name = "currency_code")
    private String currency_code;

    @Column(name = "decimal_places")
    private int decimalPlaces;

    @Column(name = "description")
    private String description;
}

