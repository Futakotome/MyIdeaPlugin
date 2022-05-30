package io.futatkotome.mydddplugin.infrastructure;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import io.futatkotome.mydddplugin.domain.model.vo.ORMConfigVO;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "ORMDataSetting", storages = @Storage("plugin.xml"))
public class ORMDataSetting implements PersistentStateComponent<DataState> {
    private DataState state = new DataState();

    public static ORMDataSetting getInstance(Project project) {
        return ServiceManager.getService(project, ORMDataSetting.class);
    }

    public ORMConfigVO getORMConfig() {
        return state.getOrmConfigVO();
    }

    @Override
    public @Nullable
    DataState getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull DataState dataState) {
        this.state = dataState;
    }
}
