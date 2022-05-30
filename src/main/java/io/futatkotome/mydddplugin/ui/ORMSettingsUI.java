package io.futatkotome.mydddplugin.ui;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.vfs.VirtualFile;
import io.futatkotome.mydddplugin.domain.model.vo.ORMCodeGenContextVO;
import io.futatkotome.mydddplugin.domain.model.vo.ORMConfigVO;
import io.futatkotome.mydddplugin.domain.service.IORMGenerator;
import io.futatkotome.mydddplugin.infrastructure.DBHelper;
import io.futatkotome.mydddplugin.infrastructure.ORMDataSetting;
import io.futatkotome.mydddplugin.infrastructure.po.Table;
import io.futatkotome.mydddplugin.module.FileChooserComponent;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ORMSettingsUI implements Configurable {
    private JPanel main;
    private JTextField classpath;
    private JTextField projectName;
    private JTextField database;
    private JTextField user;
    private JPasswordField password;
    private JTextField poPath;
    private JTextField daoPath;
    private JTextField xmlPath;
    private JTextField host;
    private JButton poButton;
    private JTextField port;
    private JButton daoButton;
    private JButton xmlButton;
    private JButton selectButton;
    private JButton testButton;
    private JTable table1;

    private final ORMConfigVO config;
    private final Project project;
    private final IORMGenerator iormGenerator;

    public ORMSettingsUI(Project project, IORMGenerator iormGenerator) {
        this.project = project;
        this.iormGenerator = iormGenerator;
        config = ORMDataSetting.getInstance(null != project ? project : ProjectManager.getInstance().getDefaultProject()).getORMConfig();

        this.projectName.setText(project.getName());
        this.classpath.setText(project.getBasePath());
        this.database.setText(config.getDatabase());
        this.host.setText(config.getHost());
        this.port.setText(config.getPort());
        this.poPath.setText(config.getPoPath());
        this.daoPath.setText(config.getDaoPath());
        this.xmlPath.setText(config.getXmlPath());
        // 选择PO生成目录
        this.poButton.addActionListener(e -> {
            FileChooserComponent component = FileChooserComponent.getInstance(project);
            VirtualFile baseDir = project.getBaseDir();
            VirtualFile virtualFile = component.showFolderSelectionDialog("选择PO生成目录", baseDir, baseDir);
            if (null != virtualFile) {
                ORMSettingsUI.this.poPath.setText(virtualFile.getPath());
            }
        });

        // 选择DAO生成目录
        this.daoButton.addActionListener(e -> {
            FileChooserComponent component = FileChooserComponent.getInstance(project);
            VirtualFile baseDir = project.getBaseDir();
            VirtualFile virtualFile = component.showFolderSelectionDialog("选择DAO生成目录", baseDir, baseDir);
            if (null != virtualFile) {
                ORMSettingsUI.this.daoPath.setText(virtualFile.getPath());
            }
        });

        // 选择XMl生成目录
        this.xmlButton.addActionListener(e -> {
            FileChooserComponent component = FileChooserComponent.getInstance(project);
            VirtualFile baseDir = project.getBaseDir();
            VirtualFile virtualFile = component.showFolderSelectionDialog("选择XML生成目录", baseDir, baseDir);
            if (null != virtualFile) {
                ORMSettingsUI.this.xmlPath.setText(virtualFile.getPath());
            }
        });

        // 查询数据库表列表
        this.selectButton.addActionListener(e -> {
            try {
                DBHelper dbHelper = new DBHelper(this.host.getText(), Integer.parseInt(this.port.getText()), this.user.getText(), this.password.getText(), this.database.getText());
                List<String> tableList = dbHelper.getAllTableName(this.database.getText());

                String[] title = {"", "表名"};
                Object[][] data = new Object[tableList.size()][2];
                for (int i = 0; i < tableList.size(); i++) {
                    data[i][1] = tableList.get(i);
                }

                table1.setModel(new DefaultTableModel(data, title));
                TableColumn tc = table1.getColumnModel().getColumn(0);
                tc.setCellEditor(new DefaultCellEditor(new JCheckBox()));
                tc.setCellEditor(table1.getDefaultEditor(Boolean.class));
                tc.setCellRenderer(table1.getDefaultRenderer(Boolean.class));
                tc.setMaxWidth(100);
            } catch (Exception exception) {
                Messages.showWarningDialog(project, "Database connect error.", "Warning");
            }
        });

        // 给表添加事件
        this.table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (1 == e.getClickCount()) {
                    int rowIdx = table1.rowAtPoint(e.getPoint());
                    Boolean flag = (Boolean) table1.getValueAt(rowIdx, 0);
                    Set<String> tableNames = ORMSettingsUI.this.config.getTableNames();
                    if (null != flag && flag) {
                        tableNames.add(table1.getValueAt(rowIdx, 1).toString());
                    } else {
                        tableNames.remove(table1.getValueAt(rowIdx, 1).toString());
                    }
                }
            }
        });

        // 测试数据库链接
        this.testButton.addActionListener(e -> {
            try {
                DBHelper dbHelper = new DBHelper(this.host.getText(), Integer.parseInt(this.port.getText()), this.user.getText(), this.password.getText(), this.database.getText());
                String mysqlVersion = dbHelper.testDatabase();
                Messages.showInfoMessage(project, "Connection successful! \r\nMySQL version : " + mysqlVersion, "OK");
            } catch (Exception exception) {
                Messages.showWarningDialog(project, "Database connect error.", "Warning");
            }
        });
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Config";
    }

    @Override
    public @Nullable
    JComponent createComponent() {
        return main;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        // 获取配置
        config.setUser(this.user.getText());
        config.setPassword(new String(this.password.getPassword()));
        config.setProjectName(this.projectName.getText());
        config.setClasspath(this.classpath.getText());
        config.setDatabase(this.database.getText());
        config.setHost(this.host.getText());
        config.setPort(this.port.getText() != null ? this.port.getText() : "3306");
        config.setPoPath(this.poPath.getText());
        config.setDaoPath(this.daoPath.getText());
        config.setXmlPath(this.xmlPath.getText());

        // 链接DB
        DBHelper dbHelper = new DBHelper(config.getHost(), Integer.parseInt(config.getPort()), config.getUser(), config.getPassword(), config.getDatabase());

        // 组装代码生产上下文
        ORMCodeGenContextVO codeGenContext = new ORMCodeGenContextVO();
        codeGenContext.setModelPackage(config.getPoPath() + "/po/");
        codeGenContext.setDaoPackage(config.getDaoPath() + "/dao/");
        codeGenContext.setMapperDir(config.getXmlPath() + "/mapper/");
        List<Table> tables = new ArrayList<>();
        Set<String> tableNames = config.getTableNames();
        for (String tableName : tableNames) {
            tables.add(dbHelper.getTable(tableName));
        }
        codeGenContext.setTables(tables);

        // 生成代码
        iormGenerator.doGenerate(project, codeGenContext);
    }
}
