package io.futatkotome.mydddplugin.domain.model.vo;

import io.futatkotome.mydddplugin.infrastructure.po.Table;

import java.util.List;

public class ORMCodeGenContextVO {

    private String modelPackage;
    private String daoPackage;
    private String mapperDir;

    private List<Table> tables;

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public String getMapperDir() {
        return mapperDir;
    }

    public void setMapperDir(String mapperDir) {
        this.mapperDir = mapperDir;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
