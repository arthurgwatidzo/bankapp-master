package za.co.bank.atm.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
@Entity
@Table(name = "client_account")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClientAccount implements Serializable {

    @Id
    @Column(name = "client_account_number")
    private String clientAccountNumber;

    @Column(name = "display_balance")
    private BigDecimal displayBalance;

    @JoinColumn(name = "client_id",  referencedColumnName = "client_id" )
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Client clientId;

    @JoinColumn(name = "account_type_code", referencedColumnName = "account_type_code")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AccountType accountTypeCode;

    @JoinColumn(name = "currency_code", referencedColumnName = "currency_code")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Currency currencyCode;


}
