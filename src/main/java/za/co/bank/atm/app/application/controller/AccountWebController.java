package za.co.bank.atm.app.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import za.co.bank.atm.app.application.service.AccountService;
import za.co.bank.atm.app.domain.dto.AccountDto;
import za.co.bank.atm.app.domain.dto.CurrencyAccountDto;
import za.co.bank.atm.app.exception.ResourceNotFoundException;

import java.util.List;

/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */

@Controller
public class AccountWebController {

    private AccountService accountService;

    public AccountWebController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/")
    public String displayAccountBalances(Model model) throws ResourceNotFoundException {

        List<AccountDto> accounts = accountService.getAccountBalances(1, true);
        List<CurrencyAccountDto> currencyAccounts = accountService.getCurrencyAccounts(1, true);
        model.addAttribute("accounts", accounts);
        model.addAttribute("currencyAccounts", currencyAccounts);

        return "accountbalances";
    }
}
