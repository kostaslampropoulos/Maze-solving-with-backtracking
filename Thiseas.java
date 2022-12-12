import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Thiseas {

    static Integer[] Firstmove(Integer pos[], Integer[][] maze, int lines, int columns){

        if(pos[0].equals(0)){ //If the starting point in the y axis is 0 then move up
            pos[0]++;
            maze[pos[0]][pos[1]] = 2; //Mark down that you have been in this position
        }
        else if(pos[0].equals(lines-1)){ //If the starting point in the y axis is lines -1 then move down 
            pos[0]--;
            maze[pos[0]][pos[1]] = 2; //Mark down that you have been in this position
        }
        if(pos[1].equals(0)){ //If the starting point in the x axis is 0 then move down
            pos[1]++;
            maze[pos[0]][pos[1]] = 2; //Mark down that you have been in this position
        }
        else if(pos[1].equals(columns-1)){//If the starting point in the x axis is columns -1 then move down
            pos[1]--;
            maze[pos[0]][pos[1]] = 2; //Mark down that you have been in this position
        }
        
        //Now we have moved the player inside the maze with the entrance already being a 1 so that 
        //the algorithm does not see it as an escape

        return pos; //Return the position after the first move
    }
    
    static void Nextmove(Integer pos[], Integer[][] maze, StringStackImpl<Integer> stack_LR, StringStackImpl<Integer> stack_UD){

        //now we check if there is an available space up, down, right or left from the player

        if (maze[pos[0]+1][pos[1]].equals(0)){
           stack_UD.push(+1); //Store our move to the stacks
            stack_LR.push(0);
            pos[0]+=1; //Transfer the player to its new position
            maze[pos[0]][pos[1]] = 2; //Mark doen the box as "seen"
        } 
        else if(maze[pos[0]-1][pos[1]].equals(0)){
            stack_UD.push(-1); //Store our move to the stacks
            stack_LR.push(0);
            pos[0]-=1; //Transfer the player to its new position
            maze[pos[0]][pos[1]] = 2; //Mark doen the box as "seen"
        }
        else if(maze[pos[0]][pos[1]+1].equals(0)){
            stack_LR.push(+1); //Store our move to the stacks
            stack_UD.push(0);
            pos[1]+=1; //Transfer the player to its new position
            maze[pos[0]][pos[1]] = 2; //Mark doen the box as "seen"
        }
        else if(maze[pos[0]][pos[1]-1].equals(0)){
            stack_LR.push(-1); //Store our move to the stacks
            stack_UD.push(0);
            pos[1]-=1; //Transfer the player to its new position
            maze[pos[0]][pos[1]] = 2; //Mark doen the box as "seen"
        } 
        else{
            maze[pos[0]][pos[1]] = 1; //Start filling the dead ends as a 1 so that they are closed of for future moves
        }
    }
    
    public static void main(String[] args) {
        BufferedReader reader = null;
        String l;

        try {

            //String path = "a.txt";
            String path = args[0];
            reader = new BufferedReader(new FileReader(new File(path)));
            l = reader.readLine();
                
            String s = l.trim();
            int i = 0;
            int lines = 0, columns = 0; //number of lines / columns in the array
            for (i = 0; i <= s.length(); i++){
                if (s.substring(i,i+1).equals(" ")){ //in case there are double digit lines
                    lines = Integer.valueOf(l.trim().substring(0, i)); //Read the number of lines from the txt doc
                    break;
                }
            }
            columns = Integer.valueOf(s.substring(i, s.length()).trim()); //Read the number of columns from the txt doc
            
            l = reader.readLine();
            s = l.trim();
            i = 0; 
            int ent_l = 0, ent_c = 0; //entrance line / entrance column
            for (i = 0; i <= s.length(); i++){
                if (s.substring(i,i+1).equals(" ")){ //in case the entrance line is in double digits
                    ent_l = Integer.valueOf(l.trim().substring(0, i)); //Read the index of the entrance line
                    break;
                }
            }
            ent_c = Integer.valueOf(s.substring(i, s.length()).trim()); //Read the index of the entrance column

            Integer [][] maze = new Integer [lines][columns];
            
            boolean solvable = true;
            boolean entrance_exists = false;

            String [] line;
            
            for (i=0; i<maze.length; i++){
                
                l = reader.readLine();
                if (l == null){
                    System.out.println("Wrong number of lines on the maze itself!");
                    solvable = false;
                    break;
                }else {
                    line = l.trim().split(" "); //Every value of the line[] is a "0" or a "1"
                }
                if (line.length != columns){
                    System.out.println("Incorrect number of columns declared!");
                    solvable = false;
                    break;
                }
                for (int j = 0; j < line.length; j++){
                    if (line[j].equals("E")){
                        if (ent_c == j && ent_l == i){
                            line[j] = "1"; //Make the entrance a 1 so that the backtracking does not consider it as an escape
                            entrance_exists = true;
                        }
                        else{
                            System.out.println("Entrace cordinates don't match map cordinates!");
                            solvable = false;
                        
                        }
                    }
                    int point = Integer.parseInt(line[j]);
                    if (point != 1 && point != 0){
                        System.out.println("Number diffrent to 0 and 1 found on map!");
                        solvable = false;
                    } else{
                        maze[i][j] = point;
                    }
                
                }
                     
            } 
            
            if(entrance_exists != true){
                System.out.println("No entrance found on map!");
                solvable = false;
            }
            
            StringStackImpl<Integer> stack_LR = new StringStackImpl<Integer>(); //Make a stack data structure where we store our horizontal moves
            StringStackImpl<Integer> stack_UD = new StringStackImpl<Integer>(); //Make a stack data structure where we store our vertical moves
            Integer [] player = new Integer [2]; //Initialize the player who will solve the maze

            player[0] = ent_l;
            player[1] = ent_c;
            
            if (solvable){
                player = Firstmove(player, maze, lines, columns); //Initialize the players first move based on the starting point
            }

            /* 
            System.out.print("Runner:\ngrammh: ");
            System.out.println(player[0]);
            System.out.print("sthlh: ");
            System.out.println(player[1]);
            */
            Integer[] next = new Integer[2];

            while(solvable){
                /* 
                System.out.print("Runner:\ngrammh: ");
                System.out.println(player[0]);
                System.out.print("sthlh: ");
                System.out.println(player[1]);
                for (i = 0; i <= lines-1; i++){
                        for (int j = 0; j <= columns-1; j++){
                            System.out.print(maze[i][j]);
                            System.out.print(" ");
                            }
                        System.out.print("\n");
                    }
                System.out.print("\n\n\n");
                */
                if(player[0].equals(lines-1) || player[1].equals(columns-1) || player[0].equals(0) || player[1].equals(0)){ //If the player finds himself on the edge of the maze again, then he must have solved it
                    System.out.println("\nYou Won!");
                    System.out.print("\nWinning horizontal position(0-" + (lines-1) + "): ");
                    System.out.print(player[0]);
                    System.out.print("\nWinning vertical position(0-" + (columns-1) + "): ");
                    System.out.print(player[1]);
                    System.out.print("\n\n");
                    break;
                }

                next[0] = player[0]; //Temp value for the players line
                next[1] = player[1]; //Temp value for the players column
                
                Nextmove(next, maze, stack_LR, stack_UD); //The next move is being calculated and being stored in the temporary variable "next"

                // If left, right, up and down of the player there is a 1 then there is no next move, hence no solution 
                if(player[0].equals(next[0]) && player[1].equals(next[1])){
                    if(maze[player[0]][player[1]+1].equals(1) && maze[player[0]][player[1]-1].equals(1) && maze[player[0]+1][player[1]].equals(1) && maze[player[0]-1][player[1]].equals(1)){

                        System.out.println("There is no solution to the maze!");
                        solvable = false;

                    }
                    else{
                        //Backtracking
                        player[0] -= stack_UD.pop(); //We get the players next move by looking at the log we kept on the Up - Down stack (stack_UD)
                        player[1] -= stack_LR.pop(); //We get the players next move by looking at the log we kept on the Left - Right stack (stack_LR)

                    }
                }else{
                    // if backtracking is not needed then the player is moved to the calculated next move
                    player[0] = next[0];
                    player[1] = next[1];
                }

            }

        } catch (IOException e) {
            //Inform the user that the File is not readable for the making of the maze
            System.out.println("\nError reading file...\n");
        }
    }
}