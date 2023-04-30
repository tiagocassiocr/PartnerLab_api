alter table cliente add ativo tinyint;
update cliente set ativo =1;

alter table tecnico add ativo tinyint;
update tecnico set ativo =1;

alter table equipamento add ativo tinyint;
update equipamento set ativo =1;

