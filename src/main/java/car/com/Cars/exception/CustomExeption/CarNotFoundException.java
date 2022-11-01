package car.com.Cars.exception.CustomExeption;


/**
 * Exception when a car is not found
 */
public class CarNotFoundException extends RuntimeException {
    /**
     * get message of the exception and return it while handling
     *
     * @param message of the exception
     */
     public CarNotFoundException(String message){
       super(message);
   }

}
