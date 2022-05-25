package io.futatkotome.mydddplugin.factory;

import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.platform.ProjectTemplate;
import com.intellij.platform.ProjectTemplatesFactory;
import com.intellij.platform.templates.BuilderBasedTemplate;
import io.futatkotome.mydddplugin.infrastructure.ICONS;
import io.futatkotome.mydddplugin.module.DDDModuleBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TemplateFactory extends ProjectTemplatesFactory {
    @Override
    @NotNull
    public String[] getGroups() {
        return new String[]{"DDD脚手架"};
    }

    @Override
    public Icon getGroupIcon(String group) {
        return ICONS.DDD;
    }

    @Override
    @NotNull
    public ProjectTemplate[] createTemplates(@Nullable String s, WizardContext wizardContext) {
        return new ProjectTemplate[]{new BuilderBasedTemplate(new DDDModuleBuilder())};
    }
}
