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
	amaterno VARCHAR( 30 ),
	activo VARCHAR( 1 ) NOT NULL DEFAULT 'S',	
	email VARCHAR( 50 ) NOT NULL ,
	UNIQUE (
		usuario
	)
	
) ENGINE = InnoDB;

create table dicadministrativo(
	idusuario INT NOT NULL,
	idperfil INT NOT NULL ,	
	constraint FK_dicadministrativo01 foreign key (idusuario) references dicusuario(idusuario)  on delete cascade on update cascade,
	constraint FK_dicadministrativo02 foreign key (idperfil) references dicperfil(idperfil) on delete cascade on update cascade	
) ENGINE = InnoDB;

create table dicgruposalumno(
	idgrupoalumno int not null AUTO_INCREMENT PRIMARY KEY ,
	grupoalumno varchar(30) not null,
	unique(grupoalumno)
) ENGINE = InnoDB;

create table dicalumno(
	idusuario INT NOT NULL,		
	/* porcentajecreditos float, */
	idgrupoalumno int not null,
	unique(idusuario),
	constraint FK_dicalumno01 foreign key (idusuario) references dicusuario(idusuario)  on delete cascade on update cascade,
	constraint FK_dicalumno02 foreign key (idgrupoalumno) references dicgruposalumno(idgrupoalumno)  on delete cascade on update cascade
) ENGINE = InnoDB;





create table tblgruposalumno(
	idusuario INT NOT NULL,
	idgrupoalumno int not null,
	constraint FK_tblgruposalumno01 foreign key (idusuario) references dicusuario(idusuario)  on delete cascade on update cascade,
	constraint FK_tblgruposalumno02 foreign key (idgrupoalumno) references dicgruposalumno(idgrupoalumno)  on delete cascade on update cascade	
)  ENGINE = InnoDB;


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

create table dictipopregunta(
	idtipopregunta int not null auto_increment primary key,
	tipopregunta varchar(50) not null	
) ENGINE = InnoDB;

create table dicindicador(
    idindicador int not null  auto_increment primary key,
    indicador   varchar(50) not null,
    activo varchar(1) not null default 'S' CHECK (activo = 'S' or activo = 'N')    
) ENGINE= InnoDB;

create table dicpregunta(
	idpregunta int not null  auto_increment primary key,
	pregunta varchar(50) not null,
	idtipopregunta int not null,
	idindicador int,
	activo varchar(1) not null default 'S' CHECK (activo = 'S' or activo = 'N'),
	constraint FK_dicpregunta01 foreign key(idtipopregunta) references dictipopregunta(idtipopregunta) on delete cascade,
	constraint FK_dicpregunta02 foreign key(idindicador) references dicindicador(idindicador) on delete cascade
) ENGINE = InnoDB;


create table diccuestionario(
	idcuestionario int not null  auto_increment primary key,
	cuestionario varchar(50) not null,
	activo varchar(1) not null default 'S' CHECK (activo = 'S' or activo = 'N')
) ENGINE = InnoDB;

create table tblcuestionariopreguntas(
	idcuestionario int not null,
	idpregunta int not null,
	orden int not null,
	constraint FK_tblcuestionariopreguntas01 foreign key (idcuestionario) references diccuestionario(idcuestionario) on delete cascade,
	constraint FK_tblcuestionariopreguntas02 foreign key (idpregunta) references dicpregunta(idpregunta) on delete cascade
);






/* ========================================Insertando algo de informacion================================ */

/* Insertando el perfil administrador y un usuario llamado Armando */
INSERT INTO dicperfil VALUES (1, 'Administrador', 'S');
INSERT INTO dicusuario VALUES (1, 'armando', md5('hola'), 'Armando Jesus', 'Gomez', 'Parra', 'S', 'armandojpr@yahoo.com');
insert into dicadministrativo values (1,1);


/* Insertando algunos alumnos */
INSERT INTO dicusuario VALUES (2, 'D0392323', md5('hola'), 'Juan Alberto', 'Gomez', 'Arredondo', 'S', 'abc@gmail.com');
INSERT INTO dicusuario VALUES (3, 'abcd', md5('hola'), 'Laura Fabiola', 'Vallin', 'Garcia', 'S', 'laura@gmail.com');
INSERT INTO dicusuario VALUES (4, 'opesdsd', md5('hola'), 'Marta', 'Reyes', 'Sandoval', 'S', 'ms@gmail.com');
INSERT INTO dicusuario VALUES (5, 'killer', md5('hola'), 'Jorge Alejandro', 'Corona', 'Perez', 'S', 'xxx@gmail.com');

insert into dicgruposalumno values (1,'Grupo 1');
insert into dicgruposalumno values (2,'Grupo 2');


insert into dicalumno values (2,1);
insert into dicalumno values (3,1);
insert into dicalumno values (4,2);
insert into dicalumno values (5,2);



/* Insertando los tipos de pregunta */
insert into dictipopregunta values (1,'Respuesta abierta');
insert into dictipopregunta values (2,'Opciones del 1 al 5');


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

insert into dicmenu
(idmenu,menu,orden,nivel,idmenupadre,url)
values
(5,'Indicadores',3,2,1,'listadoIndicadores.run');

insert into dicmenu
(idmenu,menu,orden,nivel,idmenupadre,url)
values
(6,'Alumnos',4,2,1,'listadoAlumnos.run');


/* insertando los permisos */
insert into dicpermiso
(idpermiso,permiso,description)
values
(1,'Listado de cuestionarios','Permiso para poder visualizar el listado de cuestionarios');

insert into dicpermiso
(idpermiso,permiso,description)
values
(2,'Listado de preguntas','Permiso para poder visualizar el listado de preguntas');

insert into dicpermiso
(idpermiso,permiso,description)
values
(3,'Listado de indicadores','Permiso para poder visualizar el listado de indicadores');

insert into dicpermiso
(idpermiso,permiso,description)
values
(4,'Listado de alumnos','Permiso para poder visualizar el listado de alumnos');


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

insert into tblmenupermiso
(idpermiso,idmenu)
values
(3,1);

insert into tblmenupermiso
(idpermiso,idmenu)
values
(3,5);

insert into tblmenupermiso
(idpermiso,idmenu)
values
(4,1);

insert into tblmenupermiso
(idpermiso,idmenu)
values
(4,6);

/* insertando los permisos que tiene el perfil de administrados*/
insert into tblpermisosperfil
(idperfil,idpermiso)
values
(1,1);

insert into tblpermisosperfil
(idperfil,idpermiso)
values
(1,2);

insert into tblpermisosperfil
(idperfil,idpermiso)
values
(1,3);

insert into tblpermisosperfil
(idperfil,idpermiso)
values
(1,4);

