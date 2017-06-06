package tek;

/**
 * Handle the Sucess and Error JSON messages
 * @author Haroon
 *
 */

public class CustomErrorType {
 
    private String errorMessage;
 
    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }
 
}
