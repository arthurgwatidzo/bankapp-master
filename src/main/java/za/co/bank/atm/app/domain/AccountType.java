package za.co.bank.atm.app.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;
/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
@Entity
@Table(name = "account_type")
@Getter
@Setter
@NoArgsConstructor
public class AccountType implements Serializable {


    @Id
    @NonNull
    @Column(name = "account_type_code")
    private String accountTypeCode;

    @NonNull
    @Column(name = "description")
    private String description;

    @Column(name = "transactional")
    private boolean transactional;

}
