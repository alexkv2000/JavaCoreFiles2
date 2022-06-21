import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress gameOne = new GameProgress(20, 25, 2, 20.1);
        GameProgress gameTwo = new GameProgress(89, 50, 7, 98.5);
        GameProgress gameThree = new GameProgress(100, 800, 15, 333.33);
        String defPath = "D:";
        saveGame(defPath  + "//Games//savegames//game1.dat", gameOne);
        saveGame(defPath  + "//Games//savegames//game2.dat", gameTwo);
        saveGame(defPath  + "//Games//savegames//game3.dat", gameThree);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(defPath  + "//Games//savegames//game1.dat");
        arrayList.add(defPath  + "//Games//savegames//game2.dat");
        arrayList.add(defPath  + "//Games//savegames//game3.dat");
        zipFiles(defPath  + "//Games//savegames//zip.zip", arrayList);
        File game1Dat = new File(defPath  + "//Games//savegames//game1.dat");
        File game2Dat = new File(defPath  + "//Games//savegames//game2.dat");
        File game3Dat = new File(defPath  + "//Games//savegames//game3.dat");
        if (game1Dat.delete()) System.out.println("Файл \"game1.dat\" удален");
        if (game2Dat.delete()) System.out.println("Файл \"game2.dat\" удален");
        if (game3Dat.delete()) System.out.println("Файл \"game3.dat\" удален");
    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
