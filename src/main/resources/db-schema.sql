create table updates
(
    id         bigserial not null
        constraint updates_pk
            unique,
    created_at timestamp,
    raw_update jsonb
);
alter table updates
    owner to mafia_stats_user;
create
index updates_created_at_index
    on updates (created_at);
create
index updates_id_index
    on updates (id);
