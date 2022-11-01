package car.com.Cars.exception.CustomExeption;

/**
 * Exception when a car could not be deleted
 */
public class DeleteException extends RuntimeException {

    /**
     * get message of the exception and return it while handling
     *
     * @param message of the exception
     */
   public DeleteException(String message){
       super(message);
   }
}
