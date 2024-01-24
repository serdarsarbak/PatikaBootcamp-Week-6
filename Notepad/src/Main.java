import java.io.*;
import java.lang.reflect.Executable;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Basit bir Notepad uygulaması oluşturun. Bu uygulama kullanıcıdan metin girişi almalı ve bu metni bir dosyaya
        // kaydetmelidir. Kullanıcı programı tekrar başlattığında, en son kaydedilen metni ekrana yazdırmalıdır.

        readText();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Notepad'e bir metin giriniz: ");
        String metin = scanner.nextLine();
        File file = new File("notepad.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(metin);
            printWriter.println();
            printWriter.close();
            readText();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Notepad.txt'deki verilerin okunması
    public static void readText() {
        File file = new File("notepad.txt");
        if (file.exists()) {
            try {
                System.out.println("###########################################");
                System.out.println();
                System.out.println("------------------NOTEPAD------------------");
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String text = bufferedReader.readLine();
                while ((text!=null)) {
                    System.out.println(text);
                    text= bufferedReader.readLine();
                }
                System.out.println("###########################################");
                System.out.println();
                bufferedReader.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
