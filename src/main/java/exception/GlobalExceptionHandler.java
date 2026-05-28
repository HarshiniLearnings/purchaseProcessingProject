package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(PurchaseNotFoundException.class)
    public ResponseEntity<String> notFoundException(PurchaseNotFoundException pex){
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND)
                .body(pex.getMessage());

    }

    @ExceptionHandler(CurrencyConversionException.class)
    public ResponseEntity<String> conversionException(CurrencyConversionException cex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(cex.getMessage());
    }
}
