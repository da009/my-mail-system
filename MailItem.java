
/**
 * Write a description of class MailItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MailItem
{
    // Remitente del correo.
    private String from;
    // Destinatario del correo.
    private String to;
    // Contenido del correo.
    private String message;
    
    /**
     * Objeto MailItem
     */
    public MailItem(String from1, String to1, String message1)
    {
        from = from1;
        to = to1;
        message = message1;
    }
    
    /**
     * Devuelve el from
     */
    public String getFrom()
    {
        return from;
    }
    
    /**
     * Devuelve el to
     */
    public String getTo()
    {
        return to;
    }
    
    /**
     * Devuelve el texto del correo
     */
    public String getMessage()
    {
        return message;
    }
    
    /**
     * Método print que sin parámetros y que muestra por pantalla los atributos del objeto.
     */
    public void print()
    {
        System.out.println("De=> " + from);
        System.out.println("Para=> " + to);
        System.out.println("Mensaje=> " + message);
    }
}
