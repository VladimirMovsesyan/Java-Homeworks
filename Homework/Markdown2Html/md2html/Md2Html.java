package md2html;

import java.io.*;

public class Md2Html {
    public static void main(String[] args) throws IOException {

        try (BufferedReader in = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(args[0]),
                "utf8"
            )
        );) {
            try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    "utf8"
                )
            );) {

                String nextLine = new String();
                StringBuilder sb = new StringBuilder();

                while (nextLine != null) {
                    nextLine = in.readLine();
                    if (nextLine == null || nextLine.equals("")) {
                        if (sb.length() > 0) {
                            Part part = new Part(sb);
                            out.write(part.modify());
                            sb = new StringBuilder();
                        }
                    } else {
                        sb.append(nextLine);
                        sb.append(System.lineSeparator());
                    }
                }

            }
        }
    }
}
