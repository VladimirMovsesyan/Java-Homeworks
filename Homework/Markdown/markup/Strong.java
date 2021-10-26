package markup;

import java.util.*;

public class Strong extends ModifyElement implements Tag {
    StringBuilder sb;
    
    Strong(List<Tag> ls) {
        sb = super.modify(ls, "__");
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
