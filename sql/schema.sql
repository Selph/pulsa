DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS subs CASCADE;
DROP TABLE IF EXISTS replies CASCADE;
DROP TABLE IF EXISTS posts CASCADE;

CREATE TABLE IF NOT EXISTS subs (
    id serial primary key,
    subname character varying(64) not null unique,
    slug character varying (64) not null unique
);

INSERT INTO subs (subname, slug) VALUES ('Málning þornar', 'malning_tornar');
INSERT INTO subs (subname, slug) VALUES ('Grænmeti', 'granmeti');
INSERT INTO subs (subname, slug) VALUES ('Háskólalífið', 'haskolalifid');

CREATE TABLE IF NOT EXISTS users (
    id serial primary key,
    realname character varying(64) not null,
    username character varying(64) not null unique,
    pass character varying(256) not null,
    email varchar(128) not null check (email <> ''),
    avatar character varying(800) default 'placeholder'
);

INSERT INTO users (realname, username, pass, email) VALUES ('anon', 'anon', 'anon', 'anon@anon.anon');

CREATE TABLE IF NOT EXISTS replies (
    id serial primary key,
    post varchar(2048) not null check (post <> ''),
    vote int not null,
    voted jsonb not null,
    replies integer[] not null,
    image character varying(800),
    audio character varying(800),
    creator serial not null,
    created TIMESTAMP with time zone not null default current_timestamp,
    updated TIMESTAMP with time zone not null default current_timestamp,
        foreign key(creator) references users(id)
);

INSERT INTO replies (post,vote,voted,replies,creator) VALUES (
                                                              'Sammála þessu!',
                                                              0,
                                                              '{}',
                                                              array[]::integer[],
                                                              1
                                                             );

CREATE TABLE IF NOT EXISTS posts
(
    id      serial primary key,
    title   character varying(128)   not null,
    post    varchar(2048)            not null check (post <> ''),
    vote    int                      not null,
    voted   jsonb                    not null,
    replies integer[]                not null,
    image   character varying(800),
    audio   character varying(800),
    sub     serial                   not null,
    creator serial                   not null,
    created TIMESTAMP with time zone not null default current_timestamp,
    updated TIMESTAMP with time zone not null default current_timestamp,
        foreign key (sub) references subs (id),
        foreign key (creator) references users (id)
);

INSERT INTO posts (title,post,vote,voted,replies,sub,creator) VALUES (
                                                                      'Vegan Kjöt = Gjöt?',
                                                                      'Mér finnst að vegan kjöt ætti að heita gjöt.',
                                                                      0,
                                                                      '{}',
                                                                      ARRAY [1],
                                                                      2,
                                                                      1
                                                                     );
