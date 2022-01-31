package UI;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;

public class EditorMenu extends JPopupMenu {
    protected JTextComponent editor;
    protected String selectedText = null;
    //-------------------------------------------------
    JMenuItem m_cut;
    JMenuItem m_copy;
    JMenuItem m_paste;

    //-------------------------------------------------
    public EditorMenu(JTextComponent editor_in) {
        editor = editor_in;
        m_cut = new JMenuItem("Вырезать");
        m_cut.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.cut();
            }
        });
        add(m_cut);
        m_copy = new JMenuItem("Копировать");
        m_copy.addActionListener(
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        editor.copy();
                    }
                });
        add(m_copy);
        m_paste = new JMenuItem("Вставить");
        m_paste.addActionListener(
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        editor.paste();
                    }
                });
        add(m_paste);
        //-
        editor.setComponentPopupMenu(this);
    }
}
