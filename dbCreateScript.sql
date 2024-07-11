CREATE TABLE track_type (
    id SERIAL PRIMARY KEY,
    name varchar not null
);

CREATE TABLE subscribe (
    id serial primary key,
    name varchar not null,
    money numeric not null
);

CREATE TABLE playlist_type (
    id serial PRIMARY KEY,
    name varchar not null,
);

CREATE TABLE  role (
    id serial primary key,
    name varchar not null
);

CREATE TABLE client (
    id serial primary key,
    name varchar not null,
    email varchar not null UNIQUE,
    password varchar not null,
    role int REFERENCES role (id),
    subscribe int REFERENCES subscribe (id),
    subscribe_end date
);

CREATE TABLE playlist(
    id serial primary key,
    title varchar not null,
    author int REFERENCES client (id),
    description varchar not null,
    playlist_type int references playlist_type (id),
    cover varchar not null
);

CREATE TABLE track (
    id serial primary key,
    title varchar not null,
    author int references client(id),
    album varchar,
    cover varchar not null,
    length time not null,
    track_type int references track_type(id),
    tags varchar not null
);

CREATE TABLE tracking_playlist(
    track int references track(id) ,
    playlist int references playlist(id),
    primary key (track, playlist)
);

CREATE TABLE support(
    id serial primary key,
    author int references client(id),
    title varchar not null,
    text varchar not null,
    date date not null
);