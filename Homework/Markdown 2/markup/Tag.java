package markup;

public interface Tag extends Html {
    @Override
    public void toHtml(StringBuilder sb);
    @Override
    public void toMarkdown(StringBuilder sb);
}
