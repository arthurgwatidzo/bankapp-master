package za.co.bank.atm.app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
public class AtmDenominationResponseDto {

    private int key;
    private int count;


}
