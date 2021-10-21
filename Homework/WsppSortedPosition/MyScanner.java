import java.io.*;

public class MyScanner {
    private BufferedReader reader;
    private char[] buff;
    private int curSize, curPos;
    private StringBuilder sb;
    final private int buffSize = 128;
    private boolean isEndFound = false;

    MyScanner(InputStream input) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(input));
        this.buff = new char[this.buffSize];
        this.curPos = 0;
        this.curSize = -2;
        this.sb = new StringBuilder();
        read();
    }

    MyScanner(String s) throws IOException {
        this.reader = new BufferedReader(new StringReader(s));
        this.buff = new char[this.buffSize];
        this.curPos = 0;
        this.curSize = -2;
        this.sb = new StringBuilder();
        read();
    }

    MyScanner(String s, String codec) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(s), codec));
        this.buff = new char[this.buffSize];
        this.curPos = 0;
        this.curSize = -2;
        this.sb = new StringBuilder();
        read();
    }

    public boolean read() throws IOException {
        if (this.curPos < this.curSize)
            return true;
        this.curSize = this.reader.read(this.buff, 0, this.buffSize);
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

    public boolean checkEnd() {
        if (isEndFound) {
            isEndFound = false;
            return true;
        }
        return false;
    }

    public void skipNotAllowedSymbols() throws IOException {
        while (read() && !goodForWord(this.buff[this.curPos])) {
            isEndFound = ((Character.toString(this.buff[this.curPos]).equals(System.getProperty("line.separator"))) ? true : isEndFound);
            this.curPos++;
        }
    }

    public boolean hasNextInt() throws IOException {
        skipWhiteSpaces();
        while ((this.curPos < this.curSize || read()) && goodForInt(this.buff[this.curPos])) {
            this.sb.append(this.buff[this.curPos++]);
        }
        return (this.sb.length() > 0);
    }

    public int nextInt() {
        String temp = this.sb.toString();
        this.sb = new StringBuilder();
        return Integer.parseInt(temp);
    }

    public boolean hasNextLine() throws IOException {
        while (read() && !(Character.toString(this.buff[this.curPos]).equals(System.getProperty("line.separator")))) {
            this.sb.append(this.buff[this.curPos++]);
            read();
        }
        this.curPos += ((Character.toString(this.buff[this.curPos]).equals(System.getProperty("line.separator"))) ? 1 : 0);
        return (this.curSize != -1);
    }

    public String nextLine() {
        String temp = this.sb.toString();
        this.sb = new StringBuilder();
        return temp;
    }

    public boolean hasNextWord() throws IOException {
        skipNotAllowedSymbols();
        while ((this.curPos < this.curSize || read()) && goodForWord(this.buff[this.curPos])) {
            this.sb.append(this.buff[this.curPos++]);
        }
        return (this.sb.length() > 0);
    }

    public String nextWord() {
        String temp = this.sb.toString();
        this.sb = new StringBuilder();
        return temp;
    }

    public void close() throws IOException {
        this.reader.close();
    }
}