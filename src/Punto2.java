import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Punto2{
    public static void main(String[] args) throws IOException {
        String fileA = "src\\a.mat";
        String fileB = "src\\b.mat";
        String fileC = "src\\c.mat";


        FileInputStream readerA = new FileInputStream(fileA);
        DataInputStream matA = new DataInputStream(readerA);

        FileInputStream readerB = new FileInputStream(fileB);
        DataInputStream matB = new DataInputStream(readerB);

        int numRowsA = matA.readByte();
        int numColsA = matA.readByte();
        int numRowsB = matB.readByte();
        int numColsB = matB.readByte();

        if (numColsA != numRowsB) {
            System.out.println("Multiplicacion no posible");
            return;
        }

        double[][] matrixA = new double[numRowsA][numColsA];
        double[][] matrixB = new double[numRowsB][numColsB];


        for (int i = 0; i < numRowsA; i++) {
            for (int j = 0; j < numColsA; j++) {
                matrixA[i][j]= matA.readDouble();
            }
        }
        readerA.close();
        matA.close();

        for (int i = 0; i < numRowsB; i++) {
            for (int j = 0; j < numColsB; j++) {
                matrixB[i][j] = matB.readDouble();
            }
        }

        readerB.close();
        matB.close();

        double[][] resultMatrix = new double[numRowsA][numColsB];
        for (int i = 0; i < numRowsA; i++) {
            for (int j = 0; j < numColsB; j++) {
                double sum = 0;
                for (int k = 0; k < numColsA; k++) {
                    sum += matrixA[i][k] * matrixB[k][j];
                }
                resultMatrix[i][j] = sum;
            }
        }

        FileOutputStream fileOutputStreamC = new FileOutputStream(fileC);

        fileOutputStreamC.write((byte) numRowsA);
        fileOutputStreamC.write((byte) numColsB);

        for (int i = 0; i < numRowsA; i++) {
            for (int j = 0; j < numColsB; j++) {
                long bits = Double.doubleToLongBits(resultMatrix[i][j]);
                for (int k = 0; k < 8; k++) {
                    fileOutputStreamC.write((int) (bits >> (8 * k)) & 0xFF);
                }
            }
        }

        fileOutputStreamC.close();

    }
}