DROP TABLE t_user;
CREATE TABLE t_user (
    id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(20) NOT NULL,
    meter_id nvarchar(20),
    password varchar(40),
    joinDate datetime
);


DROP TABLE t_user_detail;
CREATE TABLE t_user_detail (
    id int PRIMARY KEY,
    last_login_ip varchar(20),
    last_login_time datetime
);