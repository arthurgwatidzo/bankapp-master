package za.co.bank.atm.app.application.service;

import lombok.extern.slf4j.Slf4j;
import za.co.bank.atm.app.domain.*;
import za.co.bank.atm.app.domain.dto.*;
import za.co.bank.atm.app.exception.ApplicationException;
import za.co.bank.atm.app.exception.ResourceNotFoundException;
import za.co.bank.atm.app.infrastructure.persistence.AtmAllocationRepository;
import za.co.bank.atm.app.infrastructure.persistence.ClientAccountRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.RoundingMode.HALF_UP;
import static za.co.bank.atm.app.domain.dto.CurrencyAccountDto.*;

/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
@Slf4j
@Service
public class AccountService {

    private ClientAccountRepository clientAccountRepository;

    private AtmAllocationRepository atmAllocationRepository;


    public AccountService(final ClientAccountRepository clientAccountRepository,
                          final AtmAllocationRepository atmAllocationRepository) {
        this.clientAccountRepository = clientAccountRepository;
        this.atmAllocationRepository = atmAllocationRepository;
    }

    public List<AccountDto> getAccountBalances(Integer id, boolean transactional) throws ResourceNotFoundException {

        List<Object[]> clientAccounts = clientAccountRepository.findAllByClientId(id, transactional);

        if (clientAccounts.size() == 0) {
            throw new ResourceNotFoundException();
        }
        return clientAccounts.stream()
                .map(AccountDto::mapToAccountDto)
                .collect(Collectors.toList());
    }


    public List<CurrencyAccountDto> getCurrencyAccounts(Integer id, boolean transactional) throws ResourceNotFoundException {
        List<Object[]> currencyAccounts = clientAccountRepository.findAllCurrencyAccountsByClientId(id, transactional);

        if (currencyAccounts.size() == 0) {
            throw new ResourceNotFoundException();
        }
        List<CurrencyAccountDto> responseList = new ArrayList<>();
        currencyAccounts.forEach(obj ->{
           BigDecimal convertedResult = convertToZAR(obj[3].toString(), new BigDecimal(obj[1].toString()), new BigDecimal(obj[4].toString()));
           responseList.add(mapToDto(obj, convertedResult));

        });

        return responseList;
    }

    public  List<AtmDenominationResponseDto> withdrawFromAcc(Integer id, AccountWithdrawalRequestDto accountWithdrawalRequestDto) throws ApplicationException {

        List<AtmDenominationResponseDto> atmDenominationResponseDtoList;
        BigDecimal amountAfterWithdrawal;

        ClientAccount clientAccountDetails =  clientAccountRepository.findByClientAccountNumber(accountWithdrawalRequestDto.getAccountNumber());
        log.debug("ClientAccountDetails= {}", clientAccountDetails);

        if(clientAccountDetails.getAccount_type_code().getAccount_type_code().equals(AccountTypeEnum.CHQ.toString())){
            int i = clientAccountDetails.getDisplayBalance().subtract(accountWithdrawalRequestDto.getWithdrawalAmt()).intValue();
            if(clientAccountDetails.getDisplayBalance().compareTo(new BigDecimal(-10000)) == -1||i < -10000){
                throw new ApplicationException("Insufficient funds: You have exceeded your cheque account limit");
            }


            atmDenominationResponseDtoList = getAtmDispensation(accountWithdrawalRequestDto.getWithdrawalAmt(), accountWithdrawalRequestDto.getAtmId());

            amountAfterWithdrawal = clientAccountDetails.getDisplayBalance().subtract(accountWithdrawalRequestDto.getWithdrawalAmt());
            updateClientAccountBalance(amountAfterWithdrawal,clientAccountDetails.getClientAccountNumber());

        }else{
            if(clientAccountDetails.getDisplayBalance().compareTo(accountWithdrawalRequestDto.getWithdrawalAmt()) == -1){
                throw new ApplicationException("Insufficient funds");
            }
            atmDenominationResponseDtoList = getAtmDispensation(accountWithdrawalRequestDto.getWithdrawalAmt(), accountWithdrawalRequestDto.getAtmId());

            amountAfterWithdrawal = clientAccountDetails.getDisplayBalance().subtract(accountWithdrawalRequestDto.getWithdrawalAmt());
            updateClientAccountBalance(amountAfterWithdrawal,clientAccountDetails.getClientAccountNumber());
        }
        return atmDenominationResponseDtoList;
    }

    private List<AtmDenominationResponseDto> getAtmDispensation(BigDecimal requestAmt, Integer atmId) throws ApplicationException {

        List<Object[]> atmAllocations = atmAllocationRepository.getListOfDenominationsAndSumByAtmId(atmId);

        List<AtmAllocationDto> atmAllocationModelList = atmAllocations.stream()
                .map(AtmAllocationDto::mapToAtmAllocationDto)
                .collect(Collectors.toList());

        int atmAllocationSum = 0;
        for (AtmAllocationDto atmAllocation : atmAllocationModelList) {
            atmAllocationSum += atmAllocationSum + atmAllocation.getDenominationSum();
        }

        if(requestAmt.compareTo(new BigDecimal(atmAllocationSum)) == 1){
            throw new ApplicationException("Amount not available, would you like to draw R "+atmAllocationSum);
        }

        List<AtmDenominationResponseDto> atmDenominationResponseDtoList = new ArrayList<>();
        int countNotes = 0;
        BigDecimal tempDispenseBalance = requestAmt;
        for(AtmAllocationDto model: atmAllocationModelList){
            if(model.getCount() > 0) {

                if (tempDispenseBalance.remainder(new BigDecimal(model.getValue())).compareTo(BigDecimal.ZERO) == 0) {

                    countNotes = tempDispenseBalance.divide(new BigDecimal(model.getValue())).intValue(); //7
                    if (model.getCount() >= countNotes) {
                        model.setCount(model.getCount()-countNotes);
                        atmDenominationResponseDtoList.add(new AtmDenominationResponseDto(model.getValue(),countNotes));
                        tempDispenseBalance = BigDecimal.ZERO;
                        break;

                    } else {
                        atmDenominationResponseDtoList.add(new AtmDenominationResponseDto(model.getValue(),model.getCount()));
                        model.setCount(0);

                        tempDispenseBalance = tempDispenseBalance.subtract(new BigDecimal(model.getDenominationSum()));
                    }

                }else if (tempDispenseBalance.divide(new BigDecimal(model.getValue())).intValue() >=1 ){

                    countNotes = tempDispenseBalance.divide(new BigDecimal(model.getValue())).intValue();
                    atmDenominationResponseDtoList.add(new AtmDenominationResponseDto(model.getValue(),countNotes));
                    model.setCount(model.getCount() - countNotes);

                    tempDispenseBalance = tempDispenseBalance.subtract(new BigDecimal(countNotes*model.getValue()));
                }
            }
        }



        return atmDenominationResponseDtoList;
    }

    private void updateClientAccountBalance(BigDecimal amountAfterWithdrawal, String clientAccNum ){

        clientAccountRepository.updateClientAccountBalance(amountAfterWithdrawal,clientAccNum);

    }


    private BigDecimal convertToZAR(String convIndicator, BigDecimal curBalance, BigDecimal convRate){

        switch (convIndicator){
            case "*" : return curBalance.multiply(convRate);
            case "/" : return curBalance.divide(convRate, HALF_UP);
            default : return new BigDecimal(0);
        }
    }
}
