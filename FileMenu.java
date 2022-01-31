package UI;

import Flower.Flower;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.util.Vector;

public class FileMenu extends JPopupMenu {
    //-------------------------------------------------
    JMenuItem m_create;
    JMenuItem m_open;
    JMenuItem m_save;
    //-------------------------------------------------
    public FileMenu(FileForm fileForm) {
        m_create = new JMenuItem("Создать");
        m_create.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileForm.CreateNewFile();
            }
        });
        add(m_create);
        //-
        m_open = new JMenuItem("Открыть...");
        m_open.addActionListener(
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fileForm.OpenFile();
                    }
                });
        add(m_open);
        //-
        m_save = new JMenuItem("Сохранить");
        m_save.addActionListener(
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       fileForm.SaveCurrentFile();
                    }
                });
        add(m_save);
    }
}
