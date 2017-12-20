package com.pucmm.web2.Service.Auxiliary;

import com.pucmm.web2.Entity.Receipt;
import com.pucmm.web2.Entity.User;
import com.sendgrid.*;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class EmailService {


    public boolean sendEmail(String emailTo, String Subject, String theContent, Attachments attachments)
    {

        Email from = new Email("management@atlanticshop.com");
        Email to = new Email(emailTo);
        Content content = new Content("text/plain", theContent+"\n\n\nEmail Service by Atlantic Shop");
        Mail mail = new Mail(from, Subject, to, content);

        if(attachments != null){
            mail.addAttachments(attachments);
        }

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API"));
//        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println("Sengrid Status Code:"+response.getStatusCode());
            System.out.println("Sendgrid Errpr Body:"+response.getBody());
            System.out.println("Sendgrid Headers:"+response.getHeaders());
            System.out.println("Delivered to: " + emailTo);
        } catch (IOException ex) {
            System.out.println("ERROR WITH THE EMAIL SERVER, CONTAC YOUR ADMIN");
            return false;
        }
        return true;
    }

    public boolean sendOrderConfirmationEmail(Receipt receipt)
    {

        String content = "Thanks for your order!\n\n"+"Order#"+receipt.getFiscalCode();
        return sendEmail(receipt.getUser().getEmail(),
                "Order Confirmation from atlantic",
                content,null);

    }

    public boolean sendStorageConfirmationEmail(Receipt receipt, User user,String pdf)
    {
        Attachments attachments = new Attachments();

        String content = "Guys, you have a delivery!\n\n"+"Order#"+receipt.getFiscalCode() + "\n\n" +
                "For: " + receipt.getUser().getFullName() + "\n\n" +
                "To: " + receipt.getUser().getShippingAddress() + ", " + receipt.getUser().getCity() +
                ", " + receipt.getUser().getCountry() + ".";

        attachments.setContent(pdf);
        attachments.setType("application/pdf");
        attachments.setFilename("Receipt.pdf");
        attachments.setDisposition("attachment");
        attachments.setContentId("Balance Sheet");

        return sendEmail(user.getEmail(),
                "Order Confirmation for delivery!",
                content,attachments);

    }

    public boolean sendUserRegistrationConfirmation(User user)
    {

        String content = "Welcome to atlantic shop "+user.getFullName()+"!!\n\n" +
                "Here are your credentials:\n\n" +
                "Email: "+ user.getEmail() + "\n\n" +
                "Password: " + user.getPassword() + "\n\n" +
                "Enter now: by copying " + "'localhost:8090'" + " in your browser URL.";
        return sendEmail(user.getEmail(),
                "Welcome to Atlantic Shop!",
                content,null);

    }

}
