package za.co.bank.atm.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
@Entity
@Table(name = "credit_card_limit")
@Getter
@Setter
@NoArgsConstructor
public class CreditCardLimit implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_account_number", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @NonNull
    private ClientAccount clientAccountNumber;

    @NonNull
    @Column(name = "account_limit")
    private double accountLimit;
}
