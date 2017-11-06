import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Maze extends JPanel implements ActionListener {

    private JButton startstop = new JButton("Start Maze");
    private JButton generate = new JButton("Generate Maze");
    private JSlider rowSlider = new JSlider(JSlider.HORIZONTAL, 10, 50, 10);
    private JSlider columnSlider = new JSlider(JSlider.HORIZONTAL,10,50,10);

    private JPanel buttonPanel = new JPanel(new GridLayout(4,4,5,5));
    private JPanel maze;
    private JFrame mazeFrame = new JFrame();
    private MazePanel mazePanel = new MazePanel();

    static int[][] index;
    static int width=20,height=20;
    static int[][] myMaze;
    static Path[][] mazePath;
    static boolean[][] solution;
    static DisjointSets mazeSet;


    public Maze() {


        startstop.setPreferredSize(new Dimension(20,20));
        generate.setPreferredSize(new Dimension(20,20));
        mazeFrame.setTitle("Maze Game");
        mazeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mazeFrame.setSize(600,600);
        mazeFrame.setLocationRelativeTo(null);


        rowSlider.setMinorTickSpacing(2); //small tick marks
        rowSlider.setMajorTickSpacing(10); //every 10
        rowSlider.setPaintTicks(true);
        rowSlider.setPaintLabels(true);

        columnSlider.setMinorTickSpacing(2);
        columnSlider.setMajorTickSpacing(10);
        columnSlider.setPaintTicks(true);
        columnSlider.setPaintLabels(true);

        buttonPanel.add(startstop);
        buttonPanel.add(rowSlider);
        buttonPanel.add(columnSlider);
        buttonPanel.add(generate);


        buttonPanel.setPreferredSize(new Dimension(150,500));
        mazeFrame.add(buttonPanel, BorderLayout.EAST);
        mazePanel.setPreferredSize(new Dimension(500,500));


        buildMazeMatric();
        maze=new MazeSolution(myMaze,20);
        maze.setLayout(new BorderLayout());
        mazeFrame.add(maze, BorderLayout.WEST);
        mazeFrame.pack();

        rowSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                int rowSource =((JSlider)ce.getSource()).getValue();
                width = rowSource;
            }
        });
        columnSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ve) {
                int columnSource = (((JSlider)ve.getSource()).getValue());
                height = columnSource;
                mazePanel.setColumn(columnSource);
                mazeFrame.add(mazePanel, BorderLayout.WEST);
            }
        });


          generate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("New Dimensions");
                System.out.println("Width:"+ width +" Height:"+ height);
                mazeFrame.getContentPane().remove(maze);
                buildMazeMatric();
                maze=new MazeSolution(myMaze,10);
                maze.setLayout(new BorderLayout());
                mazeFrame.add(maze, BorderLayout.WEST);
                mazeFrame.revalidate();
                mazeFrame.pack();
                mazeFrame.repaint();
            }
        });


        startstop.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Maze solved with:");
                System.out.println("Width:"+ width +" Height: "+ height);

                mazePath = new Path[height][width];
                createVertices();
                findSolutions(mazePath[0][0]);
                solve(mazePath[height-1][width-1]);
                mazeFrame.revalidate();
                mazeFrame.repaint();

            }
        });

        mazeFrame.setVisible(true);

    }

    public static void main(String[] args){

        new Maze();
    }

    @Override
    public void actionPerformed(ActionEvent e) {}



    static class MazeSolution extends JPanel {
        private int [][] myArr;
        private int intBox;
        public MazeSolution(int [][] array, int intBox) {
            myArr = array;
            this.intBox = intBox;
            setPreferredSize(new Dimension((myArr[0].length + 1) * intBox,
                    (myArr.length + 1) * intBox));
        }
        public void paintComponent(Graphics g) {
            int startx = 5;
            int currenty = startx;
            g.setColor(Color.red);
            for(int i = 0; i< myArr.length; i++){
                int currentx = startx;
                for(int j = 0; j< myArr[i].length; j++){
                    if(solution[i][j])
                        g.fillRect(currentx, currenty, intBox, intBox);
                    currentx += intBox;
                }
                currenty+= intBox;
            }


            currenty = startx;
            for(int i = 0; i< myArr.length; i++){
                int currentx = startx;
                for (int j = 0; j < myArr[i].length ; j++){
                    currentx += intBox;
                    if((myArr[i][j]==1|| myArr[i][j]==3)&& j!= myArr[i].length-1)
                        g.drawLine(currentx,currenty,currentx,currenty+ intBox);
                }
                currenty+= intBox;
            }
            g.drawLine(intBox * myArr[0].length+5,startx, intBox * myArr[0].length+5, intBox * myArr.length+5- intBox);
            g.drawLine(startx+ intBox,startx, intBox * myArr[0].length+5,startx);
            g.drawLine(startx,startx,startx, intBox * myArr.length+5);
            g.drawLine(startx, intBox * myArr.length+5, intBox * myArr[0].length+5, intBox * myArr.length+5);
            currenty = startx;
            for(int i = 0; i< myArr.length; i++){
                int currentx = startx;
                currenty+= intBox;
                for (int j = 0; j < myArr[0].length ; j++){
                    if((myArr[i][j] == 2) ||(myArr[i][j] == 3))
                        g.drawLine(currentx,currenty,currentx+ intBox,currenty);
                    currentx += intBox;
                }

            }
        }

    }

    public static void buildMazeMatric(){

        myMaze = new int[height][width];
        index = new int[height][width];
        solution = new boolean[height][width];




        int temp = 0;
        for(int i = 0; i<height;i++){
            for(int j = 0; j < width; j++){
                index[i][j] = temp;
                myMaze[i][j] = 3;
                temp++;
            }
        }
        mazeSet = new DisjointSets(height*width);


        boolean done = true;
        while(done){

            int random = (int) (Math.random()*10000)%(height*width);
            //System.out.println("Random: " + random);
            int randomRowIndex = getRowIndex(random);
            int randomColIndex = getColIndex(random);
            while(getAdjacent(random)<0){
                random = (int) (Math.random()*10000)%(height*width);
            }
            int adjacent = getAdjacent(random);
            int adjRowIndex = getRowIndex(adjacent);
            int adjColIndex = getColIndex(adjacent);
            int minW = getColIndex(Math.min(random, adjacent));
            int minH = getRowIndex(Math.min(random, adjacent));
            if(!(mazeSet.find(random)==mazeSet.find(adjacent))){
                mazeSet.union(mazeSet.find(random), mazeSet.find(adjacent));
                if(randomRowIndex==adjRowIndex){
                    if(myMaze[randomRowIndex][minW]==3)
                        myMaze[randomRowIndex][minW]=2;
                    else
                        myMaze[randomRowIndex][minW]=0;
                }
                else if(randomColIndex==adjColIndex){
                    if(myMaze[minH][randomColIndex]==3)
                        myMaze[minH][randomColIndex]=1;
                    else
                        myMaze[minH][randomColIndex]=0;
                }
            }
            for(int i = 0;i<height*width;i++){
                if(mazeSet.testArr[i]==-1*(height*width)){
                    done = false;
                }
            }
        }
    }


    public static int getRowIndex(int cellIndex){
        for(int i = 0; i<index.length;i++){
            for(int j = 0; j<index[i].length;j++){
                if(index[i][j] == cellIndex)
                    return i;
            }
        }
        return -1;
    }

    public static int getColIndex(int cellIndex){
        for(int i = 0; i<index.length;i++){
            for(int j = 0; j<index[i].length;j++){
                if(index[i][j] == cellIndex)
                    return j;
            }
        }
        return -1;
    }

    public static int getAdjacent(int cell){
        int cellWidth = getColIndex(cell);
        int cellHeight = getRowIndex(cell);
        int posCount = 0;
        int[] temp = new int[4];
        for(int i = 0; i<temp.length;i++)
            temp[i] = -1;
        if(cellHeight!=0){
            if(myMaze[cellHeight-1][cellWidth]!=0 || myMaze[cellHeight-1][cellWidth]!=1){
                temp[posCount] = index[cellHeight-1][cellWidth];
                System.out.println("1 - " + posCount);
                posCount++;

            }
        }
        if(cellHeight!=index.length-1){
            if(myMaze[cellHeight][cellWidth]!=0 || myMaze[cellHeight][cellWidth]!=1){
                temp[posCount] = index[cellHeight+1][cellWidth];
                System.out.println("2 - " + posCount);
                posCount++;
            }
        }
        if(cellWidth!=0){
            if(myMaze[cellHeight][cellWidth-1]!=0 || myMaze[cellHeight][cellWidth]!=2){
                temp[posCount] = index[cellHeight][cellWidth-1];
                System.out.println("3 - " + posCount);
                posCount++;
            }
        }
        if(cellWidth!=index[0].length-1){
            if(myMaze[cellHeight][cellWidth]!=0 || myMaze[cellHeight][cellWidth]!=2){
                temp[posCount] = index[cellHeight][cellWidth+1];
                System.out.println("4 - " + posCount);
                posCount++;
            }
        }
        if(temp[0]<0 && temp[1]<0 && temp[2]<0 && temp[3]<0)
            return -1;
        int tempRand = (int) (Math.random()*10)%posCount;
        while(temp[tempRand]<0){
            tempRand = (int) (Math.random()*10)%posCount;
        }
        return temp[tempRand];
    }




    public static void createVertices(){
        for(int i = 0;i<height;i++){
            for(int j=0;j<width;j++){
                mazePath[i][j] = new Path();
                mazePath[i][j].index = index[i][j];
            }
        }
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(i!=0){
                    if(myMaze[i-1][j]==0 || myMaze[i-1][j]==1){
                        mazePath[i][j].makeAdj(mazePath[i-1][j]);
                    }
                }
                if(j!=0){
                    if(myMaze[i][j-1]==0 || myMaze[i][j-1]==2){
                        mazePath[i][j].makeAdj(mazePath[i][j-1]);
                    }
                }
                if(i!=(height-1)){
                    if(myMaze[i][j]==0 || myMaze[i][j]==1){
                        mazePath[i][j].makeAdj(mazePath[i+1][j]);
                    }
                }
                if(j!=(width-1)){
                    if(myMaze[i][j]==0 || myMaze[i][j]==2){
                        mazePath[i][j].makeAdj(mazePath[i][j+1]);
                    }
                }
            }
        }
    }

    public static void findSolutions(Path start){
        start.dist = 0.0;
        for(int i = 0; i< mazePath.length; i++){
            for(int j = 0; j< mazePath[i].length; j++){
                Path smallVar = smallUnknown();
                if(smallVar == null){
                    break;
                }
                smallVar.found = true;
                while(!smallVar.pathToGo.isEmpty()){
                    Path smallVar2 = smallVar.pathToGo.remove();
                    if(!smallVar2.found){
                        if((smallVar.dist+1)<smallVar2.dist){
                            smallVar2.dist = smallVar.dist+1;
                            smallVar2.path = smallVar;
                        }
                    }
                }
            }
        }
    }


    public static Path smallUnknown(){
        Double temp = Double.POSITIVE_INFINITY;
        Path t = new Path();
        for(int i = 0; i< mazePath.length; i++){
            for(int j = 0; j< mazePath[i].length; j++){
                if(!mazePath[i][j].found){
                    if(temp> mazePath[i][j].dist){
                        t = mazePath[i][j];
                        temp = t.dist;
                    }
                }
            }
        }
        if(temp == Double.POSITIVE_INFINITY){
            return null;
        }
        return t;
    }

    public static void solve(Path v){
        if(v.path!=null){
            solve(v.path);
        }
        solution[getRowIndex(v.index)][getColIndex(v.index)] = true;
    }

}