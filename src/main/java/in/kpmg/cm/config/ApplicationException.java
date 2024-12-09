package in.kpmg.cm.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error Occured during processing Request")
public class ApplicationException extends Exception{
    private static final long serialVersionUID = 1L;

    
    
}