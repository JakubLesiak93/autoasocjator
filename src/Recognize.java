import javax.swing.*;

class Recognize extends JButton {

    Recognize(ToRippleNet net, Picture picture){
        super("Rozpoznaj");
        addActionListener(e->{
            picture.clear();
            picture.setAnswerArray(net.getPic());
            picture.setRecordHolderArray(net.getRecordHolderArray());
            picture.doJob();
        });
    }
}
