public class MinMax {
    //could improve by making all inside the same class but I got some erros
    static class Move
    {
        int row, col;
    };

    static char player = 'x', ai = 'o';

    //so function to see if there are moves remaining
    //loops through board
    static Boolean keepPlaying(char board[][])
    {
        for (int i = 0; i <3; i++)
            for (int j=0; j< 3; j++)
                if (board[i][j] == '_')
                    return true;
        return false;
    }

    //checks for a winner in 1 move
    static int evaluate(char board[][])
    {

        //checks for first box (top left)

            if (board[0][0] == board[0][1] && board[1][0] == board[0][1] && board[1][1] == board[0][0])
            {
                if(board[0][0] == player)
                    return +10;
                else if (board[0][0] == ai)
                    return -10;

            }

            //checks for second box (top right)
        
            if (board[0][1] == board[0][2] && board[1][1] == board[0][2] && board[1][2] == board[0][2])
            {
                if(board[0][2] == player)
                    return +10;
                else if (board[0][2] == ai)
                    return -10;

            }

            //checks for third box (bottom left)
            if (board[1][0] == board[1][1] && board[2][0] == board[1][1] && board[2][1] == board[1][0])
            {
                if(board[1][0] == player)
                    return +10;
                else if (board[1][0] == ai)
                    return -10;

            }
            //checks for fourth box (bottom right)
            if (board[1][1] == board[1][2] && board[2][1] == board[1][2] && board[2][2] == board[1][1])
            {
                if(board[2][2] == player)
                    return +10;
                else if (board[2][2] == ai)
                    return -10;

            }
        return 0;

    }

    //minmax
    //got some of this online
    static int minimax(char board[][],
                        int depth,Boolean isMax)
    {
        int score = evaluate(board);

        if (score == 10)
            return score;

        if (score == -10)
            return score;

        if (keepPlaying(board) == false)
            return 0;


        //loops to check max value
        if (isMax)
        {
            int best =-1000;

            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (board[i][j] == '_')
                    {
                        board[i][j] = player;
                        
                        //minmax function online
                        best = Math.max(best, minimax(board, depth + 1, !isMax));

                        board[i][j] = '_';
                    }
                } 
            }
            return best;
        }
        //loops to check min value
        else
        {
            int best = 1000;

            for(int i = 0; i<3; i++)
            {
                for (int j = 0; j<3; j++)
                {
                    if (board[i][j] == '_')
                    {
                        board[i][j] = ai;

                        best = Math.min(best, minimax(board,
                                    depth + 1, !isMax));
                        
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
        
    }
    //calls the minimax function to find the best move
    static Move findBestMove(char board[][])
    {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;


        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j <3; j++)
            {
                if (board[i][j] == '_')
                {
                    board[i][j]= player;

                    int moveVal = minimax(board, 0, false);

                    board[i][j] = '_';

                    //update if current is better than best
                    if(moveVal > bestVal)
                    {
                        bestMove.row=i;
                        bestMove.col=j;
                        bestVal= moveVal;
                    }
                }
            }
        }
        System.out.printf("The value of the best Move " +
                             "is : %d\n\n", bestVal);

        return bestMove;
    }

    //to test it out it could ask for user input but I didnt have time in class so I just modified the matrix by hand

    public static void main(String[] args)
    {
        //my idea is to do this but with a matrix for the lines and modify the evaluate function to find squares
        char board[][] = {{ '_', '_', '_' },
                        { '_', '_', '_' },
                        { '_', '_', '_' }};


    
        Move bestMove = findBestMove(board);
    
        System.out.printf("The Optimal Move is :\n");
        System.out.printf("ROW: %d COL: %d\n\n",
                bestMove.row, bestMove.col );
    }

}
