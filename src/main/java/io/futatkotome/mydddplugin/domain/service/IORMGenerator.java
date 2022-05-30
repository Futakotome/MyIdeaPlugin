package io.futatkotome.mydddplugin.domain.service;

import com.intellij.openapi.project.Project;
import io.futatkotome.mydddplugin.domain.model.vo.ORMCodeGenContextVO;

public interface IORMGenerator {
    void doGenerate(Project project, ORMCodeGenContextVO ormCodeGenContextVO);
}
