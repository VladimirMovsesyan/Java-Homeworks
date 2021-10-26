package markup;

import java.util.*;

public abstract class ModifyElement {
    protected StringBuilder modify(List<Tag> curr, String sym) {
        StringBuilder sb = new StringBuilder(sym);

        for (Tag i : curr) {
            sb.append(i);
        }
        
        sb.append(sym);
        return sb;
    }
}