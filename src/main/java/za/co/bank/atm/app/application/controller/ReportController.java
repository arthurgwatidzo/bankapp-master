package za.co.bank.atm.app.application.controller;

import za.co.bank.atm.app.application.service.ReportingService;
import za.co.bank.atm.app.domain.dto.AccountReportDto;
import za.co.bank.atm.app.domain.dto.AggregateReportDto;
import za.co.bank.atm.app.exception.ResourceNotFoundException;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
@RequestMapping("/reports")
@RestController
public class ReportController {

    private ReportingService reportingService;

    public ReportController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @GetMapping("/highestTransactionalAccount")
    public List<AccountReportDto> getHighestTransactionalAccountPerUser(@RequestParam boolean transactional) throws ResourceNotFoundException {

        return reportingService.getHighestTransactionalAccountPerUser(transactional);
    }


    @GetMapping("/{id}/aggregateFinPosition")
    public AggregateReportDto getAggregateFinancialPositions(@PathVariable Integer id) throws ResourceNotFoundException {

        return reportingService.getAggregateFinancialPositions(id);
    }

}
