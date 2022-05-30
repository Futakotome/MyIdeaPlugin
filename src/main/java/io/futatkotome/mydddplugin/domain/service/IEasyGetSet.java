package io.futatkotome.mydddplugin.domain.service;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;

public interface IEasyGetSet {
    void doGenerate(Project project, DataContext dataContext);
}
