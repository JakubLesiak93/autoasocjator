import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Interface extends Frame {

    public static void main(String[] args) {
        new Interface();
    }

    private Interface() {
        super("Autoasocjator");

        GridBagConstraints gBC = new GridBagConstraints();
        setSize(new Dimension(1300,600));
        setLayout(new GridBagLayout());
        setResizable(false);

        ToRippleNet net = new ToRippleNet();
        Ripple ripple = new Ripple(net);
        Denoise denoise = new Denoise(net);
        Picture pi = new Picture();
        Previous previous = new Previous(net, pi);
        Next next = new Next(net, pi);

        Recognize re = new Recognize(net, pi);

        gBC.fill = GridBagConstraints.HORIZONTAL;
        gBC.gridx = 0;
        gBC.gridy = 0;
        add(net, gBC);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        gBC.fill = GridBagConstraints.HORIZONTAL;
        gBC.gridx = 0;
        gBC.gridy = 0;
        panel.add(ripple, gBC);

        gBC.fill = GridBagConstraints.HORIZONTAL;
        gBC.gridx = 0;
        gBC.gridy = 1;
        panel.add(denoise, gBC);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        gBC.fill = GridBagConstraints.HORIZONTAL;
        gBC.gridx = 0;
        gBC.gridy = 0;
        panel1.add(previous, gBC);

        gBC.fill = GridBagConstraints.HORIZONTAL;
        gBC.gridx = 1;
        gBC.gridy = 0;
        panel1.add(next, gBC);

        gBC.fill = GridBagConstraints.HORIZONTAL;
        gBC.gridx = 0;
        gBC.gridy = 3;
        panel.add(panel1, gBC);

        gBC.fill = GridBagConstraints.HORIZONTAL;
        gBC.gridx = 0;
        gBC.gridy = 4;
        panel.add(re, gBC);

        add(panel);

        gBC.fill = GridBagConstraints.HORIZONTAL;
        gBC.gridx = 3;
        gBC.gridy = 0;
        add(pi, gBC);

        pack();

        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }
}
