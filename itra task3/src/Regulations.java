import java.util.ArrayList;

public class Regulations {
    private String[] Moves;
    private Integer Move;

    public Regulations(String[] Moves,Integer ComputerMove){
        this.Moves = Moves;
        this.Move = ComputerMove;
    }

    public Integer getMove() {
        return Move;
    }

    public String[] getMoves() {
        return Moves;
    }

    public static ArrayList<String>  getWinMoves(String[] Moves, Integer Move){
        ArrayList<String>WinMoves = new ArrayList<String>();
        Integer iterator = (Moves.length-1)/2;
        for(int i = Move;i < Moves.length;i++){
            if(iterator == 0 )break;
            WinMoves.add(Moves[i]);
            iterator--;
        }
        if(iterator> -1)
            for(int i =0; i<iterator;i++)
                WinMoves.add( Moves[i]);

        return WinMoves;
    }
}