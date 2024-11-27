package application;

import org.apache.commons.validator.routines.EmailValidator;


import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailService {

    public void sendRegistrationEmail(String toEmail, String username,String type) 
    {
        String fromEmail = "careerconnect635@gmail.com";
        String password = "imla xqyw dfee bajc"; 

        // SMTP Configuration
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Authentication
        Session session = Session.getInstance(props, new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Compose the email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Registration Successful");
            if(type.equals("JobSeeker"))
            {
            	message.setText("Dear " + username + ",\n\n" +
                        "Welcome to CareerConnect!\n\n" +
                        "We're thrilled to have you on board. Your account has been successfully created, and you're now ready to explore exciting job opportunities that match your skills and aspirations. " +
                        "Log in to your account and start your journey towards finding the perfect job today!\n\n" +
                        "Best regards,\nThe CareerConnect Team");            }
            else
            {
            	message.setText("Dear " + username + ",\n\n" +
                        "Welcome to CareerConnect!\n\n" +
                        "We're excited to have you as part of our community. Your account has been successfully created, and you're now ready to find top talent for your company. " +
                        "Log in to your account to start posting job openings and connect with qualified candidates who can help take your business to the next level.\n\n" +
                        "Best regards,\nThe CareerConnect Team");
            }
            // Send the email
            Transport.send(message);
            System.out.println("Registration email sent to " + toEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public void sendApplicationApprovalEmail(String toEmail, String username,int jobid) 
    {
        String fromEmail = "careerconnect635@gmail.com";
        String password = "imla xqyw dfee bajc"; 

        // SMTP Configuration
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Authentication
        Session session = Session.getInstance(props, new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Compose the email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Registration Successful");
            
            	message.setText("Dear " + username + ",\n\n" +
                        "Congratulations!\n\n" +
                        "We are excited to inform you that your application for the JobID "
                        + jobid +" has been accepted. "
                        + "We look forward to discussing the next steps with you soon.\n\n"
            			+ "Best regards,\nThe CareerConnect Team");            
            
            // Send the email
            Transport.send(message);
            System.out.println("Registration email sent to " + toEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}


