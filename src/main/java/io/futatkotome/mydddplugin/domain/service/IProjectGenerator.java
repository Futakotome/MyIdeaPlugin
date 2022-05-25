package io.futatkotome.mydddplugin.domain.service;

import com.intellij.openapi.project.Project;
import io.futatkotome.mydddplugin.domain.model.vo.ProjectConfigVO;

public interface IProjectGenerator {
    void doGenerator(Project project, String entryPath, ProjectConfigVO projectConfigVO);
}
