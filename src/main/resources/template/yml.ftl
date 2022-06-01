server:
    port: 9000

spring:
    datasource:
        url:
        username:
        password:
        driver-class-name:
mybatis:
    mapper-locations: classpath:mapper/*Mapper.xml
    type-aliases-package: ${_package}.infrastucture.po.*
    config-location: classpath:mybatis-config.xml