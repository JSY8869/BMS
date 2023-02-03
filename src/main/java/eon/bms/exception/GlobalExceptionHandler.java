package eon.bms.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { CustomException.class })
    protected ModelAndView handleCustomException(CustomException e, Model model) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        model.addAttribute("errorCode", e.getErrorCode().getMessage());
        return new ModelAndView("/error/customErrorPage", (Map<String, ?>) model) ;
    }
}