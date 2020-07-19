create table academic_group (
                                id bigint not null auto_increment,
                                numeric varchar(255) not null,
                                constraint academic_group_pkey
                                    primary key (id),
                                constraint uk_bxfwwl3avu6nwue0lxybcgr3f
                                    unique (numeric)
);
create table app_user (
                          id bigint not null auto_increment,
                          email varchar(255) not null,
                          full_name varchar(255) not null,
                          pass_hash varchar(255) not null,
                          role varchar(255) not null,
                          photo_path varchar(255) null,
                          constraint app_user_pkey
                              primary key (id)
);
create table teacher (
                         user_id bigint not null,
                         information varchar(255) null,
                         position varchar(255) null,
                         link varchar(255),
                         constraint teacher_pkey
                             primary key (user_id),
                         constraint fk2fun0xdr4vew4040wk1o3yk9j
                             foreign key (user_id)
                                 references app_user
);
create table teacher_curated_groups (
                                        teacher_user_id bigint not null,
                                        curated_groups_id bigint not null,
                                        constraint fkj90uhn6u47spl2axxlhxvdwq9
                                            foreign key (teacher_user_id)
                                                references teacher,
                                        constraint fkrrsle0mlcvwdxsi0xa8upmvcj
                                            foreign key (curated_groups_id)
                                                references academic_group
);
create table job_profile (
                             id bigint not null auto_increment,
                             title varchar(255) not null,
                             constraint job_profile_pkey
                                 primary key (id),
                             constraint uk_gr1niur6cwaneph00pt1y7a60
                                 unique (title)
);
create table student (
                         description clob not null,
                         user_id bigint not null,
                         group_id bigint not null,
                         link varchar(255),
                         work_experience varchar(255),
                         job_profile_id bigint null,
                         constraint student_pkey
                             primary key (user_id),
                         constraint fkd5m4yhlpu19okmb1lpc8hsulv
                             foreign key (user_id)
                                 references app_user,
                         constraint fkt931avueic9fdo5y54ytfahxl
                             foreign key (group_id)
                                 references academic_group,
                         constraint fk48kdill030qo5q05chqqjqtg3
                             foreign key (job_profile_id)
                                 references job_profile
);
create table competence (
                            id bigint not null,
                            description varchar(255) not null,
                            confirmed_by_user_id bigint null,
                            student_user_id bigint not null,
                            constraint competence_pkey
                                primary key (id),
                            constraint fkbit8cv8fen01ckxq69053jn3v
                                foreign key (confirmed_by_user_id)
                                    references teacher,
                            constraint fk9aqryjakn06ksge4jgqvs51no
                                foreign key (student_user_id)
                                    references student
);
create table project (
                         id bigint not null auto_increment,
                         description clob not null,
                         student_user_id bigint not null,
                         title varchar(255) not null,
                         constraint project_pkey
                             primary key (id),
                         constraint fk8mjacxhp1049qevui0dlmji28
                             foreign key (student_user_id)
                                 references student
);
create table persistent_logins (
                                   username varchar(64) not null,
                                   series varchar(64) not null,
                                   token varchar(64) not null,
                                   last_used timestamp not null,
                                   constraint persistent_logins_pkey
                                       primary key (series)
);
create table spring_session (
                                primary_id char(36) not null,
                                session_id char(36) not null,
                                creation_time bigint not null,
                                last_access_time bigint not null,
                                max_inactive_interval int not null,
                                expiry_time bigint not null,
                                principal_name varchar(100) null,
                                constraint spring_session_pk
                                    primary key (primary_id)
);
create unique index spring_session_ix1 on spring_session(session_id);
create index spring_session_ix2 on spring_session(expiry_time);
create index spring_session_ix3 on spring_session(principal_name);
create table spring_session_attributes (
                                           session_primary_id char(36) not null,
                                           attribute_name varchar(200) not null,
                                           attribute_bytes blob not null,
                                           constraint spring_session_attributes_fk
                                               foreign key (session_primary_id)
                                                   references spring_session on delete cascade,
                                           constraint spring_session_attributes_pk
                                               primary key (
                                                            session_primary_id,
                                                            attribute_name
                                                   )
);
create table student_competences (
                                     student_user_id bigint not null,
                                     competences_id bigint not null,
                                     constraint fkn6bful2wadw55mq5ulr19p2on
                                         foreign key (student_user_id)
                                             references student,
                                     constraint uk_m4yytt63rt5i1kqm07j0mf9io
                                         unique (competences_id),
                                     constraint fkylmt9h3u3l7waot7r4786h0a
                                         foreign key (competences_id)
                                             references competence
);
create table tag (
                     id bigint not null auto_increment,
                     name varchar(255) not null,
                     constraint tag_pkey
                         primary key (id)
);
create unique index tag_name_uindex on tag(name);
create table competence_tags (
                                 competence_id bigint not null,
                                 tags_id bigint not null,
                                 constraint fk10h3kgepm3ddqxuj7xw4x4ywd
                                     foreign key (competence_id)
                                         references competence,
                                 constraint fknsoxj8cp3d3nvv2lrn0mo46q5
                                     foreign key (tags_id)
                                         references tag
);
create table project_tags (
                              project_id bigint not null,
                              tags_id bigint not null,
                              constraint fkfvy64usu7e9x7ev6obh91q0qe
                                  foreign key (project_id)
                                      references project,
                              constraint fkl7ukpu2t0cd2rlc8bl5o7igtw
                                  foreign key (tags_id)
                                      references tag
);
