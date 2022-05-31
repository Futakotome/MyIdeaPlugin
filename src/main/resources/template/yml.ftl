server:
    port: 9000
<#--数据库配置-->
mybatis:
    mapper-locations: classpath:mapper/*Mapper.xml
    type-aliases-package: ${_package}.infrastucture.po.*
    config-location: classpath:mybatis-config.xml