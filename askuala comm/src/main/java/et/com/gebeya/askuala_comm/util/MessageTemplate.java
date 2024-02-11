package et.com.gebeya.askuala_comm.util;

public class MessageTemplate {
    private MessageTemplate(){
    }
    public static String addAccountMessage(String name,String username,String password){
        return "Hello "+ name+ "\nThank you for Joining Asquala School.\nYour Account is\n\nUsername: "+username+"\nPassword: "+password+"\n\n";
    }
    public static String updateAccountMessage(String name){
        return "Hello "+name+"\nYour account successfully updated";
    }
    public static String paymentPendingMessage(String name,Double price,String month){
        return "Hello "+name+ "'s guardian,\nYour "+month+"'s tuition fee is "+price+".\nThe due date is after 10 days so make sure you pay on the given time.\nThank you.";
    }
    public static String paymentSuccessfulMessage(String name,String month){
        return "Hello "+name+ "'s guardian,\nYour "+month+"'s tuition fee have been paid successfully.\nThank you.";
    }

}
