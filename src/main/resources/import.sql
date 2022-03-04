
/* Creamos algunos usuarios con sus roles */
INSERT INTO users (username, area, correo, password, enabled) VALUES ('juancarlos','recursos humanos','juan@gmail.com','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',true);
INSERT INTO users (username, area, correo, password, enabled) VALUES ('admin','recursos humanos','admin@gmail.com','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',true);



INSERT INTO authorities (id, authority) VALUES (1,'ROLE_USER');
INSERT INTO authorities (id, authority) VALUES (2,'ROLE_ADMIN');
INSERT INTO authorities (id, authority) VALUES (3,'ROLE_INVITADO');

INSERT INTO usuario_role  (idusers, idauthorities) VALUES (1,1);
INSERT INTO usuario_role (idusers, idauthorities) VALUES (2,2);

