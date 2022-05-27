package za.co.bank.atm.app.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
@Setter
@Getter
public class AccountWithdrawalRequestDto {

    private String accountNumber;
    private String accountType;
    private BigDecimal withdrawalAmt;
    private int atmId;
    private int clientId;
}
