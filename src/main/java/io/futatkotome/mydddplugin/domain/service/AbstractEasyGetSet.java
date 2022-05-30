package io.futatkotome.mydddplugin.domain.service;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import io.futatkotome.mydddplugin.domain.model.GenerateContext;
import io.futatkotome.mydddplugin.domain.model.dataObject.GetObjConfigDO;
import io.futatkotome.mydddplugin.domain.model.dataObject.SetObjConfigDO;

public abstract class AbstractEasyGetSet implements IEasyGetSet {
    @Override
    public void doGenerate(Project project, DataContext dataContext) {
        // 1. 获取上下文
        GenerateContext generateContext = this.getGenerateContext(project, dataContext);

        // 2. 获取对象的 set 方法集合
        SetObjConfigDO setObjConfigDO = this.getSetObjConfigDO(generateContext);

        // 3. 获取对象的 get 方法集合 【从剪切板获取】
        GetObjConfigDO getObjConfigDO = this.getObjConfigDOByClipboardText(generateContext);

        // 4. 织入代码 set->get
        this.weavingSetGetCode(generateContext, setObjConfigDO, getObjConfigDO);
    }

    protected abstract GenerateContext getGenerateContext(Project project, DataContext dataContext);

    protected abstract SetObjConfigDO getSetObjConfigDO(GenerateContext generateContext);

    protected abstract GetObjConfigDO getObjConfigDOByClipboardText(GenerateContext generateContext);

    protected abstract void weavingSetGetCode(GenerateContext generateContext, SetObjConfigDO setObjConfigDO, GetObjConfigDO getObjConfigDO);
}
