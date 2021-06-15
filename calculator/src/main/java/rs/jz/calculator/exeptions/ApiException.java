package rs.jz.calculator.exeptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {
    private final String massage;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;

    public ApiException(String massage, HttpStatus httpStatus, ZonedDateTime timeStamp) {
        this.massage = massage;
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }

    public String getMassage() {
        return massage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }
}
