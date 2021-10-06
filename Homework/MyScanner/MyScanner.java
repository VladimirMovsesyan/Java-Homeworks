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

    public void skipWhiteSpaces() throws IOException {
        while (read() && !goodForInt(this.buff[this.curPos])) {
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
        while (read() && buff[curPos] != '\n') {
            sb.append(buff[curPos++]);
            read();
        }
        curPos += ((buff[curPos] == '\n') ? 1 : 0);
        return (curSize != -1);
    }

    public String nextLine() {
        String temp = sb.toString();
        sb = new StringBuilder();
        return temp;
    }

    public void close() throws IOException {
        reader.close();
    }
}
/* 
public boolean read() throws IOException {
        if (curPos < curSize) {
            return true;
        }
        this.curSize = this.reader.read(this.buff, 0, 512);
        curPos = 0;
        if (curSize == -1) {
            endOfFile = true;
            return false;
        }
        return true;
    }

    public void skipWhiteSpaces() {
        while (curPos < curSize && Character.isWhitespace(buff[curPos])) {
            curPos++;
        }
        if (curPos == curSize) {
            endOfLine = (buff[curPos - 1] == '\n');
        }
    }

    public boolean hasNextInt() throws IOException {
        if (curPos >= curSize) {
            read();
        }
        skipWhiteSpaces();
        while (curPos < curSize && (Character.isDigit(buff[curPos]) || buff[curPos] == '-') && read()) {
            sb.append(buff[curPos]);
            curPos++;
            if (curPos == curSize)
                System.err.println(read());
        }
        if (curPos == 512)
            System.err.println(curPos + " " + curSize);
        if (curPos == curSize)
            System.err.println(read());
        if (buff[curPos] == '\n')
            curPos++;
        return (sb.length() > 0);
    }
    
    public int nextInt() {
        int num = Integer.parseInt(sb.toString());
        sb = new StringBuilder();
        return num;
    }

    public boolean hasNextLine() throws IOException {
        read();
        if (curSize == -1)
            return false;
        while (buff[curPos] != '\n') {
            sb.append(buff[curPos++]);
            if (curPos == curSize)
                read();
            // if (buff[curPos] == '\n') {
            //     curPos++;
            // }
        }
        read();
        System.err.println(curPos + " " + curSize + " " + (buff[curSize - 1] == '\n'));
        return (sb.length() > 0);
    }

    public String nextLine() {
        String temp = sb.toString();
        sb = new StringBuilder();
        // System.err.println(curPos + " " + curSize + " " + (buff[curPos + 1] == '\n') + " " + buff[curPos + 1]);
        return temp;
    }

    public void close() throws IOException {
        reader.close();
    }
*/