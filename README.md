# ssitest
a web architecture use gulp+angular+ssi
### backend
1. spring-3.2.2.RELEASE as the service layer
2. spring-mvc as the mvc layer
3. mybatis-3.4.1 as the persistence layer
4. maven as the build tool 
5. use redis as the noSql layer
6. use org.mybatis.spring.mapper.MapperScannerConfigurer as the dao mapper
### front-end
1. gulp as the build tool
2. angular for the modulize
3. ng-file-upload for the file upload
4. angular-ui-router for the route

### sample project structure
#### role and activity:
##### admin:
- manage users: browse user list with name,fileNum,disk usage; lock\unlock user
- manage files: browse files list with name,size,upload time,download num,owner;delete\recover file
##### user:
- upload/browse/delete/download/move/share file
- create/delete directory
- login/logout
