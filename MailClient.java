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
    // Almacena el número de mensajes de spam recibidos
    private int ContadorSpam;
    // Almacena el número de correos enviados
    private int ContadorEnviados;
    // Almacena el número de mensajes recibidos
    private int ContadorRecibidos;
    // Longitud máxima de mensaje recibido
    private int numCarMes;
    // Usuario que envió el correo más largo
    private String maxUsr;
    // Último mensaje de spam recibido
    private MailItem UltSpam;
    
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
        ContadorRecibidos = ContadorRecibidos + 1;
        DetSpam();
        maxMes();
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
    
    /**
     * Recupera el MailItem y si no hay muestra un mensaje de error.
     */
    public void printNextMailItem()
    {
        DetSpam();
        maxMes();
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
        showStats();
        ContadorEnviados = ContadorEnviados +1;
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
        showStats();
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
            ContadorSpam = ContadorSpam + 1;
            UltSpam = ultimo;
            if (spam.getMessage().contains("trabajo"))
            {
                esSpam = false;
            }
        }
        else
        {
            esSpam = false;
        }
    }
    
    /**
     * Mensaje mas largo
     */
    public void maxMes()
    {
        MailItem correo = server.getNextMailItem(user);
        if (numCarMes < correo.getMessage().length())
        {
            numCarMes = correo.getMessage().length();
            maxUsr = correo.getSubject();
        }
    }
    
    /**
     * Estadísticas de mensajes
     */
    public void showStats()
    {
        System.out.println("Son spam " + ContadorSpam + " mensajes");
        System.out.println("Has enviado " + ContadorEnviados + " mensajes");
        System.out.println("Has recibido " + ContadorRecibidos + " mensajes");
        System.out.println("El " + ContadorRecibidos / ContadorSpam + "% son spam");
        System.out.println("El mensaje más largo recibido es de " + maxUsr + " y consta de " + numCarMes + " caracteres");
    }
    
    /**
     * Muestra la información del ultmo correo de spam, si no hay se informa de ello
     */
    public void showInfoLastSpam()
    {
        System.out.print(UltSpam);
        if (UltSpam == null)
        {
            System.out.print("No se ha recibido spam por el momento");
        }
    }
}
