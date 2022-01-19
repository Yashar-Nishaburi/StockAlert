import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SettingGUI extends JFrame implements ActionListener
{
    private JPanel panelRefresh;
    private JPanel panelSort;
    private JPanel panelSearch;
    private JLabel labelRef;
    private JLabel labelSort;
    private JLabel labelSearch;
    private JLabel temp1;
    private JLabel temp2;
    private JLabel spacer;
    private JButton buttonRefresh;
    private JRadioButton sortDef;
    private JRadioButton sortPrice;
    private JRadioButton sortName;
    private ButtonGroup sortGroup;
    private JTextField searchField;

    public SettingGUI ()
    {
        panelRefresh = new JPanel();
        panelSort = new JPanel();
        panelSearch = new JPanel();
        searchField = new JTextField();
        labelRef = new JLabel("Force Refresh",JLabel.CENTER);
        labelSort = new JLabel("Sort By:",JLabel.CENTER);
        labelSearch = new JLabel("Search For:",JLabel.CENTER);
        spacer = new JLabel("================",JLabel.CENTER);
        temp1 = new JLabel(" ");
        temp2 = new JLabel(" ");
        buttonRefresh = new JButton("Refresh");
        buttonRefresh.setFocusable(false);
        buttonRefresh.addActionListener(this);

        searchField.setPreferredSize(new Dimension(200,20));
        searchField.addActionListener(this);

        sortDef = new JRadioButton("Default");
        sortDef.setFocusable(false);
        sortPrice = new JRadioButton("Price");
        sortPrice.setFocusable(false);
        sortName = new JRadioButton("Name");
        sortName.setFocusable(false);

        sortGroup = new ButtonGroup();
        sortGroup.add(sortDef);
        sortGroup.add(sortPrice);
        sortGroup.add(sortName);

        sortDef.addActionListener(this);
        sortName.addActionListener(this);
        sortPrice.addActionListener(this);



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("StockAlert - Settings");
        this.setSize(300,250);
        this.setLayout(null);
        this.setResizable(false);
        this.add(panelSearch);
        this.add(panelRefresh);
        this.add(panelSort);

        //******************************************************************
        //Initializing gui panel for search section
        panelSearch.setBounds(40,20,210,50);
        panelSearch.setLayout(new FlowLayout());
        panelSearch.add(labelSearch);
        panelSearch.add(searchField);
        //******************************************************************
        //Initializing gui panel for refresh section
        panelRefresh.setBounds(40,130,210,90);
        panelRefresh.setLayout(new GridLayout(4,1));
        panelRefresh.add(spacer);
        panelRefresh.add(temp1);
        panelRefresh.add(labelRef);
        panelRefresh.add(buttonRefresh);
        //******************************************************************
        //Initializing gui panel for radio button menu
        panelSort.setBounds(40,80,210,50);
        //panelSort.setBackground(Color.BLACK);
        panelSort.setLayout(new GridLayout(2,3));
        panelSort.add(temp1);
        panelSort.add(labelSort);
        panelSort.add(temp2);
        panelSort.add(sortDef);
        panelSort.add(sortPrice);
        panelSort.add(sortName);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==searchField)
        {
            try
            {
                Config.write("SearchFor",searchField.getText()); // Write search field to config on enter
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            searchField.setText("");
        }
        if(e.getSource()==sortDef)
        {
            try
            {
                Config.write("SortBy","Default");
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }else if(e.getSource()==sortPrice)
        {
            try
            {
                Config.write("SortBy","Price");
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }else if(e.getSource()==sortName)
        {
            try
            {
                Config.write("SortBy","Name");
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        if (e.getSource()==buttonRefresh)
        {
            Main.inputSupplied = true;//Exits RepeatingTask function on Button click
        }
    }
}
/*
This section could also use quite a bit of improvement. The implementation of the gui is dirty (Null layout manager) and doesn't work well across OS/Res.
The final actionPerformed function is once again bad practice. If/else spam :)
 */