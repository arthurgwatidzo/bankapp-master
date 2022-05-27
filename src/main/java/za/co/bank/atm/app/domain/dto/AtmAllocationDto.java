package za.co.bank.atm.app.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
@Getter
@Setter
public class AtmAllocationDto {

    private int atmAllocationId;
    private int AtmId;
    private int denominationId;
    private int count;
    private int value;
    private int denominationSum;


    public static AtmAllocationDto mapToAtmAllocationDto(Object[] object){

        AtmAllocationDto atmAllocationModel = new AtmAllocationDto();
        atmAllocationModel.setAtmAllocationId(Integer.parseInt(object[0].toString()));
        atmAllocationModel.setAtmId(Integer.parseInt(object[1].toString()));
        atmAllocationModel.setDenominationId(Integer.parseInt(object[2].toString()));
        atmAllocationModel.setCount(Integer.parseInt(object[3].toString()));
        atmAllocationModel.setValue(Integer.parseInt(object[4].toString()));
        atmAllocationModel.setDenominationSum(Integer.parseInt(object[5].toString()));

        return atmAllocationModel;
    }
}
