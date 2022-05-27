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
@Table(name = "denomination_type")
@Getter
@Setter
@NoArgsConstructor
public class DenominationTypeCode implements Serializable {

    @Id
    @NonNull
    @Column(name = "denomination_type_code")
    private String denominationTypeCode;

    @Column(name = "description")
    private String description;
}
