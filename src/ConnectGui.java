//Retired
/*
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConnectGui extends JFrame
{
    //public JPanel panelLogo;
    public JPanel panelStatus;
    //public JTextArea labelLogo;
    public JTextArea labelStatus;

    public ConnectGui()
    {
        panelLogo = new JPanel();
        panelStatus = new JPanel();
        labelStatus = new JTextArea();
        labelStatus.setSize(500,70);
        labelStatus.setWrapStyleWord(true);
        DefaultCaret caret = (DefaultCaret)labelStatus.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane scrollPane = new JScrollPane(labelStatus);
        scrollPane.setPreferredSize(new Dimension(540, 170));
        scrollPane.getViewport().setBackground(Color.BLACK);

        labelLogo = new JTextArea();
        labelLogo.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        labelLogo.setText(
                "   _____ __             __   ___    __          __\n" +
                        "  / ___// /_____  _____/ /__/   |  / /__  _____/ /_\n" +
                        "  \\__ \\/ __/ __ \\/ ___/ //_/ /| | / / _ \\/ ___/ __/\n" +
                        " ___/ / /_/ /_/ / /__/ ,< / ___ |/ /  __/ /  / /_\n" +
                        "/____/\\__/\\____/\\___/_/|_/_/  |_/_/\\___/_/   \\__/"
        );
        labelLogo.setEditable(false);
        labelLogo.setOpaque(false);

        labelStatus.append(">>Starting...\n");
        labelStatus.setForeground(Color.GREEN);
        labelStatus.setEditable(false);
        labelStatus.setOpaque(false);

        this.setTitle("StockAlert - Connecting");
        this.setSize(640, 400);
        this.setLayout(null);
        this.setResizable(false);
        this.add(panelLogo);
        this.add(panelStatus);

        panelLogo.setBounds(40,20,540,140);
        panelLogo.setBorder(BorderFactory.createLineBorder(Color.black));
        panelLogo.setLayout(new FlowLayout());
        panelLogo.add(labelLogo);

        panelStatus.setBounds(40,160,540,180);
        panelStatus.setBackground(Color.BLACK);
        panelStatus.add(scrollPane);

        //this.pack();
        this.setVisible(true);
    }

}
*/