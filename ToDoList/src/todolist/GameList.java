package todolist;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY_OR_MOVE;
import static javax.swing.TransferHandler.MOVE;


public class GameList extends JPanel
{
    DefaultListModel model, submodel1, submodel2, submodel3;
    JList List, Work, Bugs, Done;
    JPanel GList, WList, BList, DList, TDPanel, btnPanel;
    JScrollPane listView, workView, bugsView, doneView, tdView, btnViewA, btnViewB, btnViewC, btnViewD;
    JTextField TD;
    JButton Add, Del, Clr, Abt;
    
    public GameList()
    {   
        add (TheTD());
        add (TheList());
        add (TheWork());
        add (TheBugs());
        add (TheDone());
        
        Add.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent me) 
            {
                if(TD.getText().equals(""))
                {
                    JFrame ER = new JFrame();

                    JOptionPane.showMessageDialog(ER,
                    "Put something in TODO","ERROR",
                    JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    String[] WTypes = { "2D/3D Design", "GUI", "Audio", "Scripting", "Text", "Others" };
                    String Res = (String) JOptionPane.showInputDialog(null, "What kind of work?",
                    "Work Types", JOptionPane.QUESTION_MESSAGE, null, 
                    WTypes,
                    WTypes[0]);
                    
                    switch (Res) 
                    {
                        case "2D/3D Design":
                            model.addElement(TD.getText()+" (2D/3D Design)");
                            TD.setText("");
                            break;
                        case "GUI":
                            model.addElement(TD.getText()+" (GUI)");
                            TD.setText("");
                            break;
                        case "Audio":
                            model.addElement(TD.getText()+" (Audio)");
                            TD.setText("");
                            break;
                        case "Scripting":
                            model.addElement(TD.getText()+" (Scripting)");
                            TD.setText("");
                            break;
                        case "Text":
                            model.addElement(TD.getText()+" (Text)");
                            TD.setText("");
                            break;
                        case "Others":
                            model.addElement(TD.getText()+" (Others)");
                            TD.setText("");
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        Del.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent me) 
            {
                model.remove(0);
            }
        });
        Clr.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent me) 
            {
                model.clear();
                submodel1.clear();
                submodel2.clear();
                submodel3.clear();
            }
        });
        Abt.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent me) 
            {

                JFrame ER = new JFrame();

                JOptionPane.showMessageDialog(ER,
                "Created By: Daniel"
                + "\nIts a simple todo list for game development."
                + "\nIt has no save function but still useful.",
                "Game Dev List",
                JOptionPane.INFORMATION_MESSAGE);
                
                
            }
        });
    }
    // The Creation of a list
    private JPanel TheTD()
    {
        TD = new JTextField();
                
        tdView = new JScrollPane(TD);
        tdView.setPreferredSize(new Dimension(220, 40));
        
        TD.setDragEnabled(false);

        TDPanel = new JPanel(new BorderLayout());
        TDPanel.add(tdView, BorderLayout.PAGE_START);
        TDPanel.setBorder(BorderFactory.createTitledBorder("TODO"));
        Add = new JButton("Add");
        Del = new JButton("Delete");
        Clr = new JButton("Clear");
        Abt = new JButton("About");

        btnPanel = new JPanel();
        btnPanel.add(btnViewA = new JScrollPane(Add));
        btnPanel.add(btnViewB = new JScrollPane(Del));
        btnPanel.add(btnViewC = new JScrollPane(Clr));
        btnPanel.add(btnViewD = new JScrollPane(Abt));

        TDPanel.add(btnPanel, BorderLayout.LINE_END);
        
        return TDPanel;
    }

    // The Lists
    private JPanel TheList()
    {
        model = new DefaultListModel();
        List = new JList(model);

        List.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listView = new JScrollPane(List);
        listView.setPreferredSize(new Dimension(200, 100));
        
        List.setDragEnabled(true);
        List.setTransferHandler(new ListTransferHandler());

        GList = new JPanel(new BorderLayout());
        GList.add(listView, BorderLayout.CENTER);
        GList.setBorder(BorderFactory.createTitledBorder("List"));
        
        model.clear();
        return GList;
    }
    
    private JPanel TheWork()
    {
        submodel1 = new DefaultListModel();
        Work = new JList(submodel1);

        Work.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        workView = new JScrollPane(Work);
        workView.setPreferredSize(new Dimension(200, 100));
        
        Work.setDragEnabled(true);
        Work.setTransferHandler(new ListTransferHandler());

        WList = new JPanel(new BorderLayout());
        WList.add(workView, BorderLayout.CENTER);
        WList.setBorder(BorderFactory.createTitledBorder("Working"));
        
        submodel1.clear();
        return WList;
    }
    
    private JPanel TheBugs()
    {
        submodel2 = new DefaultListModel();
        Bugs = new JList(submodel2);

        Bugs.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        bugsView = new JScrollPane(Bugs);
        bugsView.setPreferredSize(new Dimension(200, 100));
        
        Bugs.setDragEnabled(true);
        Bugs.setTransferHandler(new ListTransferHandler());
        
        BList = new JPanel(new BorderLayout());
        BList.add(bugsView, BorderLayout.CENTER);
        BList.setBorder(BorderFactory.createTitledBorder("Bugs/Issues"));

        submodel2.clear();
        return BList;
    }
    
    private JPanel TheDone()
    {
        submodel3 = new DefaultListModel();
        Done = new JList(submodel3);
        
        Done.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        doneView = new JScrollPane(Done);
        doneView.setPreferredSize(new Dimension(200, 100));
        
        Done.setDragEnabled(true);
        Done.setTransferHandler(new ListTransferHandler());
        
        DList = new JPanel(new BorderLayout());
        DList.add(doneView, BorderLayout.CENTER);
        DList.setBorder(BorderFactory.createTitledBorder("Done"));
        
        submodel3.clear();
        return DList;
    }
    
    // The Drag n Drop Code
    abstract class StringTransferHandler extends TransferHandler 
    {
        protected abstract String exportString(JComponent c);
        protected abstract void importString(JComponent c, String str);
        protected abstract void cleanup(JComponent c, boolean remove);
    
        @Override
        protected Transferable createTransferable(JComponent c) 
        {
            return new StringSelection(exportString(c));
        }

        @Override
        public int getSourceActions(JComponent c) 
        {
            return COPY_OR_MOVE;
        }

        @Override
        public boolean importData(JComponent c, Transferable t) 
        {
        if (canImport(c, t.getTransferDataFlavors())) 
        {
            try 
            {
                String str = (String)t.getTransferData(DataFlavor.stringFlavor);
                importString(c, str);
                return true;
            } catch (UnsupportedFlavorException | IOException ufe) {
            }
        }

        return false;
        }

        @Override
        protected void exportDone(JComponent c, Transferable data, int action) 
        {
            cleanup(c, action == MOVE);
        }

        @Override
        public boolean canImport(JComponent c, DataFlavor[] flavors) 
        {
            for (DataFlavor flavor : flavors) 
            {
                if (DataFlavor.stringFlavor.equals(flavor)) 
                {
                    return true;
                }
            }
            return false;
        }
    }
    class ListTransferHandler extends StringTransferHandler 
    {
        private int[] indices = null;
        private int addIndex = -1;
        private int addCount = 0;

        @Override
        protected String exportString(JComponent c) 
        {
            JList list = (JList)c;
            indices = list.getSelectedIndices();
            Object[] values = list.getSelectedValues();

            StringBuilder buff = new StringBuilder();

            for (int i = 0; i < values.length; i++) 
            {
                Object val = values[i];
                buff.append(val == null ? "" : val.toString());
                if (i != values.length - 1) 
                {
                    buff.append("\n");
                }
            }

            return buff.toString();
        }

        @Override
        protected void importString(JComponent c, String str) 
        {
            JList target = (JList)c;
            DefaultListModel listModel = (DefaultListModel)target.getModel();
            int index = target.getSelectedIndex();

            if (indices != null && index >= indices[0] - 1 &&
                index <= indices[indices.length - 1]) 
            {
                indices = null;
                return;
            }

            int max = listModel.getSize();
            if (index < 0) 
            {
                index = max;
            } 
            else 
            {
                index++;
            if (index > max) 
            {
                index = max;
            }
            }

            addIndex = index;
            String[] values = str.split("\n");
            addCount = values.length;
            for (String value : values) 
            {
                listModel.add(index++, value);
            }
        }

        @Override
        protected void cleanup(JComponent c, boolean remove) 
        {
            if (remove && indices != null) 
            {
            JList source = (JList)c;
            DefaultListModel model  = (DefaultListModel)source.getModel();

            if (addCount > 0) 
            {
                for (int i = 0; i < indices.length; i++) 
                {
                    if (indices[i] > addIndex) 
                    {
                        indices[i] += addCount;
                    }
            }
            }
            for (int i = indices.length - 1; i >= 0; i--) 
            {
                model.remove(indices[i]);
            }
            }
            indices = null;
            addCount = 0;
            addIndex = -1;
        }
    }            
        
    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame frame = new JFrame("Game Dev List");
       
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100,100);
        GameList GL = new GameList();
        frame.setContentPane(GL);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) 
    {
        javax.swing.SwingUtilities.invokeLater(GameList::createAndShowGUI);
    }
}
