import javax.swing.*;

class Next extends JButton {
    Next(ToRippleNet rippleNet, Picture picture){
        setText(">");
        addActionListener(e-> {
            rippleNet.addPictureNum();
            picture.clear();
        });
    }
}
