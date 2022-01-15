import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingGUI extends JFrame implements ActionListener
{
    //private JPanel panelRefresh;
    private JLabel labelRef;
    private JLabel labelSort;
    private JButton buttonRefresh;
    private JRadioButton sortDef;
    private JRadioButton sortPrice;
    private JRadioButton sortName;

    public SettingGUI ()
    {
        //panelRefresh = new JPanel();
        labelRef = new JLabel("Click refresh to instantly apply settings", SwingConstants.CENTER);
        labelSort = new JLabel("Sort By:", SwingConstants.CENTER);
        buttonRefresh = new JButton("Refresh");
        buttonRefresh.addActionListener(this);

        sortDef = new JRadioButton("Default");
        sortPrice = new JRadioButton("Price");
        sortName = new JRadioButton("Name");

        //this.setResizable(false);
        //this.add(panelRefresh);
        this.add(labelSort);
        this.add(sortDef);
        this.add(sortPrice);
        this.add(sortName);
        //this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("StockAlert - Settings");
        this.setSize(250,600);
        this.setLayout(new GridLayout(9,1));
        this.setVisible(true);
        this.add(buttonRefresh);
        this.add(labelRef);
        //panelRefresh.setBackground(Color.BLACK);
        //panelRefresh.setBounds(10,470,260,100);
        //panelRefresh.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        //panelRefresh.setLayout(new GridLayout(0, 1));
        //panelRefresh.add(buttonRefresh);
        //panelRefresh.add(label);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==buttonRefresh)
        {
            Main.inputSupplied = true;
        }
    }
}
