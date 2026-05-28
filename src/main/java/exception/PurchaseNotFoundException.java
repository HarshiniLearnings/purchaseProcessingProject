package exception;

public class PurchaseNotFoundException extends RuntimeException{
    public PurchaseNotFoundException(){
        super("Purchase not found");
    }
}
