package io.futatkotome.mydddplugin.domain.service;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.futatkotome.mydddplugin.domain.model.vo.ORMCodeGenContextVO;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public abstract class AbstractORMGenerator extends FreemarkerConfiguration implements IORMGenerator {
    @Override
    public void doGenerate(Project project, ORMCodeGenContextVO ormCodeGenContextVO) {
        generateORM(project, ormCodeGenContextVO);
    }

    protected abstract void generateORM(Project project, ORMCodeGenContextVO ormCodeGenContextVO);


    public void writeFile(Project project, String packageName, String name, String ftl, Object dataModel) {
        VirtualFile virtualFile = null;
        try {
            virtualFile = createPackageDir(packageName).createChildData(project, name);
            StringWriter stringWriter = new StringWriter();
            Template template = super.getTemplate(ftl);
            template.process(dataModel, stringWriter);
            virtualFile.setBinaryContent(stringWriter.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private static VirtualFile createPackageDir(String packageName) {
        String path = FileUtil.toSystemIndependentName(StringUtil.replace(packageName, ".", "/"));
        new File(path).mkdirs();
        return LocalFileSystem.getInstance().refreshAndFindFileByPath(path);
    }

}
