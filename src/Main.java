import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        File divinaComedia = new File("src/divina_comedia_sp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(divinaComedia));

        int[] counter = new int[9];
        while (reader.readLine() != null) {
            String[] lecturaSeparada = (reader.readLine()).split(" ");

            for (String s : lecturaSeparada) {
                if (s.length() >= 2 && s.length() <= 10) {

                    switch (s.length()) {
                        case 2 -> counter[0]++;
                        case 3 -> counter[1]++;
                        case 4 -> counter[2]++;
                        case 5 -> counter[3]++;
                        case 6 -> counter[4]++;
                        case 7 -> counter[5]++;
                        case 8 -> counter[6]++;
                        case 9 -> counter[7]++;
                        case 10 -> counter[8]++;
                    }

                }

            }
        }
        reader.close();

        String[] options = {"2","3","4","5","6","7","8","9","10"};

        for (int i = 0; i < options.length; i++) {
            System.out.printf("%2s |", options[i]);
            int scaledFrequency = (int) (counter[i] / 100.0);

            for (int j = 0; j < scaledFrequency; j++) {
                System.out.print("*");
            }

            System.out.print(" " + counter[i]);

            System.out.println();
        }

    }
}