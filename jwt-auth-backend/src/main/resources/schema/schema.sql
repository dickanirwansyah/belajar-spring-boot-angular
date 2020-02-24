create table tbl_user(
    id int auto_increment,
    name varchar(45) not null,
    username varchar(60) not null,
    email varchar(100) not null,
    password varchar(100) not null,
    created_at date not null,
    updated_at date not null,
    constraint pk_tbl_user_id primary key (id)
);

create table tbl_role(
    id int auto_increment,
    name varchar(45) not null,
    created_at date not null,
    updated_at date not null,
    constraint pk_tbl_role_id primary key (id)
);

create table tbl_user_role(
    user_id int not null,
    role_id int not null,
    constraint fk_tbl_user_role_user_id foreign key (user_id) references tbl_user(id),
    constraint fk_tbl_user_role_role_id foreign key (role_id) references tbl_role(id)
);

create table tbl_category(
    id int auto_increment primary key,
    name varchar(100),
    created_at date not null,
    updated_at date not null
);

insert into tbl_category(name, created_at, updated_at) values('siganaturelink', curdate(), curdate()),
('smartplan', curdate(),curdate());

insert into tbl_role(name, created_at, updated_at) values('ADMIN', curdate(), curdate()),
('STAFF', curdate(), curdate()), ('MANAGER', curdate(), curdate());