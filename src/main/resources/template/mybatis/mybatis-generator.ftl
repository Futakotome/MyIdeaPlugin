<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<!-- 配置生成器 -->
<generatorConfiguration>
    <context id="MysqlTables" targetRuntime="MyBatis3" defaultModelType="flat">
        <!-- 自动识别数据库关键字，默认false，如果设置为true，根据SqlReservedWords中定义的关键字列表；一般保留默认值，遇到数据库关键字（Java关键字），使用columnOverride覆盖 -->
        <property name="autoDelimitKeywords" value="true"/>
        <!-- 生成的Java文件的编码 -->
        <property name="javaFileEncoding" value="utf-8"/>
        <!-- beginningDelimiter和endingDelimiter：指明数据库的用于标记数据库对象名的符号，比如ORACLE就是双引号，MYSQL默认是`反引号； -->
        <property name="beginningDelimiter" value="`"/>
        <property name="endingDelimiter" value="`"/>
        <!-- 格式化java代码 -->
        <property name="javaFormatter" value="org.mybatis.generator.api.dom.DefaultJavaFormatter"/>
        <!-- 格式化XML代码 -->
        <property name="xmlFormatter" value="org.mybatis.generator.api.dom.DefaultXmlFormatter"/>
        <plugin type="org.mybatis.generator.plugins.SerializablePlugin"/>
        <plugin type="org.mybatis.generator.plugins.ToStringPlugin"/>
        <!-- 注释 -->
        <commentGenerator>
            <property name="suppressAllComments" value="true"/>
            <!-- 是否取消注释 -->
            <property name="suppressDate" value="false"/> <!-- 是否生成注释代时间戳-->
        </commentGenerator>
        <!-- jdbc连接-->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:32000/test?serverTimezone=UTC" userId="root"
                        password="password">
            <property name="nullCatalogMeansCurrent" value="true" />
        </jdbcConnection>
        <!-- 类型转换 -->
        <javaTypeResolver>
            <!-- 是否使用bigDecimal， false可自动转化以下类型（Long, Integer, Short, etc.） -->
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>
        <!-- 生成实体类地址 -->
        <javaModelGenerator targetPackage="io.futakotome.test.infrastructure.pojo" targetProject="src/main/java">
            <!-- 是否允许子包 是否让schema作为包的后缀 -->
            <property name="enableSubPackages" value="false"/>
            <!-- 是否对modal添加构造函数 -->
            <property name="constructorBased" value="true"/>
            <!-- 从数据库返回的值去掉前后空格 -->
            <property name="trimStrings" value="true"/>
            <!-- 建立modal对象是否不可改变 即生成的modal对象不会有setter方法，只有构造方法 -->
            <property name="immutable" value="false"/>
        </javaModelGenerator>
        <!-- 生成mapper.xml文件存放地址 -->
        <sqlMapGenerator targetPackage="mapper" targetProject="src/main/resources">
            <property name="enableSubPackages" value="false"/>
        </sqlMapGenerator>
        <!-- 生成接口dao -->
        <javaClientGenerator targetPackage="io.futakotome.test.infrastructure.dao" targetProject="src/main/java" type="XMLMAPPER">
            <property name="enableSubPackages" value="false"/>
        </javaClientGenerator>
        <!-- table可以有多个,每个数据库中的表都可以写一个table，tableName表示要匹配的数据库表,也可以在tableName属性中通过使用%通配符来匹配所有数据库表,只有匹配的表才会自动生成文件 enableSelectByPrimaryKey相应的配置表示是否生成相应的接口 -->
        <!--
        < table tableName="t_user"
               schema="test"
               domainObjectName="Users"
               enableCountByExample="true"
               enableUpdateByExample="true"
               enableDeleteByExample="true"
               enableSelectByExample="true"
               selectByExampleQueryId="true"
               enableSelectByPrimaryKey="true"
               enableUpdateByPrimaryKey="true"
               enableDeleteByPrimaryKey="true">
        </table>
        -->
    </context>
</generatorConfiguration>