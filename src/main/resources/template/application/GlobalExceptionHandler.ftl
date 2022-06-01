package ${_package}.application;

import ${_package}.common.BusinessException;
import ${_package}.common.BusinessResponse;
import ${_package}.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

/**
* 处理所有不可知异常
*/
@ExceptionHandler(Exception.class)
public ResponseEntity<String> handleUnknownException(Exception e) {
    LOGGER.error(e.getMessage(), e);
    return ResponseEntity.internalServerError().body("服务器内部错误!");
    }

    /**
    * 处理所有业务异常
    */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessResponse> handleBusinessException(BusinessException businessException) {
        LOGGER.error(businessException.getMessage(), businessException);
        return ResponseEntity.internalServerError().body(new BusinessResponse(Constants.ResponseCode.ERROR.getCode(), businessException.getMessage()));
        }
}