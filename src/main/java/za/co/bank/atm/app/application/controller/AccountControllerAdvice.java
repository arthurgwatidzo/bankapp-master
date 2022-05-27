package za.co.bank.atm.app.application.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import za.co.bank.atm.app.exception.ResourceNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
@ControllerAdvice( assignableTypes = {AccountController.class, ReportController.class})
public class AccountControllerAdvice {
    @ExceptionHandler({
            ResourceNotFoundException.class
    })
    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    private String handleValidationErrors(Exception e) {
        Throwable cause = e.getCause();

        if (hasUnderlyingCause(cause)) {
            return "Requested resource could not be found. With cause: "+ cause.getCause().getMessage();
        }
        return "Requested resource could not be found. With cause: "+ e.getMessage();
    }

    private boolean hasUnderlyingCause(Throwable cause) {
        return cause != null && cause.getCause() != null;
    }
}
