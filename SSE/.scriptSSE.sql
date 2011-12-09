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

create table dicgrupoalumnos(
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
	constraint FK_dicalumno02 foreign key (idgrupoalumno) references dicgrupoalumnos(idgrupoalumno)  on delete cascade on update cascade
) ENGINE = InnoDB;




/***TAL VEZ NO SIRVA ESTA TABLA**/
create table tblgruposalumno(
	idusuario INT NOT NULL,
	idgrupoalumno int not null,
	constraint FK_tblgruposalumno01 foreign key (idusuario) references dicusuario(idusuario)  on delete cascade on update cascade,
	constraint FK_tblgruposalumno02 foreign key (idgrupoalumno) references dicgrupoalumnos(idgrupoalumno)  on delete cascade on update cascade	
)  ENGINE = InnoDB;


create table dicpermiso(
	idpermiso int not null auto_increment primary key,
	permiso varchar(30) not null,	
	description varchar(100) not null,
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
	menu varchar(70) not null,
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
	pregunta varchar(300) not null,
	idtipopregunta int not null,
	idindicador int,
	activo varchar(1) not null default 'S' CHECK (activo = 'S' or activo = 'N'),
	constraint FK_dicpregunta01 foreign key(idtipopregunta) references dictipopregunta(idtipopregunta) on delete cascade,
	constraint FK_dicpregunta02 foreign key(idindicador) references dicindicador(idindicador) on delete cascade
) ENGINE = InnoDB;


create table diccuestionario(
	idcuestionario int not null  auto_increment primary key,
	cuestionario varchar(300) not null,
	activo varchar(1) not null default 'S' CHECK (activo = 'S' or activo = 'N')
) ENGINE = InnoDB;

create table tblcuestionariopreguntas(
	idcuestionario int not null,
	idpregunta int not null,
	orden int not null,
	constraint FK_tblcuestionariopreguntas01 foreign key (idcuestionario) references diccuestionario(idcuestionario) on delete cascade,
	constraint FK_tblcuestionariopreguntas02 foreign key (idpregunta) references dicpregunta(idpregunta) on delete cascade
) ENGINE = InnoDB;


create table tblasignacioncuestionario(
    idasignacioncuestionario int not null auto_increment primary key,
    idcuestionario int not null,
    idgrupoalumno int not null,
    fechainicio timestamp not null,
    fechafin timestamp not null,
    activo varchar(1) not null default 'S' CHECK (activo = 'S' or activo = 'N'),
    constraint FK_tblasignacioncuestionario01 foreign key (idcuestionario) references diccuestionario(idcuestionario) on delete cascade,
    constraint FK_tblasignacioncuestionario02 foreign key (idgrupoalumno) references dicgrupoalumnos(idgrupoalumno) on delete cascade
) ENGINE = InnoDB;


create table tblaplicacioncuestionario(
	idaplicacioncuestionario int not null auto_increment primary key,
	idasignacioncuestionario int not null,
	idusuario int not null,
	fechainicio timestamp not null,
	fechafin timestamp,
	constraint FK_tblaplicacioncuestionario01 foreign key (idasignacioncuestionario) references tblasignacioncuestionario(idasignacioncuestionario) on delete cascade,
	constraint FK_tblaplicacioncuestionario02 foreign key (idusuario) references dicusuario(idusuario) on delete cascade
) ENGINE = InnoDB;

create table tblrespuesta(
	idaplicacioncuestionario int not null,
	idpregunta  int not null,
	respuestaString varchar(500),
	respuestaInt int,
    constraint PK_tblrespuesta01 primary key(idaplicacioncuestionario,idpregunta),
	constraint FK_tblrespuesta01 foreign key (idaplicacioncuestionario) references tblaplicacioncuestionario(idaplicacioncuestionario) on delete cascade,
	constraint FK_tblrespuesta02 foreign key (idpregunta) references dicpregunta(idpregunta) on delete cascade
) ENGINE = InnoDB;


/* ========================================Insertando algo de informacion================================ */

/* Insertando el perfil administrador y un usuario llamado Armando */
INSERT INTO dicperfil VALUES (1, 'Administrador', 'S');
INSERT INTO dicusuario VALUES (1, 'admin', md5('hola'), 'Armando Jesus', 'Gomez', 'Parra', 'S', 'armandojpr@yahoo.com');
insert into dicadministrativo values (1,1);


/* Insertando algunos alumnos */
INSERT INTO dicusuario VALUES (2, 'D0392323', md5('hola'), 'Juan Alberto', 'Gomez', 'Arredondo', 'S', 'abc@gmail.com');
INSERT INTO dicusuario VALUES (3, 'abcd', md5('hola'), 'Laura Fabiola', 'Vallin', 'Garcia', 'S', 'laura@gmail.com');
INSERT INTO dicusuario VALUES (4, 'marta', md5('hola'), 'Marta', 'Reyes', 'Sandoval', 'S', 'ms@gmail.com');
INSERT INTO dicusuario VALUES (5, 'jorge', md5('hola'), 'Jorge Alejandro', 'Corona', 'Perez', 'S', 'xxx@gmail.com');
INSERT INTO dicusuario VALUES (6, 'Patricia', md5('hola'), 'Patricia Alejandra', 'Corona', 'Perez', 'S', 'patri@gmail.com');
INSERT INTO dicusuario VALUES (7, 'Damian', md5('hola'), 'Damian', 'Sena', 'Garcua', 'S', 'dami_123@yahoo.com');
INSERT INTO dicusuario VALUES (8, 'D049036529', md5('hola'), 'Jose', 'Carmona', 'Parra', 'S', 'buger@hotmail.com');
INSERT INTO dicusuario VALUES (9, 'Samu', md5('hola'), 'Samule', 'Pedrosa', 'Quintero', 'S', 'samu@gmail.com');
INSERT INTO dicusuario VALUES (10, '129878923', md5('hola'), 'Daniela', 'Garcia', 'Reyes', 'S', 'kuteme@gmail.com');
INSERT INTO dicusuario VALUES (11, 'otruga', md5('hola'), 'Maciel', 'Gomez', 'Pinto', 'S', 'otruga@msn.com');
INSERT INTO dicusuario VALUES (12, '12122342', md5('hola'), 'Quetzal', 'Esparza', 'Quines', 'S', 'quet_abc@msn.com');
INSERT INTO dicusuario VALUES (13, 'arthur', md5('hola'), 'Arthur', 'Parker', '', 'S', 'pp_spider@yahoo.com');

insert into dicgrupoalumnos values (1,'Grupo 1');
insert into dicgrupoalumnos values (2,'Grupo 2');


insert into dicalumno values (2,1);
insert into dicalumno values (3,1);
insert into dicalumno values (4,1);
insert into dicalumno values (5,2);
insert into dicalumno values (6,2);
insert into dicalumno values (7,2);
insert into dicalumno values (8,2);
insert into dicalumno values (9,1);
insert into dicalumno values (10,1);
insert into dicalumno values (11,1);
insert into dicalumno values (12,1);
insert into dicalumno values (13,2);



/* Insertando los tipos de pregunta */
insert into dictipopregunta values (1,'Respuesta abierta');
insert into dictipopregunta values (2,'Opciones del 0 al 5');
insert into dictipopregunta values (3,'Opciones Si y No');

/* Insertando cuestionarios y preguntas*/
insert into diccuestionario values (1,'Cuestionario Sobre la materia de programacion avanzada','S');


insert into dicindicador values
(1,'Satisfaccion con los profesores','S');
insert into dicindicador values
(2,'Satisfaccion con las instalaciones','S');
insert into dicindicador values
(3,'Satisfaccion con el programa academico','S');


insert into dicpregunta values
(1,'Se cumplió con el programa de clases? (Siendo 5 la calificación más alta y 1 la más baja)', 2,1,'S');

insert into dicpregunta values
(2,'Se le dió conocimiento al inicio de curso acerca del programa de estudios', 3,1,'S');

insert into dicpregunta values
(3,'Se le dió conocimiento al inicio de curso acerca del metodo de evaluación', 3,1,'S');

insert into dicpregunta values
(4,'Se aclararon todas las dudas surgidas en clase (Siendo 5 la calificación más alta y 1 la más baja)', 2,1,'S');


insert into dicpregunta values
(5,'Se contaba con material suficiente de investigacion y referencia (Siendo 5 la calificación más alta y 1 la más baja)', 2,2,'S');

insert into dicpregunta values
(6,'Se contaba con aulas limpias, ordenadas y bien iluminadas (Siendo 5 la calificación más alta y 1 la más baja)', 2,2,'S');


insert into dicpregunta values
(7,'Indique su nivel de satisfacción acerca de los temas impartidos (Siendo 5 la calificación más alta y 1 la más baja)', 3,3,'S');

insert into dicpregunta values
(8,'¿Consideras que la materia de Programacion Avanzada es indispensable para una formacion academica completa en el Posgrado?', 2,3,'S');

insert into dicpregunta values
(9,'Exponga abiertamente que cambiaria usted para mejorar, si es el caso, algun aspecto de la clase', 1,null,'S');

insert into tblcuestionariopreguntas values
(1,1,1);
insert into tblcuestionariopreguntas values
(1,2,2);
insert into tblcuestionariopreguntas values
(1,3,3);
insert into tblcuestionariopreguntas values
(1,4,4);
insert into tblcuestionariopreguntas values
(1,5,5);
insert into tblcuestionariopreguntas values
(1,6,6);
insert into tblcuestionariopreguntas values
(1,7,7);
insert into tblcuestionariopreguntas values
(1,8,8);
insert into tblcuestionariopreguntas values
(1,9,9);







/* Insertando asignaciones de cuestionario de pruebas */
insert into tblasignacioncuestionario values (1,1,2,now(), STR_TO_DATE( '2011/12/31 10:00 am', '%Y/%m/%d %I:%i %p' ), 'S');






/* insertando los menus */
insert into dicmenu
(idmenu,menu,orden,nivel)
values
(1,'Encuestas',1,1);

	insert into dicmenu
	(idmenu,menu,orden,nivel,idmenupadre,url)
	values
	(4,'Indicadores',1,2,1,'listadoIndicadores.run');
	
	insert into dicmenu
	(idmenu,menu,orden,nivel,idmenupadre,url)
	values
	(5,'Preguntas',2,2,1,'listadoPreguntas.run');
	
	insert into dicmenu
	(idmenu,menu,orden,nivel,idmenupadre,url)
	values
	(6,'Cuestionarios',3,2,1,'listadoCuestionarios.run');

insert into dicmenu
(idmenu,menu,orden,nivel)
values
(2,'Alumnos',2,1);

	insert into dicmenu
	(idmenu,menu,orden,nivel,idmenupadre,url)
	values
	(7,'Alumnos',2,2,2,'listadoAlumnos.run');
	
	insert into dicmenu
	(idmenu,menu,orden,nivel,idmenupadre,url)
	values
	(8,'Grupos de alumnos',1,2,2,'listadoGruposAlumnos.run');

insert into dicmenu
(idmenu,menu,orden,nivel)
values
(3,'Operacion',3,1);

	insert into dicmenu
	(idmenu,menu,orden,nivel,idmenupadre,url)
	values
	(9,'Asignacion de Cuestionarios',1,2,3,'listadoAsignacionCuestionarios.run');
	
	insert into dicmenu
	(idmenu,menu,orden,nivel,idmenupadre,url)
	values
	(10,'Consulta de Resultados',2,2,3,'resultadoDeAsignacion.run');




/* insertando los permisos */
insert into dicpermiso
(idpermiso,permiso,description)
values
(1,'Listado de indicadores','Permiso para poder visualizar el listado de indicadores');
insert into dicpermiso
(idpermiso,permiso,description)
values
(2,'Listado de preguntas','Permiso para poder visualizar el listado de preguntas');
insert into dicpermiso
(idpermiso,permiso,description)
values
(3,'Listado de cuestionarios','Permiso para poder visualizar el listado de cuestionarios');
insert into dicpermiso
(idpermiso,permiso,description)
values
(4,'Listado de alumnos','Permiso para poder visualizar el listado de alumnos');
insert into dicpermiso
(idpermiso,permiso,description)
values
(5,'Listado de grupos de alumnos','Permiso para poder visualizar el listado de grupos de alumnos');
insert into dicpermiso
(idpermiso,permiso,description)
values
(6,'Asignacion de Cuestionarios','Permiso para poder visualizar la asignacion de cuestionarios');
insert into dicpermiso
(idpermiso,permiso,description)
values
(7,'Resultados','Permiso para poder consultar resultados de las encuestas');


/* Insertando las relaciones entre los menus y los permisos */
insert into tblmenupermiso
(idpermiso,idmenu)
values
(1,4);
insert into tblmenupermiso
(idpermiso,idmenu)
values
(1,1);
insert into tblmenupermiso
(idpermiso,idmenu)
values
(2,5);
insert into tblmenupermiso
(idpermiso,idmenu)
values
(2,1);
insert into tblmenupermiso
(idpermiso,idmenu)
values
(3,6);
insert into tblmenupermiso
(idpermiso,idmenu)
values
(3,1);
insert into tblmenupermiso
(idpermiso,idmenu)
values
(4,7);
insert into tblmenupermiso
(idpermiso,idmenu)
values
(4,2);
insert into tblmenupermiso
(idpermiso,idmenu)
values
(5,8);
insert into tblmenupermiso
(idpermiso,idmenu)
values
(5,2);
insert into tblmenupermiso
(idpermiso,idmenu)
values
(6,9);
insert into tblmenupermiso
(idpermiso,idmenu)
values
(6,3);
insert into tblmenupermiso
(idpermiso,idmenu)
values
(7,10);
insert into tblmenupermiso
(idpermiso,idmenu)
values
(7,3);



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

insert into tblpermisosperfil
(idperfil,idpermiso)
values
(1,5);

insert into tblpermisosperfil
(idperfil,idpermiso)
values
(1,6);

insert into tblpermisosperfil
(idperfil,idpermiso)
values
(1,7);

