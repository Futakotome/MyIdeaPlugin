package io.futatkotome.mydddplugin.infrastructure;

import io.futatkotome.mydddplugin.domain.model.vo.ProjectConfigVO;

public class DataState {
    private ProjectConfigVO projectConfigVO = new ProjectConfigVO();

    public ProjectConfigVO getProjectConfigVO() {
        return projectConfigVO;
    }

    public void setProjectConfigVO(ProjectConfigVO projectConfigVO) {
        this.projectConfigVO = projectConfigVO;
    }
}
