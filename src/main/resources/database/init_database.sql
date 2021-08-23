create table if not exists roles(
    id bigint auto_increment primary key not null,
    name varchar(100),
    unique (name)
);

create table if not exists users(
    id bigint auto_increment primary key not null,
    username varchar(255),
    password varchar(255),
    firstName varchar(255),
    lastName varchar(255),
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