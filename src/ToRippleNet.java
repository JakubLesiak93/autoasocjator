import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToRippleNet extends JPanel{
    private int pictureNum = 0;
    private int columnCount = 50;
    private int rowCount = 50;
    private int size = 2500;
    private List<Rectangle> cells;
    private List<Point> selectedCells;
    private List<Point> rippleCells;
    private int[] pic;
    private ArrayList<RecordHolder> recordHolderArray;
    private Random random;

    ToRippleNet() {
        cells = new ArrayList<>(columnCount * rowCount);
        selectedCells = new ArrayList<>(columnCount * rowCount);
        rippleCells = new ArrayList<>(columnCount * rowCount);
        pic = new int[size];
        random = new Random();
        setPreferredSize(new Dimension(520, 520));
        setPicture();
        recordHolderArray = startLearn();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        int width = 500;
        int height = 500;

        int cellWidth = width / columnCount;
        int cellHeight = height / rowCount;

        int xOffset = (width - (columnCount * cellWidth)) / 2;
        int yOffset = (height - (rowCount * cellHeight)) / 2;

        if (cells.isEmpty()) {
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                    Rectangle cell = new Rectangle(
                            xOffset + (col * cellWidth),
                            yOffset + (row * cellHeight),
                            cellWidth,
                            cellHeight);
                    cells.add(cell);
                }
            }
        }

        for (Rectangle cell : cells) {
            g2d.setColor(Color.WHITE);
            g2d.fill(cell);
            g2d.setColor(Color.BLACK);
            g2d.draw(cell);
        }

        if (!selectedCells.isEmpty()) {
            for (Point point : selectedCells) {
                int index = point.x + (point.y * columnCount);
                Rectangle cell = cells.get(index);
                g2d.setColor(Color.BLACK);
                g2d.fill(cell);
            }
        }

        if (!rippleCells.isEmpty()) {
            for (Point point : rippleCells) {
                int index = point.x + (point.y * columnCount);
                Rectangle cell = cells.get(index);
                g2d.setColor(Color.BLACK);
                g2d.fill(cell);
            }
        }

        g2d.dispose();
    }

    private void setPicture() {
        Im im = new Im(pictureNum);
        pic = im.getPic();

        selectedCells.clear();
        rippleCells.clear();
        int x;
        int y;

        for (int i = 0 ; i < pic.length; i++){
            y = i / 50;
            x = i % 50;
            if ( pic[i] == 1) {
                selectedCells.add(new Point(x, y));
            }
        }
        repaint();
    }

    void addRippleCells(Point point){
        rippleCells.add(point);
        repaint();
    }

    void removeAllRandomPoints(){
    }

    List<Point> getRippleCells() {
        return rippleCells;
    }

    void removeFromSelectedPoint(Point point){
        selectedCells.remove(point);
        repaint();
    }

    List<Point> getSelectedCells() {
        return selectedCells;
    }

    void addAllRandomPoints(Point point){
    }

    void addPictureNum(){
        pictureNum++;
        if ( pictureNum == 8 ){
            pictureNum = 0;
        }
        setPicture();
    }

    void subtractPictureNum(){
        pictureNum--;
        if ( pictureNum == -1 ){
            pictureNum = 7;
        }
        setPicture();
    }

    private double[] weightRandom() {
        double[] weights = new double[size];
        for (int i = 0; i < size; i++) {
            weights[i] = -1 + 2 * random.nextDouble();
        }
        return weights;
    }

    private double thetaRandom(){
        return random.nextDouble();
    }

    private int[][] getExamples(){
        int[][] examples = new int[8][size];
        Im im;
        for (int e = 0 ; e < 8 ; e ++){
            im = new Im(e);
            examples[e] = im.getPic();
        }
        return examples;
    }

    private int[] getExample(int[][] examples){
        return examples[random.nextInt(8)];
    }

    private ArrayList<RecordHolder> startLearn(){
        ArrayList<RecordHolder> arrayList = new ArrayList<>();
        RecordHolder recordHolder;
        int[][] exampleArray = getExamples();
        for (int i = 0 ; i < size ; i++){
            recordHolder = learn(i, exampleArray);
            arrayList.add(recordHolder);
        }
        return arrayList;
    }

    private int tJ(int numberToLearn){
        if ( numberToLearn == 1){
            return 1;
        } else {
            return -1;
        }
    }

    private int o(int[] example, double theta, double[] weights){
        double sum = 0.0;
        for ( int i = 0 ; i < size ; i++){
            sum += example[i] * weights[i];
        }

        if ( sum < theta ){
            return -1;
        } else {
            return 1;
        }
    }

    private RecordHolder learn(int percToLearn, int[][] exampleArray) {
        double[] weights = weightRandom();
        double theta = thetaRandom();
        int lifeTime = 0;
        RecordHolder recordHolder = new RecordHolder(theta, weights, lifeTime);

        int[] example;
        for (int iter = 0; iter < 150; iter++) {
            example = getExample(exampleArray);
            int ERR = tJ(example[percToLearn]) - o(example, theta, weights);
            if ( ERR == 0 ){
                lifeTime++;
                if (recordHolder.getLifeTime() < lifeTime ){
                    recordHolder.setTheta(theta);
                    recordHolder.setWeight(weights);
                    recordHolder.setLifeTime(lifeTime);
                }
            } else {
                for (int i = 0; i < size; i++){
                    weights[i] += 0.5 * ERR * example[i];
                }
                theta -=  0.5 * ERR;
                lifeTime = 0;
            }
        }
        return recordHolder;
    }

    int[] getPic() {
        int[] pic = new int[size];
        for (int i = 0; i < size; i++){
            pic[i] = -1;
        }
        for (Point p : rippleCells){
            int po = (int) p.getX() + (int) p.getY() * columnCount;
            pic[po] = 1;
        }
        for(Point p : selectedCells){
            int po = (int) p.getX() + (int) p.getY() * columnCount;
            pic[po] = 1;
        }
        return pic;
    }

    ArrayList<RecordHolder> getRecordHolderArray() {
        return recordHolderArray;
    }
}
