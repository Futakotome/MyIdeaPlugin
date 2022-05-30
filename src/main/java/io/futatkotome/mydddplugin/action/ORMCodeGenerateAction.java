package io.futatkotome.mydddplugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import io.futatkotome.mydddplugin.domain.service.IORMGenerator;
import io.futatkotome.mydddplugin.domain.service.impl.ORMGeneratorImpl;
import io.futatkotome.mydddplugin.ui.ORMSettingsUI;

public class ORMCodeGenerateAction extends AnAction {
    private final IORMGenerator iormGenerator = new ORMGeneratorImpl();

    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getRequiredData(CommonDataKeys.PROJECT);
        ShowSettingsUtil.getInstance().editConfigurable(project, new ORMSettingsUI(project, iormGenerator));
    }
}
