create table post
(
     post_id bigint generated by default as identity,
     title varchar(255),
     content varchar(255),
     primary key (post_id)
);