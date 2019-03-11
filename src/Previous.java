import javax.swing.*;

class Previous extends JButton {
    Previous(ToRippleNet rippleNet, Picture picture){
        setText("<");
        addActionListener(e-> {
            rippleNet.subtractPictureNum();
            picture.clear();
        });
    }

}
