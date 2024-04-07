public class MazeRunner{
    private int[][] maze;
    public MazeRunner(){
        int sizex=10;
        int sizey=10;
        maze=new int[][]
            {{1,0,0,0,0,1,1},
            {1,1,1,1,0,1,0},
            {0,0,1,0,0,1,0},
            {0,1,1,1,0,1,0},
            {0,1,0,1,0,1,0},
            {0,1,0,1,1,1,0},
            {0,1,0,1,0,1,1}};
    }
    // 0 is down. 1 is up. 2 is right. 3 is left.
    public boolean[] scanArea(int rPos, int cPos, int spaceKey){
        boolean [] AreaInformation = new boolean[4];
        // check down
        if(rPos + 1 < maze.length && maze[rPos+1][cPos] == spaceKey){
            AreaInformation[0] = true;
        }
        // check up
        if(rPos - 1 >= 0 && maze[rPos-1][cPos] == spaceKey){
            AreaInformation[1] = true;
        }
        // check right
        if(cPos + 1 < maze[rPos].length && maze[rPos][cPos+1] == spaceKey){
            AreaInformation[2] = true;
        }
        // check left
        if(cPos - 1 >= 0 && maze[rPos][cPos-1] == spaceKey){
            AreaInformation[3] = true;
        }
        return AreaInformation;
    }
    public boolean findExit(){
        boolean found=false;

        int rPos = 0;
        int cPos = 0;

        int checkValue=1;

        while(!found && maze[rPos][cPos] != 3){
            maze[rPos][cPos] = checkValue+1;

            if(rPos == maze.length-1 && cPos == maze[rPos].length-1){
                found=true;
            }else{
                boolean[] areaPing = scanArea(rPos, cPos, checkValue);

                if(areaPing[0]){
                    rPos = rPos+1;
                }else if(areaPing[1]){
                    rPos = rPos-1;
                }else if(areaPing[2]){
                    cPos = cPos+1;
                }else if(areaPing[3]){
                    cPos = cPos-1;
                }else{
                    checkValue+=1;
                }

                if(checkValue==2){
                    areaPing = scanArea(rPos, cPos,1);
                    if(areaPing[0]){
                        checkValue=1;
                        rPos = rPos+1;
                    }else if(areaPing[1]){
                        checkValue=1;
                        rPos = rPos+1;
                    }else if(areaPing[2]){
                        checkValue=1;
                        cPos = cPos+1;
                    }else if(areaPing[3]){
                        checkValue=1;
                        cPos = cPos-1;
                    }
                }
            }
        }
        for(int x = 0; x < maze.length; x++){
            for(int y = 0; y < maze[x].length; y++){
                System.out.print(maze[x][y]+" ");
            }
            System.err.print("\n");
        }
        return found;
    }
}