package io.futatkotome.mydddplugin.infrastructure;

import io.futatkotome.mydddplugin.domain.model.vo.ORMConfigVO;
import io.futatkotome.mydddplugin.domain.model.vo.ProjectConfigVO;

public class DataState {
    private ProjectConfigVO projectConfigVO = new ProjectConfigVO();
    private ORMConfigVO ormConfigVO = new ORMConfigVO();

    public ORMConfigVO getOrmConfigVO() {
        return ormConfigVO;
    }

    public void setOrmConfigVO(ORMConfigVO ormConfigVO) {
        this.ormConfigVO = ormConfigVO;
    }

    public ProjectConfigVO getProjectConfigVO() {
        return projectConfigVO;
    }

    public void setProjectConfigVO(ProjectConfigVO projectConfigVO) {
        this.projectConfigVO = projectConfigVO;
    }
}
