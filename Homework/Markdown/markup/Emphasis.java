package markup;
import java.util.*;

public class Emphasis extends ModifyElement implements Tag {
    StringBuilder sb;
    
    Emphasis(List<Tag> ls) {
        sb = super.modify(ls, "*");
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
