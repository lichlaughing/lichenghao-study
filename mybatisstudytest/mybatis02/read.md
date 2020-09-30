mybatis 配置文件

# environments

修改default为指定的数据库环境
```java
<environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url"
                          value="jdbc:mysql://127.0.0.1:3306/mybatis_test?userSSL=false&amp;characterEncoding=utf-8"/>
                <property name="username" value="root"/>
                <property name="password" value="root1234"/>
            </dataSource>
        </environment>
        <environment id="test">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url"
                          value="jdbc:mysql://127.0.0.1:3306/mybatis_test?userSSL=false&amp;characterEncoding=utf-8"/>
                <property name="username" value="root"/>
                <property name="password" value="root1234"/>
            </dataSource>
        </environment>
    </environments>
```
# properties

db.properties

注意：配置文件用了`"jdbc:mysql://127.0.0.1:3306/mybatis_test?userSSL=false&amp;characterEncoding=utf-8"`

把`&amp`修改为`&`

```properties
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://127.0.0.1:3306/mybatis_test?userSSL=false&characterEncoding=utf-8
username=root
password=root1234
```

配置文件引入properties

```xml
<!--数据库配置文件-->
<properties resource="db.properties"/>
```

# typeAliases

类别名，两种方式

```xml
<!--别名 指定类的别名,实体类较少的时候可以使用-->
<typeAliases>
    <typeAlias type="com.lichenghao.entity.User" alias="User"></typeAlias>
</typeAliases>
<!--扫描包，用类名小写代表别名;可以使用注解代替默认的类型小写-->
<typeAliases>
    <package name="com.lichenghao.entity"></package>
</typeAliases>
//扫描包的方式,使用注解自定义别名
@Alias("User")
public class User {}
```

# settings（配置）

```xml
<settings>
  <setting name="cacheEnabled" value="true"/>
  <setting name="lazyLoadingEnabled" value="true"/>
  <setting name="multipleResultSetsEnabled" value="true"/>
  <setting name="useColumnLabel" value="true"/>
  <setting name="useGeneratedKeys" value="false"/>
  <setting name="autoMappingBehavior" value="PARTIAL"/>
  <setting name="autoMappingUnknownColumnBehavior" value="WARNING"/>
  <setting name="defaultExecutorType" value="SIMPLE"/>
  <setting name="defaultStatementTimeout" value="25"/>
  <setting name="defaultFetchSize" value="100"/>
  <setting name="safeRowBoundsEnabled" value="false"/>
  <setting name="mapUnderscoreToCamelCase" value="false"/>
  <setting name="localCacheScope" value="SESSION"/>
  <setting name="jdbcTypeForNull" value="OTHER"/>
  <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString"/>
</settings>
```

# mappers（映射器）

绑定mapper文件，建议将接口和mapper文件同名并且在同一包下，写为`xxMapper.java`配置文件为`xxMapper.xml`

```xml
<!-- 使用相对于类路径的资源引用 -->
<mappers>
  <mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
  <mapper resource="org/mybatis/builder/BlogMapper.xml"/>
  <mapper resource="org/mybatis/builder/PostMapper.xml"/>
</mappers>

<!-- 使用完全限定资源定位符（URL） -->
<mappers>
  <mapper url="file:///var/mappers/AuthorMapper.xml"/>
  <mapper url="file:///var/mappers/BlogMapper.xml"/>
  <mapper url="file:///var/mappers/PostMapper.xml"/>
</mappers>

<!-- 使用映射器接口实现类的完全限定类名  注意:接口和mapper文件同名，并且在同一个包下 -->
<mappers>
  <mapper class="org.mybatis.builder.AuthorMapper"/>
  <mapper class="org.mybatis.builder.BlogMapper"/>
  <mapper class="org.mybatis.builder.PostMapper"/>
</mappers>

<!-- 将包内的映射器接口实现全部注册为映射器 注意:接口和mapper文件同名，并且在同一个包下 -->
<mappers>
  <package name="org.mybatis.builder"/>
</mappers>
```

