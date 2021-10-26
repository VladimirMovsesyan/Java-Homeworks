package markup;

import java.util.*;

public class Strikeout extends ModifyElement implements Tag {
    StringBuilder sb;
    
    Strikeout(List<Tag> ls) {
        sb = super.modify(ls, "~");
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
