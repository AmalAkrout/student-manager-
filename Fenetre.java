import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.NumberFormat;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Fenetre extends JFrame {
private JTable tab = new JTable();
private DefaultTableModel model;
private JScrollPane scroll = new JScrollPane(tab);
private JButton ajouter = new JButton(" Ajouter ");
private JButton modifier = new JButton(" Modifier");
private JButton supprimer = new JButton(" Supprimer");


private JFormattedTextField Nom1 = new JFormattedTextField();
private JFormattedTextField Prenom1 = new JFormattedTextField();


private JComboBox<String> comb = new JComboBox<>();
private JLabel Nom = new JLabel(" Nom : ");
private JLabel Prenom = new JLabel(" Prenom : ");
private JLabel Classe = new JLabel(" Classe : ");
private JPanel container = new JPanel();
BD m = new BD();
JOptionPane boite;
String nome, prenome, ide, classe;
private boolean ajoutEnCours = false;

public Fenetre() {
    comb.addItem("6°A");
    comb.addItem("5°B");
    comb.addItem("7°A");
    comb.addItem("8°B");
    comb.addItem("9°B");
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setTitle("Table de base de donner");
    this.setLocation(600, 100);
    this.setSize(600, 300);
    scroll.setPreferredSize(new Dimension(200, 240));
    Box box1 = Box.createHorizontalBox();
    box1.add(scroll);

    Box box2 = Box.createHorizontalBox();
    box2.add(Nom);
    
    box2.add(Nom1);
    box2.add(Prenom);
    
    box2.add(Prenom1);
    box2.add(Classe);
    box2.add(comb);

    Box box3 = Box.createHorizontalBox();
    box3.add(ajouter);
    box3.add(modifier);
    box3.add(supprimer);
    Box boxFinal = Box.createVerticalBox();
    boxFinal.add(box1);
    boxFinal.add(box2);
    boxFinal.add(box3);

    container.setLayout(new BorderLayout());
    container.add(boxFinal, BorderLayout.CENTER);
    this.setContentPane(container);
    initialisation();
    
    Nom1.addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            // Ne rien faire lorsque le champ gagne le focus
        }
    });

    Prenom1.addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            // Ne rien faire lorsque le champ gagne le focus
        }
    });

   ajouter.addActionListener(e -> {
        ajoutEnCours = true;

        // Récupérer le nom, le prénom et la classe saisis
        String nom = Nom1.getText();
        String prenom = Prenom1.getText();
        String classe = (String) comb.getSelectedItem();

        // Vérifier si les champs ne sont pas vides
        if (!nom.isEmpty() && !prenom.isEmpty()) {
            // Ajouter une nouvelle ligne au tableau avec les données saisies
            model.addRow(new Object[]{model.getRowCount() + 1, nom, prenom, classe});

            // Effacer les champs après l'ajout
            Nom1.setText("");
            Prenom1.setText("");
            comb.setSelectedIndex(-1);
        } else {
            // Afficher un message d'erreur si les champs nom et prénom sont vides
            JOptionPane.showMessageDialog(container, "Veuillez remplir les champs nom et prénom.", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }

        ajoutEnCours = false;
    });

   modifier.addActionListener(e -> {
        int selectedRow = tab.getSelectedRow();
        if (selectedRow != -1) {
            // Récupérer le nom, le prénom et la classe saisis
            String nom = Nom1.getText();
            String prenom = Prenom1.getText();
            String classe = (String) comb.getSelectedItem();

            // Vérifier si les champs ne sont pas vides
            if (!nom.isEmpty() && !prenom.isEmpty()) {
                // Mettre à jour les données de la ligne sélectionnée dans le modèle de tableau
                model.setValueAt(nom, selectedRow, 1);
                model.setValueAt(prenom, selectedRow, 2);
                model.setValueAt(classe, selectedRow, 3);

                // Effacer les champs après la modification
                Nom1.setText("");
                Prenom1.setText("");
                comb.setSelectedIndex(-1);
            } else {
                // Afficher un message d'erreur si les champs nom et prénom sont vides
                JOptionPane.showMessageDialog(container, "Veuillez remplir les champs nom et prénom.", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Afficher un message d'erreur si aucune ligne n'est sélectionnée
            JOptionPane.showMessageDialog(container, "Veuillez sélectionner une ligne à modifier.", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    });


    supprimer.addActionListener(e -> {
        int selectedRow = tab.getSelectedRow();
        if (selectedRow != -1) {
            // Vérifier si les champs nom et prénom sont vides
            if (!Nom1.getText().isEmpty() && !Prenom1.getText().isEmpty()) {
                // Supprimer la ligne sélectionnée du modèle de tableau
                model.removeRow(selectedRow);
            } else {
                // Afficher un message d'erreur si les champs nom et prénom sont vides
                JOptionPane.showMessageDialog(container, "Veuillez remplir les champs nom et prénom.", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Afficher un message si aucune ligne n'est sélectionnée
            JOptionPane.showMessageDialog(container, "Veuillez sélectionner une ligne à supprimer.", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    });
    tab.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting() && !ajoutEnCours) {
                int selectedRow = tab.getSelectedRow();
                if (selectedRow != -1) {
                    String nom = tab.getValueAt(selectedRow, 1).toString();
                    String prenom = tab.getValueAt(selectedRow, 2).toString();
                    String classe = tab.getValueAt(selectedRow, 3).toString();

                    Nom1.setText(nom);
                    Prenom1.setText(prenom);
                    comb.setSelectedItem(classe);
                }
            }
        }
    });

    this.setVisible(true);
}

private void initialisation() {
    model = new DefaultTableModel();
    model.addColumn("ELV_ID");
    model.addColumn("ELV_NOM");
    model.addColumn("ELV_PRENOM");
    model.addColumn("CLC_NOM");
    model.addRow(new Object[]{"1", "AMAL", "AKROUT", "MPII"});
    model.addRow(new Object[]{"2", "CHAYMA", "CHAOUICHI", "MPII"});
    model.addRow(new Object[]{"3", "PITON", "THOMAS", "6°B"});
    model.addRow(new Object[]{"4", "COUQUILLE", "OLIVIER", "6°B"});
    model.addRow(new Object[]{"5", "SALMON", "DYLAN", "6°B"});
   
    tab.setModel(model);
    
    
}

public static void main(String[] args) {
    new Fenetre();
}}