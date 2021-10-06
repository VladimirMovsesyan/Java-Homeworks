import java.io.*;

public class MyScanner {
    private BufferedReader reader;
    private char[] buff;
    private int curSize, curPos;
    private StringBuilder sb;
    final private int buffSize = 128;

    MyScanner(InputStream input) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(input));
        this.buff = new char[buffSize];
        this.curPos = 0;
        this.curSize = -2;
        this.sb = new StringBuilder();
        read();
    }

    MyScanner(String s) throws IOException {
        this.reader = new BufferedReader(new StringReader(s));
        this.buff = new char[buffSize];
        this.curPos = 0;
        this.curSize = -2;
        this.sb = new StringBuilder();
        read();
    }

    MyScanner(String s, String codec) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(s), codec));
        this.buff = new char[buffSize];
        this.curPos = 0;
        this.curSize = -2;
        this.sb = new StringBuilder();
        read();
    }

    public boolean read() throws IOException {
        if (this.curPos < this.curSize)
            return true;
        this.curSize = this.reader.read(buff, 0, buffSize);
        this.curPos = 0;
        return (this.curSize > -1);
    }

    public boolean goodForInt(char ch) {
        return (ch == '-' || Character.isDigit(ch));
    }

    public boolean goodForWord(char ch) {
        return ((Character.getType(ch) == Character.DASH_PUNCTUATION || ch == '\'' || Character.isLetter(ch)));
    }

    public void skipWhiteSpaces() throws IOException {
        while (read() && !goodForInt(this.buff[this.curPos])) {
            this.curPos++;
        }
    }

    public void skipNotAllowedSymbols() throws IOException {
        while (read() && !goodForWord(this.buff[this.curPos])) {
            this.curPos++;
        }
    }

    public boolean hasNextInt() throws IOException {
        skipWhiteSpaces();
        while ((curPos < curSize || read()) && goodForInt(buff[curPos])) {
            sb.append(buff[curPos++]);
        }
        return (sb.length() > 0);
    }

    public int nextInt() {
        String temp = sb.toString();
        sb = new StringBuilder();
        return Integer.parseInt(temp);
    }

    public boolean hasNextLine() throws IOException {
        while (read() && !(Character.toString(buff[curPos]).equals(System.getProperty("line.separator")))) {
            sb.append(buff[curPos++]);
            read();
        }
        curPos += ((Character.toString(buff[curPos]).equals(System.getProperty("line.separator"))) ? 1 : 0);
        return (curSize != -1);
    }

    public String nextLine() {
        String temp = sb.toString();
        sb = new StringBuilder();
        return temp;
    }

    public boolean hasNextWord() throws IOException {
        skipNotAllowedSymbols();
        while ((curPos < curSize || read()) && goodForWord(buff[curPos])) {
            sb.append(buff[curPos++]);
        }
        return (sb.length() > 0);
    }

    public String nextWord() {
        String temp = sb.toString();
        sb = new StringBuilder();
        return temp;
    }

    public void close() throws IOException {
        reader.close();
    }
}