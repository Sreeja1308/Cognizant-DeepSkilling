package DecoratorPatternExample;

public class TestDecorator {
    public static void main(String[] args) {
       
        System.out.println("Basic Email Notification ");
        Notifier emailNotifier = new EmailNotifier();
        emailNotifier.send("Welcome to our platform!");
        
        System.out.println("\nEmail + SMS Notification");
        Notifier emailAndSMS = new SMSNotifierDecorator(new EmailNotifier());
        emailAndSMS.send("Your order has been confirmed!");
        
        System.out.println("\nEmail + SMS + Slack Notification");
        Notifier allChannels = new SlackNotifierDecorator(
                                new SMSNotifierDecorator(
                                new EmailNotifier()));
        allChannels.send("Critical system alert!");
    }
}