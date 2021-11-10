package markup;

public interface Html extends Markdown {
    public void toHtml(StringBuilder sb);
    @Override
    public void toMarkdown(StringBuilder sb);
}