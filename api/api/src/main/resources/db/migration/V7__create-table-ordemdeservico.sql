create table ordemdeservico(

    id bigint not null auto_increment,
    tecnico_id bigint not null,
    cliente_id bigint not null,
    equipamento_id bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_consultas_tecnico_id foreign key(tecnico_id) references tecnico(id),
    constraint fk_consultas_cliente_id foreign key(cliente_id) references cliente(id),
    constraint fk_consultas_equipamento_id foreign key(equipamento_id) references equipamento(id)

);