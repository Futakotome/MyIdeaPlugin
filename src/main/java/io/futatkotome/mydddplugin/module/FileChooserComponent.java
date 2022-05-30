package io.futatkotome.mydddplugin.module;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FileChooserComponent {
    private final Project project;

    public FileChooserComponent(Project project) {
        this.project = project;
    }

    public static FileChooserComponent getInstance(@NotNull Project project) {
        return new FileChooserComponent(project);
    }

    public VirtualFile showFolderSelectionDialog(@NotNull String title,
                                                 @Nullable VirtualFile toSelect,
                                                 @Nullable VirtualFile... roots) {
        final FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();
        descriptor.setTitle(title);
        if (null != roots) {
            descriptor.setRoots(roots);
        }
        return FileChooser.chooseFile(descriptor, project, toSelect);
    }

}
