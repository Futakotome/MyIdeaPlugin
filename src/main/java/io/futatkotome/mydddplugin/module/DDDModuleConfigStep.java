package io.futatkotome.mydddplugin.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.openapi.options.ConfigurationException;
import io.futatkotome.mydddplugin.domain.model.vo.ProjectConfigVO;
import io.futatkotome.mydddplugin.infrastructure.DataSetting;
import io.futatkotome.mydddplugin.ui.ProjectConfigUI;

import javax.swing.*;

public class DDDModuleConfigStep extends ModuleWizardStep {

    private ProjectConfigUI projectConfigUI;

    public DDDModuleConfigStep(ProjectConfigUI projectConfigUI) {
        this.projectConfigUI = projectConfigUI;
    }

    @Override
    public JComponent getComponent() {
        return projectConfigUI.getComponent();
    }

    @Override
    public void updateDataModel() {

    }

    @Override
    public boolean validate() throws ConfigurationException {
        // 获取配置信息，写入到 DataSetting
        ProjectConfigVO projectConfig = DataSetting.getInstance().getProjectConfig();
        projectConfig.set_groupId(projectConfigUI.getGroupIdField().getText());
        projectConfig.set_artifactId(projectConfigUI.getArtifactIdField().getText());
        projectConfig.set_version(projectConfigUI.getVersionField().getText());
        projectConfig.set_package(projectConfigUI.getPackageField().getText());

        return super.validate();
    }
}
