# 日志配置

```xml
<settings>
        <!--<setting name="logImpl" value="STDOUT_LOGGING"/>-->
        <setting name="logImpl" value="LOG4J"/>
</settings>
```

# 分页查询

limit

```xml
<select id="userList" resultMap="UserMap" parameterType="map">
        select * from tb_user limit #{start},#{pageSize}
</select>
```

RowBounds

......

分页插件

https://pagehelper.github.io/



