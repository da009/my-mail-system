
/**
 * Write a description of class MailClient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MailClient
{
    // Servidor de correo.
    private MailServer server;
    // Destinatario del correo.
    private String user;
    
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
    }
    
    /**
     * Recupera del servidor el  objeto MailItem que tenga el usuario y devuelva dicho objeto.
     */
    public MailItem getNextMailItem()
    {
        return server.getNextMailItem(user);
    }
    
    /**
     * Recupera el MailItem y si no hay muestra un mensaje de error.
     */
    public void printNextMailItem()
    {
        MailItem correo = server.getNextMailItem(user);
        if(correo == null)
        {
            System.out.println("No hay correo.");
        }
        else
        {
            correo.print();
        }
    }
    
    /**
     * Permite enviar un mensaje.
     */
    public void sendMailItem(String to, String subject, String message)
    {
        MailItem correo = new MailItem(user, to, subject, message);
        server.post(correo);
    }
}
