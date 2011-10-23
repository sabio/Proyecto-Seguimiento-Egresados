create database sse;

use sse;

CREATE TABLE dicperfil (
	idperfil INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	perfil VARCHAR( 30 ) NOT NULL ,
	activo VARCHAR( 1 ) NOT NULL DEFAULT 'S'
) ENGINE = InnoDB;


CREATE TABLE dicusuario (
	idusuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	usuario VARCHAR( 20 ) NOT NULL ,
	password VARCHAR( 50 ) NOT NULL ,
	nombre VARCHAR( 30 ) NOT NULL ,	
	apaterno VARCHAR( 30 ) NOT NULL ,
	amaterno VARCHAR( 30 ) NOT NULL ,
	activo VARCHAR( 1 ) NOT NULL DEFAULT 'S',
	idperfil INT NOT NULL ,
	email VARCHAR( 50 ) NOT NULL ,
	UNIQUE (
		usuario
	),
	constraint FK_dicusuario01 foreign key (idperfil) references dicperfil(idperfil) on delete cascade on update cascade
) ENGINE = InnoDB;


create table dicpermiso(
	idpermiso int not null auto_increment primary key,
	permiso varchar(30) not null,	
	description varchar(50) not null,
	activo varchar(1) not null default 'S'
) ENGINE = InnoDB;

create table tblpermisosperfil(
	idperfil int not null,
	idpermiso int not null,
	constraint FK_tblpermisosperfil01 foreign key (idperfil) references dicperfil(idperfil) on delete cascade,
	constraint FK_tblpermisosperfil02 foreign key (idpermiso) references dicpermiso(idpermiso) on delete cascade
	
) ENGINE = InnoDB;



create table dicmenu(
	idmenu int not null auto_increment primary key,
	menu varchar(30) not null,
	orden int not null,
	nivel int not null,
	activo varchar(1) not null default 'S',
	idmenupadre int,
	url varchar(40),
	constraint FK_dicmenu01 foreign key (idmenupadre) references dicmenu(idmenu) on delete restrict 
) ENGINE = InnoDB;

create table tblmenupermiso(
	idpermiso int not null,
	idmenu int not null,
	constraint FK_tblmenupermiso01 foreign key(idpermiso) references dicpermiso(idpermiso) on delete cascade,
	constraint FK_tblmenupermiso02 foreign key(idmenu) references dicmenu(idmenu) on delete cascade
) ENGINE = InnoDB;


create table dicpregunta(
	idpregunta int not null  auto_increment primary key,
	pregunta varchar(50) not null,
	activo varchar(1) not null default 'S' CHECK (activo = 'S' or activo = 'N')
) ENGINE = InnoDB;








/* Insertando algo de informacion */

/* Insertando el perfil administrador y un usuario llamado Armando */
INSERT INTO dicperfil VALUES (1, 'Administrador', 'S');
INSERT INTO dicusuario VALUES (1, 'armando', md5('hola'), 'Armando Jesus', 'Gomez', 'Parra', 'S', 1, 'armandojpr@yahoo.com');


/* insertando los menus */
insert into dicmenu
(idmenu,menu,orden,nivel)
values
(1,'Configuracion',1,1);

insert into dicmenu
(idmenu,menu,orden,nivel)
values
(2,'Operacion',2,1);

insert into dicmenu
(idmenu,menu,orden,nivel,idmenupadre)
values
(3,'Cuestionarios',1,2,1);

insert into dicmenu
(idmenu,menu,orden,nivel,idmenupadre,url)
values
(4,'Preguntas',2,2,1,'listadoPreguntas.run');


/* insertando los permisos */
insert into dicpermiso
(idpermiso,permiso,description)
values
(1,'Listado de cuestionarios','Permiso para poder visualizar el listado de cuestionarios');

insert into dicpermiso
(idpermiso,permiso,description)
values
(2,'Listado de preguntas','Permiso para poder visualizar el listado de preguntas');


/* Insertando las relaciones entre los menus y los permisos */
insert into tblmenupermiso
(idpermiso,idmenu)
values
(1,3);

insert into tblmenupermiso
(idpermiso,idmenu)
values
(1,1);

insert into tblmenupermiso
(idpermiso,idmenu)
values
(2,2);

insert into tblmenupermiso
(idpermiso,idmenu)
values
(1,2);

insert into tblmenupermiso
(idpermiso,idmenu)
values
(1,4);

/* insertando los permisos que tiene el perfil de administrados*/
insert into tblpermisosperfil
(idperfil,idpermiso)
values
(1,1);

insert into tblpermisosperfil
(idperfil,idpermiso)
values
(1,2);

