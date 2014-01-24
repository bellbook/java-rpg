package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CSV {

    public static List<String[]> read(Reader r) throws IOException {
        if (r == null)
            throw new NullPointerException("r must not be null");

        List<String[]> table = new ArrayList<String[]>();
        BufferedReader br = new BufferedReader(r);
        String line;
        try {
            while ((line = br.readLine()) != null) {
                table.add(line.split(","));
            }
        } finally {
            br.close();
        }
        return table;
    }

    public static void write(Writer w, List<String[]> table) throws IOException {
        if (w == null)
            throw new NullPointerException("w must not be null");
        if (table == null)
            throw new NullPointerException("table must not be null");

        BufferedWriter bw = new BufferedWriter(w);
        try {
            for (String[] row : table) {
                StringBuilder sb = new StringBuilder();
                for (String col : row) {
                    sb.append(col);
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append("\n");
                bw.write(sb.toString());
            }
        } finally {
            bw.close();
        }
    }

}
