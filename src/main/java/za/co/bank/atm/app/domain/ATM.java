package za.co.bank.atm.app.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
@Entity
@Table(name = "atm")
@Getter
@Setter
@NoArgsConstructor
public class ATM implements Serializable {

    @Id
    @NonNull
    @Column(name = "atm_id")
    private int atm_id;


    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "location")
    private String location;
}
