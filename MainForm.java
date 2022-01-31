package UI;

import Flower.UI.FlowerForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Vector;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import static javax.swing.JOptionPane.showMessageDialog;

public class MainForm extends JFrame {
    private JPanel Content;
    private FlowerForm flowerForm;
    private JScrollPane editorScroll;
    private JTextArea editor;
    private ViewMenu view_menu;
    private WindowsMenu windowsMenu;
    private JButton bView;
    private JButton bHelp;
    private JLabel lName;
    private JLabel lAssemblyDate;
    private JLabel lLocation;
    private JLabel lSize;
    private JButton bWindows;

    //-------------------------------->>
    public MainForm() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //-
        flowerForm = new FlowerForm(this);
        view_menu = new ViewMenu(this);
        windowsMenu = new WindowsMenu(this);
        //-
        this.setContentPane(Content);
        pack();
        setMinimumSize(new Dimension(800, 550));
        setLocationRelativeTo(null);
        setVisible(true);
        bWindows.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                windowsMenu.show(bWindows, 0, bWindows.getSize().height);
            }
        });
        bView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                view_menu.show(bView, 0, bView.getSize().height);
            }
        });
        bHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageDialog(null, "Это программа о цветах. (todo дописать текст)", "", 1);
            }
        });
        ShowStatus();
    }

    private static final String appName="Lab.jar";
    public void ShowStatus() {
        File appFile = Paths.get(System.getProperty("user.dir"), appName).toFile();
        lName.setText(appFile.getName());
        lLocation.setText(appFile.getParent());
        lSize.setText(String.format("%.2f Mb", getFileSizeMegaBytes(appFile)));
        lAssemblyDate.setText(getClassBuildInfo().toString());
    }
    public void ShowFlowerDialog(){
       flowerForm.ShowDialog();
    }
    public Vector<FileForm> filesWindows = new Vector<>();
    public void AddFileWindow(){
        filesWindows.add(new FileForm(this));
    }
    public void CloseAllFilesWindows(){
        for (FileForm fileForm: filesWindows)
            fileForm.dispose();
        filesWindows.clear();
    }
    //--------------------------------->>
    private static Date getClassBuildInfo() {
        Date d = null;
        Class<?> currentClass = new Object() {}.getClass().getEnclosingClass();
        URL resource = currentClass.getResource(currentClass.getSimpleName() + ".class");
        if (resource != null) {
            if (resource.getProtocol().equals("file")) {
                try {
                    d = new Date(new File(resource.toURI()).lastModified());
                } catch (URISyntaxException ignored) {
                }
            } else if (resource.getProtocol().equals("jar")) {
                String path = resource.getPath();

                d = new Date(new File(path.substring(5, path.indexOf("!"))).lastModified());
            } else if (resource.getProtocol().equals("zip")) {
                String path = resource.getPath();
                File jarFileOnDisk = new File(path.substring(0, path.indexOf("!")));
                try (JarFile jf = new JarFile(jarFileOnDisk)) {
                    ZipEntry ze = jf.getEntry(path.substring(path.indexOf("!") + 2));
                    long zeTimeLong = ze.getTime();
                    Date zeTimeDate = new Date(zeTimeLong);
                    d = zeTimeDate;
                } catch (IOException | RuntimeException ignored) {
                }
            }
        }
        return d;
    }
    public static double getFileSizeMegaBytes(File file) {
        return file.length() / (double) (1024 * 1024);
    }
}
