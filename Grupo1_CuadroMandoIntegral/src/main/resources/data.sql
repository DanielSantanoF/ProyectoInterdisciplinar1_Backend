DROP SEQUENCE HIBERNATE_SEQUENCE;
CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 10000;
insert into COLEGIO (ID, NOMBRE_COLEGIO) values (1, 'Salesianos Triana');
insert into COLEGIO (ID, NOMBRE_COLEGIO) values (2, 'Santan Joaquina de Vedruna');
insert into USER_ENTITY (ID, CREATED_AT, EMAIL, LAST_PASSWORD_CHANGED_AT, PASSWORD, USERNAME, COLEGIO_ID) values (1, null, 'usuario@usuario.com', null, '1234', 'usuario', 1);
insert into USER_ENTITY_ROLE (USER_ENTITY_ID, ROLE) values (1, 'USER');
insert into USER_ENTITY (ID, CREATED_AT, EMAIL, LAST_PASSWORD_CHANGED_AT, PASSWORD, USERNAME, COLEGIO_ID) values (2, null, 'admin@admin.com', null, '1234', 'admin', 1);
insert into USER_ENTITY_ROLE (USER_ENTITY_ID, ROLE) values (2, 'ADMIN');
insert into USER_ENTITY (ID, CREATED_AT, EMAIL, LAST_PASSWORD_CHANGED_AT, PASSWORD, USERNAME, COLEGIO_ID) values (3, null, 'superAdmin@superAdmin.com', null, '1234', 'superAdmin', 1);



insert into USER_ENTITY_ROLE (USER_ENTITY_ID, ROLE) values (3, 'SUPERADMIN');

insert into CURSO_ACADEMICO  (ID, NOMBRE_CURSO_ACADEMICO) values (1, '2018-2019');
insert into CURSO_ACADEMICO  (ID, NOMBRE_CURSO_ACADEMICO) values (2, '2019-2020');
insert into CURSO_ACADEMICO  (ID, NOMBRE_CURSO_ACADEMICO) values (3, '2018-2019');
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (3, 'INFANTIL', 1, 1, null,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (4, 'PRIMARIA', 2, 1, null,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (5, 'SECUNDARIA', 3, 1, null,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (6, 'BACHILLERATO', 4, 1, null,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (7, 'FORMACION PROFESIONAL', 5, 1, null,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (8, '2º CICLO INFANTIL', 6, 1, 3,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (9, '1º CICLO PRIMARIA', 7, 1, 4,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (10, '2º CICLO PRIMARIA', 8, 1, 4,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (11, '3º CICLO PRIMARIA', 9, 1, 4,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (12, '1º CICLO ESO', 10, 1, 5,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (13, '2º CICLO ESO', 11, 1, 5,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (14, '3º CICLO ESO', 12, 1, 5,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (15, '4º CICLO ESO', 13, 1, 5,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (16, '1º CICLO BACHILLER', 14, 1, 6,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (17, '2º CICLO BACHILLER', 15, 1, 6,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (18, 'FORMACIÓN PROFESIONAL BÁSICA', 16, 1, 7,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (19, 'CICLOS FORMATIVOS DE GRADO MEDIO', 17, 1, 7,2);
insert into ETAPA (ID, NOMBRE_SUB_ETAPA, PESO, COLEGIO_ID, ETAPA_ID, CURSO_ACADEMICO_ID) values (20, 'CICLOS FORMATIVOS DE GRADO SUPERIOR', 18, 1, 7,2);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (21, 'Tres Años', 19, 8);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (22, 'Cuatro Años', 20, 8);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (23, 'Cinco Años', 21, 8);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (24, '1º de Educ. Prima.', 22, 9);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (25, '2º de Educ. Prima.', 23, 9);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (26, '3º de Educ. Prima.', 24, 10);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (27, '4º de Educ. Prima.', 25, 10);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (28, '5º de Educ. Prima.', 26, 11);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (29, '6º de Educ. Prima.', 27, 11);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (30, '1º de E.S.O.', 28, 12);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (31, '2º de E.S.O.', 29, 13);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (32, '3º de E.S.O.', 30, 14);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (33, '4º de E.S.O.', 31, 15);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (34, 'PRIMERO BACHILLERATO', 32, 16);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (35, 'SEGUNDO BACHILLERATO', 33, 17);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (36, '1º de F.P.B. (Electricidad y Electrónica)', 34, 18);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (37, '2º de F.P.B. (Electricidad y Electrónica)', 35, 18);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (38, '1º F.P.I.G.M. (Actividades Comerciales)', 36, 19);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (39, '1º F.P.I.G.M. (Gestión Administrativa)', 37, 19);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (40, '1º F.P.I.G.M. (Instalaciones de Telecomunicaciones)', 38, 19);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (41, '1º F.P.I.G.M. (Instalaciones Eléctricas y Automáticas)', 39, 19);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (42, '2º F.P.I.G.M. (Actividades Comerciales)', 40, 19);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (43, '2º F.P.I.G.M. (Gestión Administrativa)', 41, 19);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (44, '2º F.P.I.G.M. (Instalaciones de Telecomunicaciones)', 42, 19);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (45, '2º F.P.I.G.M. (Instalaciones Eléctricas y Automáticas)', 43, 19);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (46, '1º F.P.I.G.S. (Administración y Finanzas)', 44, 20);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (47, '1º F.P.I.G.S. (Desarrollo de Aplicaciones Multiplataforma)', 45, 20);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (48, '2º F.P.I.G.S. (Administración y Finanzas)', 46, 20);
insert into CURSO (ID, NOMBRE_CURSO, PESO, ETAPA_ID) values (49, '2º F.P.I.G.S. (Desarrollo de Aplicaciones Multiplataforma)', 47, 20);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (50, 'E.Inf. 5.A', 48, 23);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (51, 'E.Inf. 5.B', 49, 23);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (52, 'E.Inf. 4.A', 50, 22);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (53, 'E.Inf. 4.B', 51, 22);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (54, 'E.Inf. 3.A', 52, 21);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (55, 'E.Inf. 3.B', 53, 21);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (56, 'EPO 1ºA', 54, 24);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (57, 'EPO 1ºB', 55, 24);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (58, 'EPO 2ºA', 56, 25);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (59, 'EPO 2ºB', 57, 25);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (60, 'EPO 3ºA', 58, 26);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (61, 'EPO 3ºB', 59, 26);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (62, 'EPO 4ºA', 60, 27);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (63, 'EPO 4ºB', 61, 27);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (64, 'EPO 5ºA', 62, 28);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (65, 'EPO 5ºB', 63, 28);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (66, 'EPO 6ºA', 64, 29);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (67, 'EPO 6ºB', 65, 29);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (68, 'ESO 1ºA', 66, 30);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (69, 'ESO 1ºB', 67, 30);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (70, 'ESO 1ºC', 68, 30);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (71, 'ESO 2ºA', 69, 31);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (72, 'ESO 2ºB', 70, 31);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (73, 'ESO 2ºC', 71, 31);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (74, 'ESO 3ºA', 72, 32);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (75, 'ESO 3ºB', 73, 32);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (76, 'ESO 3ºC', 74, 32);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (77, 'ESO 4ºA', 75, 33);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (78, 'ESO 4ºB', 76, 33);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (79, 'ESO 4ºC', 77, 33);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (80, '1ºA Bach', 78, 34);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (81, '1ºB Bach', 79, 34);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (82, '1ºC Bach', 80, 34);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (83, '2ºA Bach', 81, 35);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (84, '2ºB Bach', 82, 35);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (85, '2ºC Bach', 83, 35);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (86, '1º FPB', 84, 36);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (87, '2º FPB', 85, 37);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (88, '1º Comerci', 86, 38);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (89, '1º Gestión', 87, 39);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (90, '1º ITelec', 88, 40);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (91, '1º Ins Aut', 89, 41);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (92, '2º Comerci', 90, 42);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (93, '2º Gestión', 91, 43);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (94, '2º ITelec', 92, 44);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (95, '2º Ins Aut', 93, 45);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (96, '1º Adm-Fin', 94, 46);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (97, '1 D.A.Mult', 95, 47);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (98, '2º Adm-Fin', 96, 48);
insert into UNIDAD (ID, NOMBRE_UNIDAD, PESO, CURSO_ID) values (99, '2 D.A.Mult', 97, 49);
insert into PROCESO (ID,NOMBRE_PROCESO,PESO,TIPO) values (1,'P.C.01.ACCIÓN TUTORIAL.',1,'CLAVE');
insert into PROCESO (ID, NOMBRE_PROCESO,CURSO_ACADEMICO_ID,PESO,TIPO) values (2,'P.C. 02. ORIENTACIÓN Y ACOMPAÑAMIENTO DE ALUMNOS',2,2,'CLAVE');
insert into PROCESO (ID, NOMBRE_PROCESO,CURSO_ACADEMICO_ID,PESO,TIPO) values (3,'P.C. 04. ATENCIÓN A LA DIVERSIDAD',2,3,'CLAVE');
insert into PROCESO (ID, NOMBRE_PROCESO,CURSO_ACADEMICO_ID,PESO,TIPO) values (4,'P.C. 05. ACCIÓN DOCENTE (PROC. EDUC. EN EL AULA)',2,4,'CLAVE');
insert into PROCESO (ID, NOMBRE_PROCESO,CURSO_ACADEMICO_ID,PESO,TIPO) values (5,'P.C. 07. GESTIÓN DE CONFLICTOS DISCIPLINARES. (de la convivencia)',2,5,'CLAVE');
insert into PROCESO (ID, NOMBRE_PROCESO,CURSO_ACADEMICO_ID,PESO,TIPO) values (6,'P.C. 09. ADMISIÓN, MATRICULACIÓN Y ACOGIDA DE ALUMNOS',2,6,'CLAVE');
insert into PROCESO (ID, NOMBRE_PROCESO,CURSO_ACADEMICO_ID,PESO,TIPO) values (7,'P.C. 10. ACCIÓN PASTORAL ESCOLAR',2,7,'CLAVE');
insert into PROCESO (ID, NOMBRE_PROCESO,CURSO_ACADEMICO_ID,PESO,TIPO) values (8,'P.C. 12. IMAGEN DEL CENTRO',2,8,'CLAVE');
insert into PROCESO (ID, NOMBRE_PROCESO,CURSO_ACADEMICO_ID,PESO,TIPO) values (9,'P.C. 14. FORMACIÓN EN CENTROS DE TRABAJO.',2,9,'CLAVE');
insert into PROCESO (ID, NOMBRE_PROCESO,CURSO_ACADEMICO_ID,PESO,TIPO) values (10,'P.E. 06. MEJORA CONTINUA.',2,10,'ESTRATEGICO');


INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (1,FALSE,'IND 01.1 % Familias diferentes que se entrevistan con el tutor',1);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (2,FALSE,'IND 01.2 % alumnos diferentes entrevistados por el tutor',1);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (3,FALSE,'IND 01.3 Nº total de entrevistas realizadas por el tutor con alumnos',1);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (4,FALSE,'IND 01.4 Nº total de entrevistas realizadas por el tutor con famillias',1);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (5,FALSE,'IND 01.5 % de acciones del Plan de Acción Tutorial realizadas sobre las planificadas.',1);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (6,FALSE,'IND 01.6 % de acciones del Plan de Acción Tutorial eficaces sobre las realizadas.',1);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (7,FALSE,'IND 01.7 % Padres que asisten a las reuniones de tutoría grupal.',1);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (8,FALSE,'IND 01.8 Grado de satisfacción de los alumnos con la acción tutorial',1);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (9,FALSE,'IND 01.9 Grado de satisfacción de las familias con la acción tutorial',1);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (10,FALSE,'IND 01.10 Grado de satisfacción de los profesores con la acción tutorial',1);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (11,FALSE,'IND 01.11 Grado satisfacción profesores con las acciones tutoriales desarrolladas con los alumnos',1);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (12,FALSE,'IND 02.1 % de pruebas colectivas realizadas sobre las planificadas.',2);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (13,FALSE,'IND 02.2 % de acciones de Orientación profesional–vocacional realizadas sobre las planificadas',2);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (14,FALSE,'IND 02.3 Grado de satisfacción de los alumnos con la orientación',2);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (15,FALSE,'IND 02.4 Grado de Satisfacción de Familias con la Orientación',2);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (16,FALSE,'IND 02.5 Grado de Satisfacción de los profesores con la Orientación',2);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (17,FALSE,'IND 04.1 Porcentaje áreas-asignaturas-materias aprobadas de todo el alumnado con Adaptación Curricular No Significativa',3);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (18,FALSE,'IND 04.2 Porcentaje áreas-asignaturas-materias aprobadas de todo el alumnado con Adaptación Curricular Significativa',3);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (19,FALSE,'IND 04.3 Porcentaje áreas-asignaturas-materias aprobadas de todo el alumnado con refuerzo o apoyo.',3);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (20,FALSE,'IND 04.4 Porcentaje otros programas valorados como eficaces.',3);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (21,FALSE,'IND 04.5 % materias pendientes recuperadas (EP, ESO, Bach y FPB)',3);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (22,FALSE,'IND 04.6 % alumnos repetidores que promocionan sin imperativo legal',3);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (23,FALSE,'IND 04.7 Grado de satisfacción de los alumnos con las medidas de atención a la diversidad',3);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (24,FALSE,'IND 04.8 Grado de satisfacción de las familias con las medidas de atención a la diversidad',3);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (25,FALSE,'IND 04.9 Grado de satisfacción profesores con la Atención a la Diversidad',3);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (26,FALSE,'IND 05.1 % alumnos que en este momento promocionarían de curso o titularían (imperativo legal)',4);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (27,FALSE,'IND 05.2 % alumnos que en este momento promocionarían (basado en el nº real de suspensos)',4);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (28,FALSE,'IND 05.3 % de alumnos sin suspensos',4);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (29,FALSE,'IND 05.4 % alumnos con 3 o más suspensos',4);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (30,FALSE,'IND 05.5 Porcentaje de alumnos que aprueban la PAU en Junio con respecto a los matriculados en 2º BACH',4);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (31,FALSE,'IND 05.6 Nota media alumnos PAU (1ª convocatoria)',4);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (32,FALSE,'IND 05.7 Diferencia entre media PAU y media Bachillerato (1ª convocatoria)',4);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (33,FALSE,'IND 05.8 % de actividades complementarias programadas sobre las realizadas.',4);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (34,FALSE,'IND 05.9 Grado de satisfacción de los alumnos con la acción docente',4);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (35,FALSE,'IND 05.10 Grado de satisfacción de las familias con la acción docente',4);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (36,FALSE,'IND 05.11 Grado de satisfacción de los profesores con la acción docente',4);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (37,FALSE,'IND 05.12 Grado de satisfacción de los alumnos con las actividades complementarias',4);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (38,FALSE,'IND 05.13 Grado de satisfacción del profesorado con las actividades complementarias',4);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (39,FALSE,'IND 07.1 Nº de alumnos diferentes suspendidos del derecho de asistencia al centro',5);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (40,FALSE,'IND 07.2 % de alumnos diferentes suspendidos del derecho de asistencia al centro',5);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (41,FALSE,'IND 07.3 % alumnos diferentes con ausencias injustificadas en 2 o más días (en el mismo trimestre).',5);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (42,FALSE,'IND 07.4 % alumnos diferentes con impuntualidades injustificadas (Se contabilizará a partir de 3 impuntualidades en el mismo trimestre)',5);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (43,FALSE,'IND 07.5 Grado de satisfacción de los alumnos con la Gestión de Conflictos',5);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (44,FALSE,'IND 07.6 Grado de satisfacción de las Familias con la Gestión de Conflictos',5);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (45,FALSE,'IND 07.7 Grado de satisfacción del profesorado con la Gestión de Conflictos',5);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (46,FALSE,'IND 09.1 % de ocupación (n.º alumnos/ratio)',6);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (47,FALSE,'IND 09.2 Porcentaje de solicitudes recibidas como primera opción respecto a plazas ofertadas',6);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (48,FALSE,'IND 09.3 Nº solicitudes recibidas (por curso)',6);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (49,FALSE,'IND 09.4 Nº de alumnos que se dan de baja ',6);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (50,FALSE,'IND 09.5 Grado de satisfacción de alumnos de nuevo ingreso con la Admisión, Matriculación y Acogida de alumnos',6);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (51,FALSE,'IND 09.6 Grado de satisfacción de las Familias de nuevo ingreso con la Admisión, Matriculación y Acogida de alumnos',6);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (52,FALSE,'IND 10.1 % alumnos que participan en actividades voluntarias de pastoral escolar (celebraciones, retiros, convivencias, …)',7);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (53,FALSE,'IND 10.2 Grado de satisfacción de los alumnos con la Pastoral',7);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (54,FALSE,'IND 10.3 Grado de satisfacción de las Familias con la Pastoral',7);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (55,FALSE,'IND 10.4 Grado de satisfacción del profesorado con la Pastoral',7);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (56,FALSE,'IND 12.1 Nº Reconocimientos / premios concedidos por parte de entidades externas' ,8);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (57,FALSE,'IND 12.2 Nº de ocasiones en los que el centro aparece en los medios de comunicación (medios externos a Salesianos)',8);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (58,FALSE,'IND 12.3 Número de actividades en las que el Centro colabora con otras instituciones externas',8);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (59,FALSE,'IND 12.4 Grado de satisfacción de los alumnos con la Imagen del Centro',8);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (60,FALSE,'IND 12.5 Grado de satisfacción de las Familias con la Imagen del Centro',8);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (61,FALSE,'IND 12.6 Grado de satisfacción del profesorado con la Imagen del Centro',8);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (62,FALSE,'IND 12.7 Grado de satisfacción de los padres con la Plataforma "QE Escuela-Familia"',8);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (63,FALSE,'IND 14.1 % de alumnos matriculados en segundo curso que realizan el módulo de FCT',9);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (64,FALSE,'IND 14.2 % de alumnos que aprueban con apto el módulo de FCT sobre los que han realizado la FCT.',9);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (65,FALSE,'IND 14.3 % de alumnos contratados en empresas tras haber terminado la FCT',9);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (66,FALSE,'IND 14.4 Grado de satisfaccion de los alumnos con la FCT',9);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (67,FALSE,'IND 14.5 Grado de satisfacción de las empresas con la FCT',9);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (68,FALSE,'IND 06.1 E Mejora en indicadores medición objetiva definida en el PSM' ,10);
INSERT INTO INDICADOR (ID,ES_PORCENTAJE,NOMBRE_INDICADOR,PROCESO_ID) VALUES (69,FALSE,'IND 06.2 E Mejora en indicadores de la satisfacción del cliente',10);

INSERT INTO INDICADOR_UNIDAD (ID) VALUES(1);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(2);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(3);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(4);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(5);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(6);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(7);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(8);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(9);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(10);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(11);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(12);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(13);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(14);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(15);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(16);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(17);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(18);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(19);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(20);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(21);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(22);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(23);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(24);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(25);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(26);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(27);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(28);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(29);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(30);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(31);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(32);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(33);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(34);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(35);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(36);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(37);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(38);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(39);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(40);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(41);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(42);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(43);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(44);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(45);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(46);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(47);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(48);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(49);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(50);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(51);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(52);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(53);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(54);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(55);
INSERT INTO INDICADOR_CENTRO (ID) VALUES(56);
INSERT INTO INDICADOR_CENTRO (ID) VALUES(57);
INSERT INTO INDICADOR_CENTRO (ID) VALUES(58);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(59);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(60);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(61);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(62);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(63);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(64);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(65);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(66);
INSERT INTO INDICADOR_UNIDAD (ID) VALUES(67);
INSERT INTO INDICADOR_CENTRO (ID) VALUES(68);
INSERT INTO INDICADOR_CENTRO (ID) VALUES(69);

INSERT INTO PSM (ID,EVALUACION,NOMBREPSM,PESO,COLEGIO_ID,CURSO_ACADEMICO_ID) VALUES (1,'FINAL','PSM FINAL 18/19',1,1,1);

INSERT INTO PUNTO_CONTROL (ID,FECHA,COLEGIO_ID) VALUES (1,TO_DATE('26/06/2019', 'DD/MM/YYYY'),1);

INSERT INTO PUNTO_CONTROL_LISTA_PSM (PUNTO_CONTROL_ID,LISTA_PSM_ID) VALUES (1,1);