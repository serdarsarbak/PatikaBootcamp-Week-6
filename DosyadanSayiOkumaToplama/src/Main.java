import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        // bir metin dosyasındaki (.txt) tüm sayıları okuyan ve bu sayıların toplamını döndüren ve ekrana yazdıran bir
        // Java programı yazınız. Dosyada sadece satır başına bir tam sayı bulunacaktır.

        // Text dosyası oluşturma:
        File file = new File ("numbers.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        // Text dosyasına sayıları yazdırma:
        String[] data = {"5", "10", "20", "12", "33"};
        FileWriter fWrite = new FileWriter(file);
        BufferedWriter bWrite = new BufferedWriter(fWrite);
        for (String i : data) {
            bWrite.write(i);
            bWrite.newLine();
        }
        bWrite.close();

        //Text dosyasındaki sayıları okuma ve toplamı bulma:
        FileReader fReader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(fReader);
        String line;
        int total = 0;
        while ((line = bReader.readLine()) != null) {
            int num =Integer.parseInt(line);
            total += num;
        }

        System.out.println("Text dosyasındaki sayıların toplamı: " + total);
    }

}
