import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date
 */
public class MyWorld extends World
{
    private String[][] grid;
    private int playerTurn;
    private String playerBlack;
    private String playerRed;
    private String playerBlackInitial;
    private String playerRedInitial;
    private int playerBlackWinTotal;
    private int playerRedWinTotal;
    private double playerBlackWinPercentage;
    private double playerRedWinPercentage;
    private int goalInARow;
    private int firstPlayerTurn;
    private boolean firstPlay;
    private String lastPlayedInitial;
    private int tieCounter;
    private int gameCounter;
    
    
    
    private int turnCounter;
    private int col1x, col1y, colDiff;
    private int actCounter;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld() 
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        grid = new String[6][7];    //to keep track of the game board
        firstPlayerTurn = (int)(Math.random()*2+1);   //randomize who starts the game
        playerBlack = "PlayerBlack";
        playerRed = "PlayerRed";
        playerBlackInitial = "b";  //to fill the 2d array grid to know which checker is where
        playerRedInitial = "r";   //to fill the 2d array grid to know which checker is where
        playerBlackWinTotal = 0;  //scoreboard
        playerRedWinTotal = 0;    //scoreboard
        goalInARow = 5;//because it is Connect 4
        actCounter = 20;//sets the speed of the game
        firstPlay = true; //determines if it's first play of new game, so we can alternate first turn each game
        lastPlayedInitial = ""; //keeps track of who played last
        tieCounter = 0;
        gameCounter = 0;
        
        
        
        turnCounter = 1; //used to keep track of number of plays made
        col1x=176; //to place checkers in the correct location
        col1y=72; //to place checkers in the correct location
        colDiff=41; //to place checkers in the correct location
        
        // SIZE TODO: Fix Board()
        addBoard();

    }
    
    public void addBoard() {
        // Size: 41 x 38
        //  (matches rowDiff & colDiff)
        for (int r=0; r<grid.length; r++) {
            for (int c=0; c<grid[0].length; c++) {
                addObject(new Tile(), col1x+(c*colDiff), col1y+(r+1)*38);
            }
        }
        
        addObject(new Top(), col1x+((grid[0].length-1)*colDiff)/2, col1y-30);
        addObject(new BlackSymbol(), col1x-2*colDiff, col1y-30);
        addObject(new RedSymbol(), col1x + (grid[0].length+1)*colDiff, col1y-30);
        addObject(new Bottom(), col1x+((grid[0].length-1)*colDiff)/2, col1y + (grid.length+1)*38);
    }

    public void act(){
        
        
        String winner = checkForWinner();  //see if there is a winner
        if(winner != null || tie()){ //if there is a winner or a tie
                
                
            
                if(winner != null && winner.equals("r"))  //red is the winner
                {
                    showPlayer2Winner();
                    
                }
                else if(winner != null && winner.equals("b")) //black is the winner
                {
                    showPlayer1Winner();
                    
                }
                
                //resetGrid();
                
                if(turnCounter % (actCounter) == 0) //after a win,allows last checker to fall before the grid is cleared
                {
                    Greenfoot.delay(500); //allows the highlighted winner more time to be seen
                    resetGame();  //resets game
                    
                }
                
            }
            
            
            if(turnCounter % actCounter == 0){  //this controls the speed of the game
                if(firstPlay && firstPlayerTurn == 1){ //runs only first time of game, to alternate first play of each game between players

                    playerBlackStrategy();   //runs player1 code
                    firstPlayerTurn = 2;    //alternates who plays first next game
                    playerTurn = 2;  //switches to other player
                    firstPlay = false;  //will be set back to true when next game starts
                    
                }
                else if(firstPlay && firstPlayerTurn == 2){//runs only first time of game, to alternate first play of each game between players
                    
                    playerRedStrategy();    //runs player2 code
                    firstPlayerTurn = 1;    //alternates who plays first next game
                    playerTurn = 1;   //switches to other player
                    firstPlay = false;  //will be set back to true when next game starts
                    
                }
                else if(playerTurn==1){  //alternates players
                    playerBlackStrategy();   //runs player1 code
                    
                    playerTurn = 2;  //switches to other player
                }
                else if(playerTurn==2){  //alternates players
                    playerRedStrategy();    //runs player2 code
                    
                    playerTurn = 1;   //switches to other player
                }
                
               }
        
      
            
                turnCounter++;    //adds to turn counter
              
                    
    }
    
    public void playerBlackStrategy(){
        // SIZE TODO: Fix formatting here
        //leave these top 4 lines at the top
        playerBlack = "Tito";  //put player1 name here
        showText(playerBlack,70,100); //shows your name
        GreenfootImage image1 = new GreenfootImage("black.png"); //sets image to black checker
        String colorInitial = new String("b"); //fills the 2d array grid with this value
        
        
        
        
        //replace the following code with your strategy
        //but the second line shows you how to place your checker
        int colToPlace = (int)(Math.random()*(grid[0].length));
        
        placeCol(colToPlace,colorInitial,image1);  //plays in the specified col and fills the 2D array with the 2nd parameter String
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
    
    public void playerRedStrategy(){
        // SIZE TODO: Fix formatting here
        //leave these top 3 lines at the top
        playerRed = "Janet";  //put your name here
        showText(playerRed,getWidth()-70,100); //shows your name
        GreenfootImage image2 = new GreenfootImage("red.png");  //sets image to red checker
        String colorInitial = new String("r");  //fills the 2d array grid with this value
        
        
        
        
        
        //replace the following code with your strategy
        //but the second line shows you how to place your checker
        int colToPlace = (int)(Math.random()*(grid[0].length));
        
        placeCol(colToPlace,colorInitial,image2);  //plays in the specified col and fills the 2D array with the 2nd parameter String
         
        
        
        
        
        
        
        
        
        
        
        
    }
    
    public void placeCol(int column,String teamInitial, GreenfootImage color){
        
        
            
        if(!lastPlayedInitial.equals(teamInitial)){//ensures placeCol() can only be called once per player per turn
        
        //places checker and updates grid
            if(grid[0][column] == null){//makes sure there is a place to play, if not a random col is selected
                addObject(new Checker(column,color),col1x+(column * colDiff),col1y);
                grid[0][column] = teamInitial; //adds initial to 2d grid
                dropGrid(); //drops teamInitial down the column to bottom
            }
            else{//a random column is selected
                placeCol((int)(Math.random()*(grid[0].length)),teamInitial, color);
                
            }
        
        }
    
        
        
        
        
        lastPlayedInitial = teamInitial;
        
    }
    
    private void dropGrid()
    {
        
        //drops new "b" or "r" to its new location in grid
        int moveToRow = 0;
        for(int c = 0; c < grid[0].length;c++)
        {
            for(int r = 0; r < grid.length - 1;r++)
            {
                if(grid[r][c] != null && grid[r + 1][c] == null)
                {
                    grid[r+1][c] = grid[r][c];
                    grid[r][c] = null;
                }
                else if(grid[r][c] != null && grid[r + 1][c] != null)
                {
                    r+=8;
                }
            }
        }
        
        
        
        
    }
    
    public String checkForWinner(){
        
        //easier to build a String from the 2d array grid, and then check for consecutive initials
        String checkersAsAString = new String();
       
        //check each row for a winner, starting at bottom
        for(int r = grid.length - 1; r >= 0; r--){
            checkersAsAString = ""; //reset String for each row
            for(int c = 0; c < grid[0].length; c++){
                if(grid[r][c] == null)
                    checkersAsAString+="-";//adds dash to the String if cell is empty
                else
                    checkersAsAString+= grid[r][c]; //adds player initial to String
            
                if(checkersAsAString.contains(playerBlackInitial+playerBlackInitial+playerBlackInitial+playerBlackInitial))
                {
                    circleRow(r,checkersAsAString, playerBlackInitial);  //calls the winning row to be circled
                    return playerBlackInitial;
                }
                else if(checkersAsAString.contains(playerRedInitial+playerRedInitial+playerRedInitial+playerRedInitial))
                {
                    circleRow(r,checkersAsAString, playerRedInitial); //calls the winning row to be circled
                    return playerRedInitial;
                }
            }
        }
        
        
        //check each column for a winner
        for(int c = 0; c < grid[0].length; c++){
            checkersAsAString = "";  //reset String for each column
            for(int r = grid.length - 1; r >= 0; r--){
                if(grid[r][c] == null)
                    checkersAsAString+="-"; //adds dash to the String if cell is empty
                else
                    checkersAsAString+= grid[r][c]; //adds the initial to the String
            
                if(checkersAsAString.contains(playerBlackInitial+playerBlackInitial+playerBlackInitial+playerBlackInitial))
                {
                    circleColumn(c,checkersAsAString,playerBlackInitial);   //highlights winning column
                    return playerBlackInitial;
                }
                else if(checkersAsAString.contains(playerRedInitial+playerRedInitial+playerRedInitial+playerRedInitial))
                {
                    circleColumn(c,checkersAsAString,playerRedInitial);  //highlights winning column
                    return playerRedInitial;
                }
            }
        }
        
        // Checks for diagonal wins (new algorithm)
        // To agree with previous code would have to proceed
        //  botleft->topright and botright->topleft
        // BUT, that's inconvenient for dynamic grids, so we go
        //  botleft->topright and topleft->botright

        // Algorithm details:
        //  Basically, virtually extends the board to the left by <rows-1> cols
        //  That way all diagonals on the board start from a valid column
        //   on the *virtual* board
        //  Iterate from every column on the virtual board, and you're done!
        //  Iterating from the bottoms of the columns instead will do the botleft-topright ("reversed") diagonals
        // Note: the strings in diags_top are all gridLimit() long exactly
        //  if a diagonal is too short the spaces are filled in the '.'s

        // PART 1: Checking topleft->botright

        // Left of the virtual board
        int virtual_board_left = -(grid[0].length-1);
        // Diagonals serialized as strings
        String[] diags_top = new String[grid.length + grid[0].length - 1];
        // Initalize strings real quick
        for (int i=0; i<diags_top.length;i++) {
            diags_top[i] = new String();
        }
        // Main loop
        for (int b=virtual_board_left; b<grid.length; b++) {
            // Continue checking rows, moving right 1 each time,
            //  until we hit the bottom
            for (int i=0; i<gridLimit(); i++) {
                // If position in grid, add entry
                int r = i;
                int c = b+i;
                boolean in_bounds = r > 0 && r < grid.length && c > 0 && c < grid[0].length;
                if (in_bounds) {
                    if (grid[r][c] == null) {
                        diags_top[b-virtual_board_left] += "-";
                    } else {
                        diags_top[b-virtual_board_left] += grid[r][c];
                    }
                } else {
                    diags_top[b-virtual_board_left] += ".";
                }
            }
        }

        // PART 2: Checking botleft->topright

        // Diagonals serialized as strings
        String[] diags_bot = new String[grid.length + grid[0].length - 1];
        // Initalize strings real quick
        for (int i=0; i<diags_bot.length;i++) {
            diags_bot[i] = new String();
        }
        // Main loop
        for (int b=virtual_board_left; b<grid.length; b++) {
            // Continue checking rows, moving right 1 each time,
            //  until we hit the bottom
            for (int i=0; i<gridLimit(); i++) {
                // If position in grid, add entry
                int r = (grid.length-1) - i;
                int c = b+i;
                boolean in_bounds = r > 0 && r < grid.length && c > 0 && c < grid[0].length;
                if (in_bounds) {
                    if (grid[r][c] == null) {
                        diags_bot[b-virtual_board_left] += "-";
                    } else {
                        diags_bot[b-virtual_board_left] += grid[r][c];
                    }
                } else {
                    diags_bot[b-virtual_board_left] += ".";
                }
            }
        }

        for (int i=0; i<diags_top.length; i++) {
            int virtual_col = i + virtual_board_left;

            if (containsWinningString(diags_top[i], playerBlackInitial)) {
                circleVirtualDiagonalPos(virtual_col, diags_top[i], playerBlackInitial);
                return playerBlackInitial;
            } else if (containsWinningString(diags_top[i], playerRedInitial)) {
                circleVirtualDiagonalPos(virtual_col, diags_top[i], playerRedInitial);
                return playerRedInitial;
            }
        }
        for (int i=0; i<diags_bot.length; i++) {
            int virtual_col = i + virtual_board_left;

            if (containsWinningString(diags_bot[i], playerBlackInitial)) {
                circleVirtualDiagonalNeg(virtual_col, diags_bot[i], playerBlackInitial);
                return playerBlackInitial;
            } else if (containsWinningString(diags_bot[i], playerRedInitial)) {
                circleVirtualDiagonalNeg(virtual_col, diags_bot[i], playerRedInitial);
                return playerRedInitial;
            }
        }
    
        return null; //returns null if there is no winner
    
    }

    // If you're iterating simultaneously on row and col
    //  this is the max
    // Useful for diagonals
    public int gridLimit() {
        if (grid.length < grid[0].length) {
            return grid.length;
        } else {
            return grid[0].length;
        }
    }
    public void circleVirtualDiagonalPos(int virt_col, String str, String initial) {
        int start = str.indexOf(initial+initial+initial+initial);
        if (start < 0) {
            System.out.println("ERROR! INVALID STRING PASSED TO circleVirtualDiagonalPos");
        }

        //System.out.println("(P)---" + str);
        for (int i=start; i<start+4; i++) {
            int r = i;
            int c = virt_col+i;
            //System.out.println(r + ", " + c);
            addObject(new Ring(),col1x+(c*colDiff),col1y+(r+1)*38);
        }
    }
    public void circleVirtualDiagonalNeg(int virt_col, String str, String initial) {
        int start = str.indexOf(initial+initial+initial+initial);
        if (start < 0) {
            System.out.println("ERROR! INVALID STRING PASSED TO circleVirtualDiagonalNeg");
        }

        //System.out.println("(N)--- " + str);
        for (int i=start; i<start+4; i++) {
            int r = (grid.length-1)-i;
            int c = virt_col+i;
            //System.out.println(r + ", " + c);
            addObject(new Ring(),col1x+(c*colDiff),col1y+(r+1)*38);
        }
    }
    
    
    
    
    public void circleRow(int r, String str, String initial){
        //adds the rainbow rings to the correct locations after a win
        
        //check the indexOf so we know where the winner rings should start
        int start = str.indexOf(initial+initial+initial+initial);
        
        for(int c = start; c < start+4; c++){
            addObject(new Ring(),col1x+(c * colDiff),col1y+(r+1)*38);
            
        }
    }
    
    
    public void circleColumn(int c, String str, String initial){
        
        //adds the rainbow rings to the correct locations after a win
        
        //System.out.println(str);
        int start = str.indexOf(initial+initial+initial+initial);
        start = (grid.length-1) - start;
        
        
            
        for(int r = start; r > start-4; r--){
            addObject(new Ring(),col1x+(c * colDiff),col1y+(r+1)*38);
            
        }
    }
    
    
    
    public boolean containsWinningString(String str, String playInit){
        
        //checks if a string contains four identical initials in a row
        return str.contains(playInit+playInit+playInit+playInit);
        
    }
    
    
    public boolean tie()
    {
        //if there is a null in the grid, there is not a tie
        for(int i = 0;i<grid[0].length;i++)
        {
            if(grid[0][i]==null)
            {
                return false;
            }
        }
        tieCounter++;
        
        showText("Ties: " + tieCounter/actCounter,getWidth()/2,370); //shows number of ties
        
        
        
        
        
        return true;
    }
    
    public void showPlayer1Winner(){
        //adds 1 to player1 total wins
        playerBlackWinTotal+=1;
        
        //divides by actCounter since this code 
        //runs actCounter times before game is cleared
        int wins = playerBlackWinTotal/actCounter;
        int wins1 = playerRedWinTotal/actCounter;
        int games = wins + wins1 ;
        
        
    
        //calculates and shows all the stats
        showText("Wins: " + wins,70,130); //shows the total wins
        double winPercentage = (double)(wins)/(games);
        double winPercentageRounded = 100*Math.round(winPercentage*1000)/1000.0;
        showText(winPercentageRounded + "%",70,160); //shows the total win %
        double opponentPercentage = Math.round((100.0-winPercentageRounded)*1000)/1000.0;
        showText(opponentPercentage + "%",getWidth()-70,160); //shows the total win % for other player
    
        addObject(new BigRing(),70,130); //highlights winning name
        
        
    
        
        
    }
    
    public void showPlayer2Winner(){
        
        //adds 1 to player2 total wins
        playerRedWinTotal+=1;
        
        //divides by actCounter since this code 
        //runs actCounter times before game is cleared
        int wins = playerBlackWinTotal/actCounter;
        int wins1 = playerRedWinTotal/actCounter;
        int games = wins + wins1 ;
        
        
        
        //calculates and shows all the stats
        showText("Wins: " + wins1,getWidth()-70,130); //shows the total wins
        double winPercentage = (double)(wins1)/(games);
        double winPercentageRounded = 100*Math.round(winPercentage*1000)/1000.0;
        showText(winPercentageRounded + "%",getWidth()-70,160); //shows the total win %
        double opponentPercentage = Math.round((100.0-winPercentageRounded)*1000)/1000.0;
        showText(opponentPercentage + "%",70,160); //shows the total win % for other player
    
        addObject(new BigRing(),getWidth()-70,130); //highlights winning name
        
        
    
    }
    
    public void resetGrid(){
        //resets 2d array after a victory
         for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
            
            grid[r][c] = null;
            
            
            }
            
        }
        
        
    }
    
    public void resetGame(){
        
        gameCounter++;
        showText("Games played : " + gameCounter,getWidth()/2,15); //shows number of games
        
        
        //clears all checkers, rings, and board
        List objects = getObjects(null);
        removeObjects(objects);
        
        addBoard();
        
        resetGrid(); //clears 2d array
        firstPlay = true; //resets firstPlay to true
        lastPlayedInitial = ""; //allows the first player of the next game to alternate
        playerTurn = 0;
        //so the first player of each game will alternate
        
    }
    
    
    public String[][] getGrid(){
        return this.grid;
    }
}
