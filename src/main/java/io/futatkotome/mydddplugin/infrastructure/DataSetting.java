package io.futatkotome.mydddplugin.infrastructure;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import io.futatkotome.mydddplugin.domain.model.vo.ProjectConfigVO;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "DataSetting", storages = @Storage("plugin.xml"))
public class DataSetting implements PersistentStateComponent<DataState> {
    private DataState state = new DataState();

    public static DataSetting getInstance() {
        return ServiceManager.getService(DataSetting.class);
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

    public ProjectConfigVO getProjectConfig() {
        return state.getProjectConfigVO();
    }

}
