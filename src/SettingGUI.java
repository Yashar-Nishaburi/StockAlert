import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class SettingGUI extends JFrame implements ActionListener
{
    //Debugpanel
    private JPanel panelDebug;
    private JCheckBox checkDebug;
    //TableRef
    public JLabel labelRecent;
    public JPanel panelRef2;
    public static DefaultTableModel modelRef;
    public JTable tableRef;
    //TableAll
    public static DefaultTableModel modelAll;
    public JTable tableAll;
    private JPanel panelCurrentSettings;
    private JLabel labelCurrentSearch;
    private JLabel labelCurrentSort;
    public JPanel panelItems;
    //Search
    private JPanel panelSearch;
    private JLabel labelSearch;
    private JLabel spacer;
    //Logs
    private JPanel panelLogo;
    public JTextArea labelStatus;
    public JPanel panelStatus;
    private JTextArea labelLogo;
    //Refresh
    private JPanel panelRefresh;
    private JLabel labelRef;
    private JButton buttonRefresh;
    //Sorting
    private JLabel temp1;
    private JLabel temp2;
    private JLabel labelSort;
    private JPanel panelSort;
    private JRadioButton sortDef;
    private JRadioButton sortPrice;
    private JRadioButton sortName;
    private ButtonGroup sortGroup;
    private JTextField searchField;

    public SettingGUI ()
    {
        //Debug instance
        panelDebug = new JPanel();
        checkDebug = new JCheckBox("Enable Debug/Browse mode - Disables data scraping");
        //Refresh table instance
        labelRecent = new JLabel("Recently Restocked");
        panelRef2 = new JPanel();
        modelRef = new DefaultTableModel();
        tableRef = new JTable(modelRef);
        //Item table instance
        modelAll = new DefaultTableModel();
        tableAll = new JTable(modelAll);
        //Current settings instance
        panelCurrentSettings = new JPanel();
        labelCurrentSearch = new JLabel();
        labelCurrentSort = new JLabel();
        panelStatus = new JPanel();
        panelItems = new JPanel();
        panelRefresh = new JPanel();
        panelSort = new JPanel();
        panelSearch = new JPanel();
        panelLogo = new JPanel();
        labelLogo = new JTextArea();
        searchField = new JTextField();

        labelRef = new JLabel("Force Refresh",JLabel.CENTER);
        labelSort = new JLabel("Sort By:",JLabel.CENTER);
        labelSearch = new JLabel("Search For:",JLabel.CENTER);
        spacer = new JLabel("================",JLabel.CENTER);
        temp1 = new JLabel(" ");
        temp2 = new JLabel(" ");
        buttonRefresh = new JButton("Refresh");
        buttonRefresh.setFocusable(false);
        buttonRefresh.addActionListener(e ->
        {
            Main.inputSupplied = true;
            modelAll.setRowCount(0);
        });

        searchField.setPreferredSize(new Dimension(200,20));
        searchField.addActionListener(e ->
        {
            try
            {
                Config.write("SearchFor",searchField.getText()); // Write search field to config on enter
                labelCurrentSearch.setText("Current Sort settings: " + searchField.getText());
            } catch (IOException ex)
            {
                System.out.println(">> Failed to access cfg file");
                ex.printStackTrace();
            }
            searchField.setText("");
        });

        sortDef = new JRadioButton("Default");
        sortDef.setFocusable(false);
        sortDef.setHorizontalAlignment(SwingConstants.CENTER);
        sortPrice = new JRadioButton("Price");
        sortPrice.setFocusable(false);
        sortPrice.setHorizontalAlignment(SwingConstants.CENTER);
        sortName = new JRadioButton("Name");
        sortName.setFocusable(false);
        sortName.setHorizontalAlignment(SwingConstants.CENTER);

        sortGroup = new ButtonGroup();
        sortGroup.add(sortDef);
        sortGroup.add(sortPrice);
        sortGroup.add(sortName);

        sortDef.addActionListener(this);
        sortName.addActionListener(this);
        sortPrice.addActionListener(this);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("StockAlert - Settings");
        this.setSize(1275,590);
        //this.getContentPane().setBackground(Color.GRAY);
        System.out.println(this.getInsets());
        this.setLayout(null);
        this.setResizable(false);
        this.add(panelSearch);
        this.add(panelRefresh);
        this.add(panelSort);
        this.add(panelLogo);
        this.add(panelStatus);
        this.add(panelItems);
        this.add(panelCurrentSettings);
        this.add(panelRef2);
        this.add(panelDebug);

        checkDebug.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    Main.debugMode = true;
                } else {
                    Main.debugMode = false;
                }
            }
        });

        panelDebug.setBounds(10,500,540,40);
        panelDebug.setLayout(new FlowLayout());
        panelDebug.setBorder(BorderFactory.createLineBorder(Color.black));
        checkDebug.setFocusable(false);
        panelDebug.add(checkDebug);
        //panelDebug.setBackground(Color.BLACK);

        panelRef2.setBounds(560,320,685,220);
        //panelRef2.setBackground(Color.BLACK);
        panelRef2.setLayout(new FlowLayout());
        modelRef.addColumn("Date & Time");
        modelRef.addColumn("Name");
        modelRef.addColumn("Price");
        modelRef.addColumn("Stock");
        modelRef.addColumn("Link");
        tableRef.getColumnModel().getColumn(0).setPreferredWidth(160);
        tableRef.getColumnModel().getColumn(1).setPreferredWidth(320);
        tableRef.getColumnModel().getColumn(2).setPreferredWidth(50);
        tableRef.getColumnModel().getColumn(3).setPreferredWidth(50);
        tableRef.getColumnModel().getColumn(4).setPreferredWidth(100);
        JScrollPane scrollPaneTableRef = new JScrollPane(tableRef);
        scrollPaneTableRef.setPreferredSize(new Dimension(680, 200));
        scrollPaneTableRef.getViewport().setBackground(Color.BLACK);
        panelRef2.add(labelRecent);
        //panelRef2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelRef2.add(scrollPaneTableRef);


        panelCurrentSettings.setBounds(560,10,685,20);
        //panelCurrentSettings.setBackground(Color.BLACK);
        panelCurrentSettings.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelCurrentSettings.setLayout(new GridLayout(1,2));
        try
        {
            labelCurrentSearch.setText("Current Search settings:" + Config.read("SearchFor"));
            labelCurrentSort.setText("Current Sort settings:" + Config.read("SortBy"));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        panelCurrentSettings.add(labelCurrentSearch);
        panelCurrentSettings.add(labelCurrentSort);

        modelAll.addColumn("Name");
        modelAll.addColumn("Price");
        modelAll.addColumn("Stock");
        modelAll.addColumn("Link");
        tableAll.getColumnModel().getColumn(0).setPreferredWidth(480);
        tableAll.getColumnModel().getColumn(1).setPreferredWidth(50);
        tableAll.getColumnModel().getColumn(2).setPreferredWidth(50);
        tableAll.getColumnModel().getColumn(3).setPreferredWidth(100);

        panelItems.setBounds(560,30,685,270);
        //panelItems.setBackground(Color.BLACK);
        panelItems.setLayout(new FlowLayout());
        //panelItems.add(tableAll);
        JScrollPane scrollPaneTable = new JScrollPane(tableAll);
        scrollPaneTable.setPreferredSize(new Dimension(680, 265));
        scrollPaneTable.getViewport().setBackground(Color.BLACK);
        panelItems.add(scrollPaneTable);

        labelLogo.setFont(new Font(Font.MONOSPACED,Font.BOLD, 16));
        labelLogo.setForeground(Color.GREEN);
        labelLogo.setText(
                "   _____ __             __   ___    __          __\n" +
                        "  / ___// /_____  _____/ /__/   |  / /__  _____/ /_\n" +
                        "  \\__ \\/ __/ __ \\/ ___/ //_/ /| | / / _ \\/ ___/ __/\n" +
                        " ___/ / /_/ /_/ / /__/ ,< / ___ |/ /  __/ /  / /_\n" +
                        "/____/\\__/\\____/\\___/_/|_/_/  |_/_/\\___/_/   \\__/"
        );
        labelLogo.setEditable(false);
        labelLogo.setOpaque(false);

        panelLogo.setBounds(10,10,540,140);
        panelLogo.setBackground(Color.BLACK);
        panelLogo.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,Color.DARK_GRAY));
        panelLogo.setLayout(new FlowLayout());
        panelLogo.add(labelLogo);


        labelStatus = new JTextArea();
        labelStatus.setSize(500,70);
        labelStatus.setWrapStyleWord(true);
        DefaultCaret caret = (DefaultCaret)labelStatus.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane scrollPane = new JScrollPane(labelStatus);
        scrollPane.setPreferredSize(new Dimension(540, 170));
        scrollPane.getViewport().setBackground(Color.BLACK);

        labelStatus.append(">>Starting...\n");
        labelStatus.setForeground(Color.GREEN);
        labelStatus.setEditable(false);
        labelStatus.setOpaque(false);
        panelStatus.setBounds(10,320,540,180);
        //panelStatus.setBackground(Color.BLACK);
        panelStatus.add(scrollPane);
        //******************************************************************
        //Initializing gui panel for search section
        panelSearch.setBounds(82,170,400,30);
        panelSearch.setLayout(new FlowLayout());
        panelSearch.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelSearch.add(labelSearch);
        panelSearch.add(searchField);
        //******************************************************************
        //Initializing gui panel for radio button menu
        panelSort.setBounds(82,200,400,50);
        //panelSort.setBackground(Color.BLACK);
        panelSort.setLayout(new GridLayout(2,3));
        panelSort.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelSort.add(temp1);
        panelSort.add(labelSort);
        panelSort.add(temp2);
        panelSort.add(sortDef);
        panelSort.add(sortPrice);
        panelSort.add(sortName);
        //******************************************************************
        //Initializing gui panel for refresh section
        panelRefresh.setBounds(82,250,400,50);
        panelRefresh.setLayout(new GridLayout(2,1));
        panelRefresh.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //panelRefresh.add(spacer);
        //panelRefresh.add(temp1);
        panelRefresh.add(labelRef);
        panelRefresh.add(buttonRefresh);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==sortDef)
        {
            try
            {
                Config.write("SortBy","Default");
                labelCurrentSort.setText("Current Sort settings: Default");
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }else if(e.getSource()==sortPrice)
        {
            try
            {
                Config.write("SortBy","Price");
                labelCurrentSort.setText("Current Sort settings: Price");
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }else if(e.getSource()==sortName)
        {
            try
            {
                Config.write("SortBy", "Name");
                labelCurrentSort.setText("Current Sort settings: Name");
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
/*
This section could also use quite a bit of improvement. The implementation of the gui is dirty (Null layout manager) and doesn't work well across OS/Res.
The final actionPerformed function is once again bad practice. If/else spam :)
 */