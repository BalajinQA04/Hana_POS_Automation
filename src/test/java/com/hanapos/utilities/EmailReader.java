
package com.hanapos.utilities;

import javax.mail.*;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import org.testng.Assert;

import java.io.IOException;
import java.util.Properties;
/*
public class EmailReader {
    private final static Properties properties = new Properties();
    private final static String host = "imap.gmail.com";
    private final static String username = "hanaposqateam@gmail.com"; // password is app password created
    private final static String password = "bcfburrmktksjckr";
    private static EmailReader EmailReaderInstance = null;

    public EmailReader() {

    }

    public static EmailReader getInstance() {
        if (EmailReaderInstance == null) {
            EmailReaderInstance = new EmailReader();
        }
        return EmailReaderInstance;
    }


    // Set up email server properties


    public static String getInvoiceNumber() {

        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", host);
        properties.put("mail.imaps.port", "993");
        properties.put("mail.imaps.starttls.enable", "true");

        try {
            Thread.sleep(5000);
            // Connect to the email server
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("imaps");
            store.connect(host, username, password);

            // Open the inbox folder
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            String invoiceNumber = null;
            String invoicePhrase = "Order Confirmation For ";

            System.out.println("Number of emails present is : " + messages.length);

            String expectedMessage = "THIS IS A COPY OF THE INVOICE FOR YOUR RECENT ORDER";

            boolean isEmailValid = false;
            for (Message singleMessage : messages) {
                String subject = singleMessage.getSubject();
                // Find a message with the subject that starts with the invoice Phrase 
                if (subject != null && subject.startsWith(invoicePhrase)) {
                    singleMessage.setFlag(Flags.Flag.SEEN, true);
                    int startIndex = invoicePhrase.length();
                    int endIndex = startIndex + 7; // Assuming the invoice number is 7 digits
                    invoiceNumber = subject.substring(startIndex, endIndex);
                    System.out.println("Invoice number at email subject : " + invoiceNumber);
                    break;
                }

                String messageBody = getMessageBody(singleMessage);

                String invoicePrefix = "INVOICE NO - ";

                // Check if the email body contains the invoice prefix
                int invoiceIndex = messageBody.indexOf(invoicePrefix);
                if (invoiceIndex != -1) {
                    // Extract the 7-digit invoice number
                    int startIndex = invoiceIndex + invoicePrefix.length();
                    int endIndex = startIndex + 7; // Assuming the invoice number is 7 digits
                    invoiceNumber = messageBody.substring(startIndex, endIndex);
                    System.out.println("Invoice number at email body : " + invoiceNumber);
                    break;
                }

            }

            // Close the folder and store
            inbox.close(false);
            store.close();

            if (invoiceNumber != null) {
                System.out.println("The Invoice Number is " + invoiceNumber);
            } else {
                System.out.println("Invoice number not found.");
            }
            return invoiceNumber;
        } catch (Exception e) {
            throw new RuntimeException("There are problems with reading emails.", e);
        }

    }

    private static String getMessageBody(Message message) throws MessagingException, IOException {
        String messageBody = "";
        if (message.isMimeType("text/plain")) {
            messageBody = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            messageBody = getTextFromMimeMultipart(mimeMultipart);
        }
        return messageBody;
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws IOException, MessagingException {
        int count = mimeMultipart.getCount();
        if (count == 0)
            throw new MessagingException("Multipart with no body parts not supported.");
        boolean multipartAlt = new ContentType(mimeMultipart.getContentType()).match("multipart/alternative");
        if (multipartAlt)
            return getTextFromBodyPart(mimeMultipart.getBodyPart(count - 1));
        String result = "";
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            result += getTextFromBodyPart(bodyPart);
        }
        return result;
    }

    private static String getTextFromBodyPart(BodyPart bodyPart) throws IOException, MessagingException {
        String result = "";
        if (bodyPart.isMimeType("text/plain")) {
            result = (String) bodyPart.getContent();
        } else if (bodyPart.isMimeType("text/html")) {
            String html = (String) bodyPart.getContent();
            result = org.jsoup.Jsoup.parse(html).text();
        } else if (bodyPart.getContent() instanceof MimeMultipart) {
            result = getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
        }
        return result;
    }
}    



*/


public class EmailReader {
    private final static Properties properties = new Properties();
    private final static String host = "imap.gmail.com";
    private final static String username = "hanaposqateam@gmail.com";
    private final static String password = "bcfburrmktksjckr";

    static {
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", host);
        properties.put("mail.imaps.port", "993");
        properties.put("mail.imaps.starttls.enable", "true");
    }

    /**
     * Polls mailbox until an email with one of the subjects appears or timeout reaches.
     *
     * @param subjectPrefixes        String array of valid subject prefixes to check for e.g. "Order Confirmation For ", "Dispatch Notification For "
     * @param invoicePrefix          Prefix string in the email body for invoice number, e.g. "INVOICE NO - "
     * @param timeoutSeconds         Total seconds to wait before giving up
     * @param pollingIntervalSeconds Seconds to wait between polls
     * @return the matched invoice number if found, null if not found within timeout
     */
    public static String waitForInvoiceEmail(String[] subjectPrefixes, String invoicePrefix, int timeoutSeconds, int pollingIntervalSeconds) {
        long endTime = System.currentTimeMillis() + timeoutSeconds * 1000L;
        try {
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("imaps");
            store.connect(host, username, password);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            while (System.currentTimeMillis() < endTime) {
                // Search for unread messages
                Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
                System.out.println("Checking emails... Number of unread emails: " + messages.length);

                for (Message message : messages) {
                    String subject = message.getSubject();
                    if (subject != null) {
                        for (String prefix : subjectPrefixes) {
                            if (subject.startsWith(prefix)) {
                                // Try to extract invoice number from subject
                                String invoiceNumber = extractInvoiceFromSubject(subject, prefix);
                                if (invoiceNumber != null) {
                                    message.setFlag(Flags.Flag.SEEN, true);
                                    inbox.close(false);
                                    store.close();
                                    System.out.println("Invoice number found in subject: " + invoiceNumber);
                                    return invoiceNumber;
                                }
                            }
                        }
                    }

                    // Check body for invoice prefix
                    String body = getMessageBody(message);
                    if (body != null && body.contains(invoicePrefix)) {
                        String invoiceNumber = extractInvoiceFromBody(body, invoicePrefix);
                        if (invoiceNumber != null) {
                            message.setFlag(Flags.Flag.SEEN, true);
                            inbox.close(false);
                            store.close();
                            System.out.println("Invoice number found in body: " + invoiceNumber);
                            return invoiceNumber;
                        }
                    }
                }
                // Wait before next poll
                Thread.sleep(pollingIntervalSeconds * 1000L);
            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            throw new RuntimeException("Error reading emails", e);
        }
        System.out.println("Timeout reached. Invoice email not found.");
        return null;
    }

    private static String extractInvoiceFromSubject(String subject, String prefix) {
        try {
            int startIndex = prefix.length();
            // Guard against short subjects
            if (subject.length() >= startIndex + 7) {
                return subject.substring(startIndex, startIndex + 7);
            }
        } catch (Exception e) {
            System.err.println("Error extracting invoice from subject: " + e.getMessage());
        }
        return null;
    }

    private static String extractInvoiceFromBody(String body, String prefix) {
        try {
            int index = body.indexOf(prefix);
            if (index != -1 && body.length() >= index + prefix.length() + 7) {
                return body.substring(index + prefix.length(), index + prefix.length() + 7);
            }
        } catch (Exception e) {
            System.err.println("Error extracting invoice from body: " + e.getMessage());
        }
        return null;
    }

    private static String getMessageBody(Message message) throws MessagingException, IOException {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(mimeMultipart);
        }
        return null;
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws IOException, MessagingException {
        int count = mimeMultipart.getCount();
        if (count == 0)
            return "";
        boolean multipartAlt = new ContentType(mimeMultipart.getContentType()).match("multipart/alternative");
        if (multipartAlt)
            return getTextFromBodyPart(mimeMultipart.getBodyPart(count - 1));

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            BodyPart bp = mimeMultipart.getBodyPart(i);
            result.append(getTextFromBodyPart(bp));
        }
        return result.toString();
    }

    private static String getTextFromBodyPart(BodyPart bodyPart) throws IOException, MessagingException {
        if (bodyPart.isMimeType("text/plain")) {
            return (String) bodyPart.getContent();
        } else if (bodyPart.isMimeType("text/html")) {
            String html = (String) bodyPart.getContent();
            return org.jsoup.Jsoup.parse(html).text();
        } else if (bodyPart.getContent() instanceof MimeMultipart) {
            return getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
        }
        return "";
    }
}
