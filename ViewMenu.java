package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ViewMenu extends JPopupMenu {
    JMenuItem m_flower;
    public ViewMenu(MainForm FileForm) {
        m_flower = new JMenuItem("Цветы");
        m_flower.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileForm.ShowFlowerDialog();
            }
        });
        add(m_flower);
    }
}
