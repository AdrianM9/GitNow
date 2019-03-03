package utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.FileWriter;

/**
 * Class used for printing the output of the operations.
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class OutputWriter {
    private String filename;
    private BufferedWriter bufferedWriter;

    /**
     * Output writer constructor.
     *
     * @param filename the file name
     */
    public OutputWriter(String filename) {
        this.filename = filename;
        bufferedWriter = new BufferedWriter(getWriterForFile(filename));
    }

    /**
     * Writes the content.
     *
     * @param content the content
     */
    public void write(String content) {
        try {
            this.bufferedWriter.write(content);
            this.bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Writer getWriterForFile(String file) {
        if (file == null || file.isEmpty()) {
            return new OutputStreamWriter(System.out);
        } else {
            try {
                return new FileWriter(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
