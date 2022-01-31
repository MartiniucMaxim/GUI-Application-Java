package Flower.UI;

import Database.Database;
import Flower.Flower;
import Flower.FlowerColor;
import Flower.FlowerType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import static javax.swing.JOptionPane.showMessageDialog;

public class FlowerForm extends JDialog {
    private JPanel Content;
    private JTextField tfName;
    private JList<FlowerColor> listColors;
    private JRadioButton rbDecorative;
    private JRadioButton rbHome;
    private JSpinner sCount;
    private JComboBox<Double> cbPrice;
    private JButton bOrder;
    //-
    private final ActionListener decorativeListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switchRB(false);
            rbHome.setSelected(!rbDecorative.isSelected());
            SaveCurrentObject();
            switchRB(true);
        }
    };
    private final ActionListener homeListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switchRB(false);
            rbDecorative.setSelected(!rbHome.isSelected());
            SaveCurrentObject();
            switchRB(true);
        }
    };

    //-------------------------
    private void createUIComponents() {
        // TODO: place custom component creation code here
        listColors = new JList<>(FlowerColor.values());
        cbPrice = new JComboBox<>();
        for (double i = 0; i < 50; i += 0.5)
            cbPrice.addItem(i);
    }


    //временное выключение событий на тип цветка.
    public void switchRB(boolean on) {
        if (on) {
            rbHome.addActionListener(homeListener);
            rbDecorative.addActionListener(decorativeListener);
        } else {
            rbHome.removeActionListener(homeListener);
            rbDecorative.removeActionListener(decorativeListener);
        }
    }

    public FlowerForm(JFrame parent_in) {
        switchRB(true);
        sCount.setModel(new SpinnerNumberModel(1, 1, 69, 1));

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        //-
        setModal(true); //свойство диалоговости.
        this.setContentPane(Content);
        pack();
        setMinimumSize(new Dimension(400, 400));
        setLocationRelativeTo(parent_in);
        //-
        tfName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveCurrentObject();
            }
        });
        //-
        //тут событие выбора слушается через модель.
        listColors.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                SaveCurrentObject();
            }
        });
        //-
        sCount.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                SaveCurrentObject();
            }
        });
        cbPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveCurrentObject();
            }
        });
        bOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //сдвиг текущего объекта. при дальнейших событиях создастся новый.
                currentFlower = null;
            }
        });
        //
        db.Reset();
    }
    public void ShowDialog(){
        setVisible(true);
    }
    //------------------------------------------------------------------------>>>

    Flower currentFlower = null; //текущий объект.
    Database db = new Database();

    public boolean validateFields() {
        Vector<String> errorsMessages = new Vector<>();
        if (tfName.getText().isEmpty())
            errorsMessages.add("Имя не может быть пустым.");
        if (listColors.isSelectionEmpty())
            errorsMessages.add("Цвет не выбран");
        if (cbPrice.getSelectedItem() == null)
            errorsMessages.add("Цена не выбрана");
        //----------------------------------------------------------------------------------
        String errorsText = String.join("\n", errorsMessages);
        if (errorsMessages.isEmpty()) {
            return true;
        } else {
            showMessageDialog(null, errorsText, "", 0);
            return false;
        }

    }

    public void SaveCurrentObject() {
        System.out.print("saving current object ");
        if (validateFields()) {
            if (currentFlower == null) {
                currentFlower = new Flower();
                db.Insert(currentFlower);
            }
            System.out.println(currentFlower.name);
            currentFlower.refreshFields(
                    tfName.getText(),
                    listColors.getSelectedValue(),
                    (rbDecorative.isSelected() ?
                            FlowerType.Decorative :
                            FlowerType.HomeFlower),
                    (int) sCount.getValue(),
                    (double) cbPrice.getSelectedItem()
            );
            db.WriteAllToDisk();
        } else System.out.println("not valid fields");
    }
}
