insert into developer (id, name_company, logo) values(nextval('developer_seq'), 'Santa Monica', 'SantaMonica.jpeg');
insert into developer (id, name_company, logo) values(nextval('developer_seq'), 'SEGA', 'Sega.png');
insert into developer (id, name_company, logo) values(nextval('developer_seq'), 'CD Projekt', 'CDProjekt.png');
insert into developer (id, name_company, logo) values(nextval('developer_seq'), 'Ubisoft', 'Ubisoft.png');
insert into developer (id, name_company, logo) values(nextval('developer_seq'), 'Activision', 'Activision.png');
insert into developer (id, name_company, logo) values(nextval('developer_seq'), 'From Software', 'FromSoftware.jpeg');


insert into videogame (id, title, genre, year, budget, platforms, url_image, developer_id) values(nextval('videogame_seq'), 'Sonic Adventure', 'Platform', '1998', 'triplaA', '{Xbox;PC;PlayStation}','sonic.jpeg', '51');
insert into videogame (id, title, genre, year, budget, platforms, url_image, developer_id) values(nextval('videogame_seq'), 'God of War', 'Action', '2018', 'triplaA', '{PlayStation;PC}','GodOfWar.jpeg', '1');
insert into videogame (id, title, genre, year, budget, platforms, url_image, developer_id) values(nextval('videogame_seq'), 'Far Cry 3', 'Shooter', '2012', 'triplaA', '{Xbox;PC;PlayStation}','Farcry3.jpeg', '151');
insert into videogame (id, title, genre, year, budget, platforms, url_image, developer_id) values(nextval('videogame_seq'), 'Prince Of Persia', 'Adventure', '2010', 'triplaA', '{Xbox;PC;PlayStation}','PrinceOfPersia.jpg', '151');
insert into videogame (id, title, genre, year, budget, platforms, url_image, developer_id) values(nextval('videogame_seq'), 'Sekiro', 'Action', '2019', 'triplaA', '{Xbox;PC;PlayStation}','Sekiro.jpg', '251');


insert into award (id, type, year) values(nextval('award_seq'), 'Platform', '1998');
insert into award (id, type, year) values(nextval('award_seq'), 'Action', '2018');
insert into award (id, type, year) values(nextval('award_seq'), 'Action', '2019');
insert into award (id, type, year) values(nextval('award_seq'), 'Adventure', '2010');
