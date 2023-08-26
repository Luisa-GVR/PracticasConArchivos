import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Punto2{
    public static void main(String[] args) throws IOException {
        String fileA = "src\\a.mat";
        String fileB = "src\\b.mat";
        String fileC = "src\\c.mat";


        FileInputStream fileInputStreamA = new FileInputStream(fileA);
        byte[] bytesA = fileInputStreamA.readAllBytes();
        fileInputStreamA.close();

        ByteBuffer bufferA = ByteBuffer.wrap(bytesA);

        int numRowsA = bufferA.get();
        int numColsA = bufferA.get();

        double[][] matrixA = new double[numRowsA][numColsA];


        for (int i = 0; i < numRowsA; i++) {
            for (int j = 0; j < numColsA; j++) {
                long bits = 0;
                for (int k = 0; k < 8; k++) {
                    bits |= (bufferA.get() & 0xFFL) << (8 * k);
                }
                matrixA[i][j] = Double.longBitsToDouble(bits);
            }
        }



        FileInputStream fileInputStreamB = new FileInputStream(fileB);
        byte[] bytesB = fileInputStreamB.readAllBytes();
        fileInputStreamB.close();

        ByteBuffer bufferB = ByteBuffer.wrap(bytesB);

        int numRowsB = bufferB.get();
        int numColsB = bufferB.get();

        double[][] matrixB = new double[numRowsB][numColsB];

        for (int i = 0; i < numRowsB; i++) {
            for (int j = 0; j < numColsB; j++) {
                long bits = 0;
                for (int k = 0; k < 8; k++) {
                    bits |= (bufferB.get() & 0xFFL) << (8 * k);
                }
                matrixB[i][j] = Double.longBitsToDouble(bits);
            }
        }

        if (numColsA != numRowsB) {
            System.out.println("Multiplicacion no posible");
            return;
        }

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