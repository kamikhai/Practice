INSERT INTO public.academic_group (numeric)
VALUES ('11-802'),
       ('11-803');

INSERT INTO public.app_user (email, full_name, role, photo_path, pass_hash)
VALUES ('test@test.test', 'Тест Тестович', 'STUDENT', '/img/empty_user.jpg', '$2y$10$3iKuQFwLBC/8XS7k7jr5secLLhK.IGdjymk1jgSEdRtBhcWeWnytS'),
       ('123@test.test', 'Тестовый студент', 'STUDENT', '/img/empty_user.jpg', '$2y$10$3iKuQFwLBC/8XS7k7jr5secLLhK.IGdjymk1jgSEdRtBhcWeWnytS'),
       ('test@123.test', 'Тестовый препод', 'TEACHER', '/img/empty_user.jpg', '$2y$10$3iKuQFwLBC/8XS7k7jr5secLLhK.IGdjymk1jgSEdRtBhcWeWnytS'),
       ('test@test.123', 'Тестовый админ', 'ADMIN', '/img/empty_user.jpg', '$2y$10$3iKuQFwLBC/8XS7k7jr5secLLhK.IGdjymk1jgSEdRtBhcWeWnytS');

INSERT INTO public.job_profile (title)
VALUES ('Java-разработчик'),
       ('Web-вёрстка');

INSERT INTO public.student (description, user_id, group_id, job_profile_id, work_experience, link)
VALUES ('я первый', 1, 1, 1, '-', 'https://vk.com/id0'),
       ('я второй', 2, 2, 2, '-', 'https://vk.com/id0');

INSERT INTO public.teacher (user_id, information, position, link)
VALUES (3, 'закончил 9 классов', 'хуй с горы', 'https://vk.com/id0');

INSERT INTO public.teacher_curated_groups (teacher_user_id, curated_groups_id)
VALUES (3, 1),
       (3, 2);

INSERT INTO public.tag (name)
VALUES ('Java'),
       ('Spring'),
       ('Javascript');

INSERT INTO public.competence (description, confirmed_by_user_id, student_user_id)
VALUES ('я знаю яву', 3, 1),
       ('весну тоже', 3, 1),
       ('я знаю джаву', null, 2),
       ('также скрипт', 3, 2);

INSERT INTO public.competence_tags (competence_id, tags_id)
VALUES (1, 1),
       (2, 2),
       (3, 1),
       (4, 3);

INSERT INTO public.project (title, student_user_id, description)
VALUES ('классный стартап', 1, 'какое-то говно'),
       ('крутейший проект', 2, 'какое-то говно');

INSERT INTO public.project_tags (project_id, tags_id)
VALUES (1, 2),
       (1, 2),
       (2, 3);