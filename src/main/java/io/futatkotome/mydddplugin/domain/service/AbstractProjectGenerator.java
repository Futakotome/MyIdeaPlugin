package io.futatkotome.mydddplugin.domain.service;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.futatkotome.mydddplugin.domain.model.vo.ProjectConfigVO;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public abstract class AbstractProjectGenerator extends FreemarkerConfiguration implements IProjectGenerator {

    @Override
    public void doGenerator(Project project, String entryPath, ProjectConfigVO projectConfigVO) {
        // 1.创建工程主POM文件
        generateProjectPOM(project, entryPath, projectConfigVO);

        // 2.创建四层架构
        generateProjectDDD(project, entryPath, projectConfigVO);

        // 3.创建 Application
        generateApplication(project, entryPath, projectConfigVO);

        // 4. 创建 Yml
        generateYml(project, entryPath, projectConfigVO);

        // 5. 创建 Common
        generateCommon(project, entryPath, projectConfigVO);

        // 6. 创建git ignore
        generateIgnore(project, entryPath, projectConfigVO);

        // 7. 创建mybatis框架相关
        generateMyBatisGeneratorAndConfig(project, entryPath, projectConfigVO);
    }

    protected abstract void generateProjectPOM(Project project, String entryPath, ProjectConfigVO projectConfig);

    protected abstract void generateProjectDDD(Project project, String entryPath, ProjectConfigVO projectConfig);

    protected abstract void generateApplication(Project project, String entryPath, ProjectConfigVO projectConfig);

    protected abstract void generateYml(Project project, String entryPath, ProjectConfigVO projectConfig);

    protected abstract void generateCommon(Project project, String entryPath, ProjectConfigVO projectConfig);

    protected abstract void generateIgnore(Project project, String entryPath, ProjectConfigVO projectConfig);

    protected abstract void generateMyBatisGeneratorAndConfig(Project project, String entryPath, ProjectConfigVO projectConfig);

    public void writeFile(Project project, String packageName, String entryPath, String name, String ftl, Object dataModel) {
        VirtualFile virtualFile = null;
        try {
            virtualFile = createPackageDir(packageName, entryPath).createChildData(project, name);
            StringWriter stringWriter = new StringWriter();
            Template template = super.getTemplate(ftl);
            template.process(dataModel, stringWriter);
            virtualFile.setBinaryContent(stringWriter.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private static VirtualFile createPackageDir(String packageName, String entryPath) {
        String path = FileUtil.toSystemIndependentName(entryPath + "/" + StringUtil.replace(packageName, ".", "/"));
        new File(path).mkdirs();
        return LocalFileSystem.getInstance().refreshAndFindFileByPath(path);
    }
}
