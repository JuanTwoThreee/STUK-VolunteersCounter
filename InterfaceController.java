package stukVolonteer;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.util.Map;
import java.util.Vector;
import java.util.ArrayList;

public class InterfaceController {
    VolonteerCounter counter;
    
    public InterfaceController(VolonteerCounter c){
        this.counter = c;
        SwingUtilities.invokeLater(() -> createWindow("STUK VOLONTÄRER", 500, 500));
    }

    private void createWindow(String title, int width, int height){
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        ArrayList<Map.Entry<Person,Integer>> list = new ArrayList<Map.Entry<Person,Integer>>(counter.getList());
        SortedListModel<Map.Entry<Person,Integer>> listModel = new SortedListModel<Map.Entry<Person,Integer>>(list);
        JList<Map.Entry<Person,Integer>> listView = new JList<Map.Entry<Person,Integer>>(listModel);
        JScrollPane listJScrollPane = new JScrollPane(listView);

        JTextField tbxAmountOfPoints = new JTextField("1");
        JTextField tbxName = new JTextField();
        tbxAmountOfPoints.setPreferredSize(new Dimension(150,24));
        tbxName.setPreferredSize(new Dimension(150, 24));
        
        JButton btnInput = new JButton("Make entry");
        btnInput.addActionListener( event -> {
            String inputName = tbxName.getText();
            int points = Integer.parseInt(tbxAmountOfPoints.getText());

            Map.Entry<Person, Integer> chosenEntry = counter.entryWhereNameIs(inputName);
            if(chosenEntry!=null) {
                counter.addEntry(chosenEntry.getKey(), points);
                //System.out.println(chosenEntry.getKey().toString());
            } else {
                Person newPerson = new Person(inputName);
                counter.addEntry(newPerson, points);
                //System.out.print(newPerson.toString());
            }
            //om det finns person med namnet ska man välja dem istället
            // Update the list model with the latest data
            listModel.updateList(counter.getList());
            System.err.println(listModel.getList());
            
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(btnInput);
        inputPanel.add(tbxName);
        inputPanel.add(tbxAmountOfPoints);

        pane.add(listJScrollPane, BorderLayout.NORTH);
        pane.add(inputPanel, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(width, height)); //suggest a size for the frame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
