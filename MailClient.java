
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
    // Ultimo correo.
    private MailItem ultimo;
    // Spam
    private MailItem spam;
    // Guarda el boolean como true si es spam
    private boolean esSpam;
    
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
        if (esSpam == true)
        {
            return null;
        }
        else
        {
            ultimo = getNextMailItem();
            return server.getNextMailItem(user);
        }
    }
    }
    
    /**
     * Recupera el MailItem y si no hay muestra un mensaje de error.
     */
    public void printNextMailItem()
    {
        if (esSpam == false)
        {
            MailItem correo = server.getNextMailItem(user);
            ultimo = getNextMailItem();
            if(correo == null)
            {
                System.out.println("No hay correo.");
            }
            else
            {
                correo.print();
            }
        }
        else 
        {
            System.out.println("Ha recibido spam");
        }
    }
    
    /**
     * Permite enviar un mensaje.
     */
    public void sendMailItem(String to, String subject, String message)
    {
        MailItem correo = new MailItem(user, to, subject, message);
    }
    
    /**
     * Nos muestra el nº de correos q tiene el cliente.
     */
    public void howManyMailItems()
    {
        System.out.println("Emails pendientes en el servidor: " + server.howManyMailItems(user));
    }
    
    /**
     * Responde automáticamente al correo.
     */
    public void getNextMailItemAndSendAutomaticRespond()
    {
            MailItem correo = server.getNextMailItem(user);
            ultimo = getNextMailItem();
            if(correo != null)
            {
                sendMailItem(correo.getFrom(), "Re:" + correo.getSubject(), "Esoty en la oficina.\n" + correo.getMessage());;
            }
    }
    
    /**
     * Detecta el spam
     */
    public void DetSpam()
    {
        if (spam.getMessage().contains("regalo") || spam.getMessage().contains("promoción"))
        {
            esSpam = true;
            if (spam.getMessage().contains("trabajo"))
            {
                esSpam = false;
            }
        }
        else
        {
            esSpam = true;
        }
    }
}
