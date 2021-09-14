import java.awt.print.PrinterException;
import java.security.SecureRandom;
import java.util.Scanner;

public class Game {

    public static boolean haveSameStrings(String[] strings){
        boolean flag = false;
        for (int i = 0; i < strings.length; i++) {
            for (int j = i+1; j < strings.length; j++) {
                if(strings[i].equals(strings[j]))
                    flag = true;
            }
        }
        return flag;
    }

    public static void main(String[] args) throws NullPointerException, PrinterException {
       if(args.length < 3 || args.length % 2 == 0 || haveSameStrings(args)) {
          System.out.println("Invalid input. Try again");
          System.exit(1);
       }
       do {

           KeyGenerator generator = new KeyGenerator(new SecureRandom(),args);
           Integer ComputerMove = generator.getComputerMove();

           generator.getHmac();

           System.out.println("Available moves:");
           for (int i = 0; i < args.length; i++) {
               System.out.println((i + 1) + " - " + args[i]);
           }
           System.out.println("0 - Exit");
           System.out.println("-1 - help");


           System.out.print("Enter you move: ");
           Scanner in = new Scanner(System.in);
           String PlayerMove = in.nextLine();
           if (Integer.parseInt(PlayerMove) == 0)
               System.exit(0);
           else if (PlayerMove.equals("-1")) {
               ShowTable table = new ShowTable(args);
               table.tableDraw();
               System.out.println();
               //System.exit(0);

           }
           else {
               System.out.println(PlayerMove);
               System.out.println("Your move: " + PlayerMove + " : " + args[Integer.parseInt(PlayerMove) - 1]);
               System.out.println("Computer move: " + ComputerMove + " : " + args[ComputerMove - 1]);
               Regulations game = new Regulations(args, Integer.parseInt(PlayerMove));
               if (Integer.parseInt(PlayerMove) == ComputerMove)
                   System.out.println("Draw");
               else if (game.getWinMoves(game.getMoves(), game.getMove()).contains(args[ComputerMove - 1]))
                   System.out.println("You win");
               else System.out.println("You lose");

               generator.getHmacKey();
               System.out.println();
           }
       }while (true);

    }
}