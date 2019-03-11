import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

class Ripple extends JButton {
    private ToRippleNet net;

    Ripple(ToRippleNet rN){
        setText("Zaszum");
        this.net = rN;
        addActionListener(e -> ripple());
    }

    private void ripple(){
        Random random = new Random();
        Point point;

        List<Point> selectedCells = net.getSelectedCells();
        List<Point> rippleCells = net.getRippleCells();
        for (int i =0 ; i < 20; i++){
            point = new Point(random.nextInt(50), random.nextInt(50));

            if ( !rippleCells.contains(point) && !selectedCells.contains(point)){
                net.addRippleCells(point);
            }

            if ( selectedCells.contains(point) ){
                net.removeFromSelectedPoint(point);
            }
        }
    }
}