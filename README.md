# DataCollect-server

毕业设计《大数据获取系统的设计与实现》服务端

Client项目：https://github.com/youyadefeng/DataCollect-Client

Server项目：https://github.com/youyadefeng/DataCollect-server

# 配置

服务端接口程序负责接收客户端发来的请求，从数据库中获取数据并将其返回给客户端。接口程序需要访问数据库，需要在数据库中创建相应的数据表。

## 数据库表格配置

#### user

| 字段名称  | 数据类型      | 说明                                           | 是否为空   |
| --------- | ------------- | ---------------------------------------------- | ---------- |
| userid    | INT(8)        | 用户id号，每个用户（user）都有一个独一无二的id | 非空，主键 |
| name      | VARCHAR（32） | 用户名                                         | 非空       |
| password  | VARCHAR（32） | 密码                                           | 非空       |
| score     | INT（8）      | 积分                                           | 非空       |
| last_time | VARCHAR(32)   | 上一次登录时间，以mm-dd的形式存储日期字符串    | 非空       |

### quest

| 字段名称    | 数据类型      | 说明                                       | 是否为空   |
| ----------- | ------------- | ------------------------------------------ | ---------- |
| questid     | INT（8）      | 任务id号，每个感知任务拥有一个独一无二的id | 非空，主键 |
| name        | VARCHAR（32） | 感知任务名字                               | 非空       |
| description | VARCHAR（32） | 感知任务描述                               | 非空       |
| price       | INT（8）      | 购买该感知任务所对应的数据需要多少积分     | 非空       |
| reward      | INT（8）      | 完成该感知任务可获得的积分                 | 非空       |

### user_having_quest

| 字段名称 | 数据类型 | 说明                                 | 是否为空   |
| -------- | -------- | ------------------------------------ | ---------- |
| id       | INT（8） | 数据id号，每个数据拥有一个独一无的id | 主键，非空 |
| userid   | INT（8） | 用户id号                             | 外键，非空 |
| questid  | INT（8） | 感知任务信息id号                     | 外键，非空 |

### subquestion

| 字段名称    | 数据类型      | 说明                         | 是否为空   |
| ----------- | ------------- | ---------------------------- | ---------- |
| questionid  | INT（8）      | 问题id号，每个问题对应一个id | 非空，主键 |
| description | VARCHAR（32） | 问题的描述                   | 非空       |
| questid     | INT（8）      | 当前问题所属的感知任务       | 非空，外键 |

### option

| 字段名称    | 数据类型      | 说明                         | 是否为空   |
| ----------- | ------------- | ---------------------------- | ---------- |
| optionid    | INT（8）      | 选项id号，每个选项对应一个id | 非空，主键 |
| description | VARCHAR（32） | 选项的描述                   | 非空       |
| questionid  | INT（8）      | 当前选项所属的问题           | 非空，外键 |

### user_info_search

| 字段名字 | 数据类型 | 说明                             | 是否为空   |
| -------- | -------- | -------------------------------- | ---------- |
| id       | INT(8)   | 数据id号，每个数据对应一个id     | 非空，主键 |
| sex      | INT(8)   | 用户的性别：1代表男性，2代表女性 | 非空       |
| age      | INT(8)   | 用户年龄                         | 非空       |

### epidemic_info_search

| 字段名字     | 数据类型 | 说明                                 | 是否为空   |
| ------------ | -------- | ------------------------------------ | ---------- |
| id           | INT(8)   | 数据id号，每个数据对应一个id         | 非空，主键 |
| in_risk_area | INT(8)   | 是否在中高风险地区：1代表是，2代表否 | 非空       |
| temperature  | INT(8)   | 体温                                 | 非空       |
| is_fever     | INT(8)   | 是否发热：1代表是，2代表否           | 非空       |
| is_contact   | INT(8)   | 是否与确诊人群接触：1代表是，2代表否 | 非空       |

### noise_info_search

| 字段名字 | 数据类型 | 说明                         | 是否为空   |
| -------- | -------- | ---------------------------- | ---------- |
| id       | INT(8)   | 数据id号，每个数据对应一个id | 非空，主键 |
| noise_db | INT(8)   | 声音分贝                     | 非空       |

### sleep_quality_search

| 字段名字         | 数据类型 | 说明                         | 是否为空   |
| ---------------- | -------- | ---------------------------- | ---------- |
| id               | INT(8)   | 数据id号，每个数据对应一个id | 非空，主键 |
| sleep_enough     | INT(8)   | 睡眠是否足够                 | 非空       |
| sleep_time       | INT(8)   | 睡眠时间                     | 非空       |
| sleep_difficulty | INT(8)   | 入睡是否存在困难             | 非空       |

## 数据库信息配置

修改`BaseDao.java`中的databaseName、username、password属性，令它们与数据库位置和数据库登录信息匹配。

## 打包运行

将本项目打包成war包，并放到Tomcat服务器中启动。

