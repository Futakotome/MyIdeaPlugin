package io.futatkotome.mydddplugin.domain.service.impl;

import com.google.common.base.CaseFormat;
import com.intellij.openapi.project.Project;
import io.futatkotome.mydddplugin.domain.model.vo.ORMCodeGenContextVO;
import io.futatkotome.mydddplugin.domain.service.AbstractORMGenerator;
import io.futatkotome.mydddplugin.infrastructure.JavaType;
import io.futatkotome.mydddplugin.infrastructure.po.*;

import java.util.ArrayList;
import java.util.List;

public class ORMGeneratorImpl extends AbstractORMGenerator {
    @Override
    protected void generateORM(Project project, ORMCodeGenContextVO ormCodeGenContextVO) {
        List<Table> tables = ormCodeGenContextVO.getTables();
        for (Table table : tables) {
            List<Column> columns = table.getColumns();
            List<Field> fields = new ArrayList<>();
            for (Column column : columns) {
                Field field = new Field(column.getComment(), JavaType.convertType(column.getType()), column.getName());
                field.setId(column.isId());
                fields.add(field);
            }

            // 生成PO
            Model model = new Model(table.getComment(), ormCodeGenContextVO.getModelPackage() + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName()), table.getName(), fields);
            writeFile(project, ormCodeGenContextVO.getModelPackage(), model.getSimpleName() + ".java", "domain/orm/model.ftl", model);

            // 生成DAO
            Dao dao = new Dao(table.getComment(), ormCodeGenContextVO.getDaoPackage() + "I" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName()) + "Dao", model);
            writeFile(project, ormCodeGenContextVO.getDaoPackage(), dao.getSimpleName() + ".java", "domain/orm/dao.ftl", dao);

            // 生成Mapper
            writeFile(project, ormCodeGenContextVO.getMapperDir(), dao.getModel().getSimpleName() + "Mapper.xml", "domain/orm/mapper.ftl", dao);

        }
    }
}
