package io.futatkotome.mydddplugin.infrastructure.po;

import java.util.List;

public class Table {
    private final String comment;
    private final String name;
    private final List<Column> columns;

    public Table(String comment, String name, List<Column> columns) {
        this.comment = comment;
        this.name = name;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public List<Column> getColumns() {
        return columns;
    }
}
