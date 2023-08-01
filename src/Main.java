import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User user=new User("34913710081", BigDecimal.valueOf(100000.50));

        try {
            ServerSocket s = new ServerSocket(7000);
            System.out.println("waiting for the Bank B Response");
            Socket server=s.accept();
            System.out.println("Bank A is connected with Bank B");
            //send data from Bank A to Bank B
            Scanner sc =new Scanner(System.in);
            PrintWriter pr=new PrintWriter(server.getOutputStream());
            while(true){
//              // Please Enter the amount which you want to transfer
                BigDecimal data=sc.nextBigDecimal();
                if(user.getBalance().compareTo(data)>=0) {
                    user.setBalance(user.getBalance().subtract(data));
                    pr.println(data);
                    pr.flush();
                    System.out.println("Money Transferred, " + "New updated balance:  " + user.getBalance());
                }
                else{
                    System.out.println("insufficient balance");
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}