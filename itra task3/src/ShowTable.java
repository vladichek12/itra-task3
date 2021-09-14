import java.awt.*;
import java.awt.print.PrinterException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShowTable extends JFrame{

        private  String[] strings;
          JFrame frame = new JFrame("Test frame");


        public ShowTable(String[] strings){
            this.strings = strings;
        }

        public String[] getColumnsNames(){
            String[] columnNames = new String[strings.length+1];
            columnNames[0] = "User\\PC";
            for (int i = 1; i < columnNames.length; i++) {
                columnNames[i] = strings[i-1];
            }

            return columnNames;
        }

        public Object[][] getData(){
            Object[][]data = new Object[strings.length][strings.length+1];
            for (int i = 0; i < strings.length; i++) {
                data[i][0] = strings[i];
            }

            for(int i = 0 ;i < strings.length;i++)
                for (int j = 1; j < strings.length+1; j++) {
                    Regulations game = new Regulations(strings,j);
                    if(i == j-1)
                        data[i][j] = "DRAW";
                    else if(!game.getWinMoves(game.getMoves(), game.getMove()).contains(data[i][0]))
                        data[i][j] = "WIN";
                    else
                        data[i][j] = "LOSE";
                }

            return data;
        }


        public void tableDraw() throws PrinterException {

            JTable table = new JTable(getData(), getColumnsNames());

            JScrollPane scrollPane = new JScrollPane(table);

            frame.getContentPane().add(scrollPane);
            frame.setPreferredSize(new Dimension(450, 200));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
}