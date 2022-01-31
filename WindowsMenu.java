package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class WindowsMenu extends JPopupMenu {
    JMenuItem m_window;
    JMenuItem m_closeAll;
    public WindowsMenu(MainForm mainForm) {
        m_window = new JMenuItem("Новое окно");
        m_window.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainForm.AddFileWindow();
            }
        });
        add(m_window);
        m_closeAll = new JMenuItem("Закрыть все окна");
        m_closeAll.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainForm.CloseAllFilesWindows();
            }
        });
        add(m_closeAll);
    }
}
