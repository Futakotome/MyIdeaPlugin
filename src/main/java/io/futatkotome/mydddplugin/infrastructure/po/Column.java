package io.futatkotome.mydddplugin.infrastructure.po;

public class Column {
    private final String comment;
    private final String name;
    private final int type;
    private boolean id;

    public Column(String comment, String name, int type) {
        this.comment = comment;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public boolean isId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public void setId(boolean id) {
        this.id = id;
    }
}
