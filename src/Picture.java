import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Picture extends JPanel {
    private int columnCount = 50;
    private int rowCount = 50;
    private int size = 2500;
    private List<Rectangle> cells;
    private List<Point> selectedCells;
    private int[] answerArray;
    private ArrayList<RecordHolder> recordHolderArray;

    Picture() {
        cells = new ArrayList<>(columnCount * rowCount);
        selectedCells = new ArrayList<>(columnCount * rowCount);
        setPreferredSize(new Dimension(520, 520));
    }

    void setAnswerArray(int[] answerArray) {
        this.answerArray = answerArray;
    }

    void setRecordHolderArray(ArrayList<RecordHolder> recordHolderArray) {
        this.recordHolderArray = recordHolderArray;
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

        g2d.dispose();
    }

    void clear(){
        selectedCells.clear();
        answerArray = new int[size];
        repaint();
    }

    void doJob(){
        int x;
        int y;
        for (int i = 0 ; i < size; i++){
            if ( check(i , answerArray, recordHolderArray) == 1 ) {
                y = i / 50;
                x = i % 50;
                selectedCells.add(new Point(x, y));
            }
        }
        repaint();
    }

    private int check(int num, int[] answerArray, ArrayList<RecordHolder> recordHolderArray){
        double sum = 0.0;
        RecordHolder rH = recordHolderArray.get(num);
        double[] weights = rH.getWeight();

        for (int i = 0 ; i < size; i++){
            sum += ((double) answerArray[i]) * weights[i];
        }

        sum -= rH.getTheta();

        return (int) Math.signum(sum);
    }
}
