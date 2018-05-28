create table automa
(
  id   int auto_increment
    primary key,
  name varchar(255) not null
)
  engine = InnoDB;

create table image
(
  id     int auto_increment
    primary key,
  name   varchar(255) not null,
  source mediumblob   null
)
  engine = InnoDB;

create table state
(
  id        int auto_increment
    primary key,
  stateName varchar(255) not null,
  automa_id int          not null,
  constraint FK8johmx089qav19vgrt7097bpd
  foreign key (automa_id) references automa (id)
)
  engine = InnoDB;

create index FK8johmx089qav19vgrt7097bpd
  on state (automa_id);