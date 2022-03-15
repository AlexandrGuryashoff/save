package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        String path1 = "E://Games//savegames//game1.dat";
        String path2 = "E://Games//savegames//game2.dat";
        String path3 = "E://Games//savegames//game3.dat";

	  GameProgress game1 = new GameProgress(12, 100, 4, 102.8);
      GameProgress game2 = new GameProgress(25, 400, 7, 302.64);
      GameProgress game3 = new GameProgress(90, 700, 10, 848.59);
      saveGame(path1, game1);
      saveGame(path2, game2);
      saveGame(path3, game3);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(path1);
        arrayList.add(path2);
        arrayList.add(path3);
        zipFiles("E://Games//savegames//zip.zip", arrayList);
        File game1Dat = new File(path1);
        File game2Dat = new File(path2);
        File game3Dat = new File(path3);
        if (game1Dat.delete()) System.out.println("Файл\"game1.dat\"удален");
        if (game2Dat.delete()) System.out.println("Файл\"game2.dat\"удален");
        if (game3Dat.delete()) System.out.println("Файл\"game3.dat\"удален");
    }

    private static void saveGame(String path, GameProgress game){
        try(FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(game);
            } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    private static void zipFiles(String path, ArrayList<String> arrayList){
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))){
            for (String arr : arrayList){
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
