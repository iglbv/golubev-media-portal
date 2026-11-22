create table articles (
    id bigserial primary key,
    title varchar(255) not null,
    text varchar not null,
    author varchar(255) not null,
    published_at timestamp
);

create table videos (
    id bigserial primary key,
    title varchar(255) not null,
    url varchar(2048) not null,
    duration_seconds integer not null
);

create table podcasts (
    id bigserial primary key,
    title varchar(255) not null
);

create table podcast_episodes (
    id bigserial primary key,
    podcast_id bigint not null references podcasts(id) on delete cascade,
    episode_number integer not null,
    title varchar(255) not null,
    audio_url varchar(2048) not null,
    duration_seconds integer not null,
    constraint uk_podcast_episode_number unique (podcast_id, episode_number)
);
