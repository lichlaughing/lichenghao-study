-- 演示基础CURD
create table tb_user(
	id int(10) not null ,
	username varchar(60) not null,
	age int(3) default 0,
	birthday timestamp,
	primary key (id)

)ENGINE=INNODB DEFAULT CHARSET=utf8;

insert into tb_user(id,username,age,birthday) values(1,'tom',20,CURRENT_TIMESTAMP);
insert into tb_user(id,username,age,birthday) values(2,'jerry',30,CURRENT_TIMESTAMP);

-- 老师学生表用于1-N;N-1映射
CREATE TABLE teacher (
  id INT(10) NOT NULL,
  name VARCHAR(30) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO teacher(id, name) VALUES (1, '秦老师');

CREATE TABLE student (
  id INT(10) NOT NULL,
  name VARCHAR(30) DEFAULT NULL,
  tid INT(10) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fktid (tid),
  CONSTRAINT fktid FOREIGN KEY (tid) REFERENCES teacher (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('1', '小明', '1');
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('2', '小红', '1');
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('3', '小张', '1');
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('4', '小李', '1');
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('5', '小王', '1');

-- 演示动态SQL
CREATE TABLE blog(
	id VARCHAR(50) NOT NULL COMMENT '博客id',
	title VARCHAR(100) NOT NULL COMMENT '博客标题',
	author VARCHAR(30) NOT NULL COMMENT '博客作者',
	create_time DATETIME NOT NULL COMMENT '创建时间',
	views INT(30) NOT NULL COMMENT '浏览量'
)ENGINE=INNODB DEFAULT CHARSET=utf8