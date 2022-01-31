package Database;

import Flower.Flower;

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.Vector;

public class Database {
    File file = new File("db.txt");
    //список объектов. сохраняет порядок, чтобы не нужно было читать файл посимвольно каждый раз.
    Vector<Flower> flowers = new Vector<>();

    public void WriteAllToDisk() {
        try {
            Vector<String> records = new Vector<>();
            for (Flower flower : flowers)
                records.add(flower.toString());
            //-
            //перезапись всего списка в файл.
            FileWriter writer = new FileWriter(file, false);
            writer.write(String.join("\n", records));
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void Reset(){
        try {
            //перезапись всего списка в файл.
            FileWriter writer = new FileWriter(file, false);
            writer.write("");
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Insert(Flower flower) {
        flowers.add(flower);
    }
}
