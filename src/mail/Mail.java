package mail;

import com.PIproject.entities.Medecin;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    public static void sendEmail(Medecin medecin) {

        final String username = "bioticpharma10@gmail.com";
        final String password = "mdnj yrah sesl srah";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            String signature = "\n\n-- \nPharmabiotic Application \nNuméro de téléphone : +216 56257992 \nAdresse e-mail : bioticpharma10@gmail.com \nSite web : www.pharmabiotic.com";

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "Pharmabiotic Application"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(medecin.getEmail()));
            message.setSubject("Medecin Ajouté !");
            message.setText("Cher Mr/Mme " + medecin.getNom() + " " + medecin.getPrenom() + ","
                    + "\n\nNous sommes ravis de vous informer que vous êtes ajouté dans notre application."
                    + "\n\nCordialement," + signature);

            Transport.send(message);

            System.out.println("Mail sent successfully");

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
