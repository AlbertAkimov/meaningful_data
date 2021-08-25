create table if not exists roles(
    id bigint auto_increment primary key not null,
    name varchar(100),
    unique (name)
);

create table if not exists users(
    id bigint auto_increment primary key not null,
    username varchar(255),
    password varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255),
    created timestamp default CURRENT_TIMESTAMP,
    updated timestamp default CURRENT_TIMESTAMP,
    status varchar(25) default 'ACTIVE',
    unique(username)
);

create table if not exists users_roles(
  user_id bigint,
  role_id bigint,

  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id),
  UNIQUE (user_id, role_id)
);

create table if not exists meaningful_data_type(
    id bigint primary key auto_increment,
    name varchar(1024) not null ,
    status varchar(25) default 'ACTIVE'
);

create table if not exists meaningful_data_owner(
    id bigint primary key auto_increment,
    name varchar(1024) not null,
    inn int not null,
    status varchar(25) default 'ACTIVE'
);

create table if not exists meaningful_data(
    id bigint primary key auto_increment,
    id_type bigint not null,
    id_own bigint not null,
    access varchar(1024) not null,
    status varchar(25) not null,

    foreign key (id_type) references meaningful_data_type(id),
    foreign key (id_own) references meaningful_data_owner(id)
);