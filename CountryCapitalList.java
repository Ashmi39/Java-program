package Swings;
import javax.swing.*;
import javax.swing.event.*;
import java.util.HashMap;

public class CountryCapitalList extends JFrame {

    JList<String> countryList;
    HashMap<String, String> capitalMap;

    public CountryCapitalList() {
        setTitle("Country Capitals");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Countries
        String countries[] = {
            "USA", "India", "Vietnam", "Canada",
            "Denmark", "France", "Great Britain",
            "Japan", "Africa", "Greenland", "Singapore"
        };

        // Create JList
        countryList = new JList<>(countries);

        // Capitals mapping
        capitalMap = new HashMap<>();
        capitalMap.put("USA", "Washington D.C.");
        capitalMap.put("India", "New Delhi");
        capitalMap.put("Vietnam", "Hanoi");
        capitalMap.put("Canada", "Ottawa");
        capitalMap.put("Denmark", "Copenhagen");
        capitalMap.put("France", "Paris");
        capitalMap.put("Great Britain", "London");
        capitalMap.put("Japan", "Tokyo");
        capitalMap.put("Africa", "Addis Ababa");
        capitalMap.put("Greenland", "Nuuk");
        capitalMap.put("Singapore", "Singapore");

        // ✅ Fixed event (no duplicate printing)
        countryList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {   // 🔥 important fix
                    String selected = countryList.getSelectedValue();
                    if (selected != null) {
                        System.out.println("Capital of " + selected + " is: " + capitalMap.get(selected));
                    }
                }
            }
        });

        add(new JScrollPane(countryList));
        setVisible(true);
    }

    public static void main(String[] args) {
        new CountryCapitalList();
    }
}