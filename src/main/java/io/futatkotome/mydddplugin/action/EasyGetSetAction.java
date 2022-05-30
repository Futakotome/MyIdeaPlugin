package io.futatkotome.mydddplugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import io.futatkotome.mydddplugin.domain.service.IEasyGetSet;
import io.futatkotome.mydddplugin.domain.service.impl.EasyGetSetImpl;
import org.jetbrains.annotations.NotNull;

public class EasyGetSetAction extends AnAction {
    private IEasyGetSet easyGetSet = new EasyGetSetImpl();

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        try {
            easyGetSet.doGenerate(anActionEvent.getProject(), anActionEvent.getDataContext());
        } catch (Exception e) {
            Messages.showErrorDialog(anActionEvent.getProject(), "Generate error", "请按规复制对象后，光标放到需要织入的对象上!");
        }
    }
}
