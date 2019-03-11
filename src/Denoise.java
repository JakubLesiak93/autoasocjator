import javax.swing.*;

class Denoise extends JButton {

    private ToRippleNet net;

    Denoise(ToRippleNet rN){
        setText("Odszum");
        this.net = rN;
        addActionListener(e-> denoise());
    }

    private void denoise(){
    }
}