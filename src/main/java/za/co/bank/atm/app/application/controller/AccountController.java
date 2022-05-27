package za.co.bank.atm.app.application.controller;

import za.co.bank.atm.app.application.service.AccountService;
import za.co.bank.atm.app.domain.dto.AccountDto;
import za.co.bank.atm.app.domain.dto.AccountWithdrawalRequestDto;
import za.co.bank.atm.app.domain.dto.AtmDenominationResponseDto;
import za.co.bank.atm.app.domain.dto.CurrencyAccountDto;
import za.co.bank.atm.app.exception.ApplicationException;
import za.co.bank.atm.app.exception.ResourceNotFoundException;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
@RequestMapping("/clients")
@RestController
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}/accounts")
    public List<AccountDto> getAccountBalance(@PathVariable Integer id, @RequestParam boolean transactional) throws ResourceNotFoundException {

        return accountService.getAccountBalances(id, transactional);
    }

    @GetMapping("/{id}/currencyAccount")
    public List<CurrencyAccountDto> getCurrencyAccounts(@PathVariable Integer id, @RequestParam boolean transactional) throws ResourceNotFoundException {

        return accountService.getCurrencyAccounts(id, transactional);
    }

    @GetMapping("/{id}/withdrawFromAccount")
    public List<AtmDenominationResponseDto> withdrawFromAcc(@PathVariable Integer id, final AccountWithdrawalRequestDto accountWithdrawalRequestDto) throws  ApplicationException {

        return accountService.withdrawFromAcc(id, accountWithdrawalRequestDto);
    }


}
