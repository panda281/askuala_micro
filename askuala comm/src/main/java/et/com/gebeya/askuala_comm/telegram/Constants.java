package et.com.gebeya.askuala_comm.telegram;

public class Constants {
  private Constants(){}
  public static final String WELCOME_MESSAGE = "Hello, Welcome to Askuala Comms bot.\n\n\nPlease Insert your username and password in a new line so that we can identify you. example\n\nabinet\nabinet@123";
  public static final String SUCCESSFUL_LOGIN_MESSAGE = "Welcome!!!\nfor whom do you want to text?";
  public static final String UNSUCCESSFUL_LOGIN_MESSAGE = "You inserted a wrong username or password please try again later";
  public static final String INCORRECT_INPUT_FOR_CHAT_TYPE="You inserted a wrong chat type. please try again\nFor whom do you want to text?";
  public static final String STUDENT_CHAT_TYPE="NICE!! Insert the Student ID\nYou can view all student by pressing the search button";
  public static final String TEACHER_CHAT_TYPE="NICE!! Insert the Teacher ID\nYou can view all teacher by pressing the search button";
  public static final String ID_NOTFOUND="SORRY, The user id that you inserted is not subscribed to Askuala comms. please try again\nFor whom do you want to text?";
  public static String idFound(String id){
      return "nice, now write a message that you want to send for user"+id;
  }

  public static String message(String name,String message){
    return "from "+name+"\nmessage: "+message;
  }

  public static final String SENT= "Your message has successfully sent\n\nfor whom do you want to text?";
}
