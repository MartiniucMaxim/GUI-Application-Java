package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileForm extends JFrame {
    private JPanel Content;
    private JButton bFile;
    private JScrollPane editorScroll;
    private JTextArea editor;
    //-
    private FileMenu file_menu;
    private EditorMenu editor_menu;
    public File currentFile = new File("Безымянный");
    JFileChooser fileChooser = new JFileChooser();
    public FileForm(JFrame parent){
        file_menu = new FileMenu(this);
        editor_menu = new EditorMenu(editor);
        bFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                file_menu.show(bFile, 0, bFile.getSize().height);
            }
        });
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setDialogTitle("Сохранение файла");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //-
        this.setContentPane(Content);
        pack();
        setMinimumSize(new Dimension(800, 550));
        setLocationByPlatform(true); //это и дает каскадность.
        setVisible(true);
        //-
        ShowFileName();
    }

    public void ShowFileName() {
        this.setTitle(currentFile.getAbsolutePath());
    }
    //-------------------------------->>
    public void CreateNewFile() {
        currentFile = new File("Безымянный");
        ShowFileName();
    }

    public void OpenFile() {
        try {
            fileChooser.setDialogTitle("Открытие файла");
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
                String text = "";
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(currentFile),
                                StandardCharsets.UTF_8));
                int c;
                while ((c = reader.read()) != -1)
                    text += (char) c;
                reader.close();
                editor.setText(text);
                ShowFileName();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void SaveCurrentFile() {
        try {
            fileChooser.setDialogTitle("Сохранение файла");
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
                FileWriter writer = new FileWriter(currentFile, false);
                writer.write(editor.getText());
                writer.close();
                ShowFileName();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
