package core.api.Responses;

public class ErrorResponse {
    private String code;
    private String error_code;
    private String message;

    public String getCode() {
        return code;
    }
    public String getErrorCode() {
        return error_code;
    }
    public String getMessage() {
        return message;
    }
}