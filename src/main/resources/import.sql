
insert into cuenta_fb_developer(id_cuenta,access_token,id_aplicacion,secret_key) values(1000001,"EAAp4gTbHgEoBAAONi8R4894QdXyjnNvax1ZAp5XftALhyS2HfuyCxUNQIFgTSZAuix0myV3qXG9a0ovOuEzSH1gnF9vfzzu9Y5U5C6aFAIZCuxTUf49l1oxaH92Pxno1nYlnVqspHP2eZA84AZCvkMkRFMTj80JPIsbeXs42DXXxVW7QynuOT","2947246132265034","16d7d0bc793f0f565653b29b30c32703");

insert into cuentafb (id_cuentafb,nombre_cuenta,cuenta_fb_developer) values(4521452,"Carulla",1000001);

insert into rol values (1,'ROLE_USER');

insert into estado (id_estado,nombre_estado) values (1,"Arriba");

insert into estado (id_estado,nombre_estado) values (2,"Caido");

insert into usuario (cargo,email,nombre) values ('Administrador','johan.jara@deeploy.co','Johan Andrés Jara Ardila')

insert into anuncio (id_anuncio,link_anuncio,cuenta_fb) values ("A542011","https://www.carulla.com/agua-brisa-garrafa-6-lts-426254/p",4521452);

insert into anuncio (id_anuncio,link_anuncio,cuenta_fb) values ("A542012","https://www.carulla.com/agua-brisasa-garrafa-6-lts-426254/p",4521452);

insert into anuncio (id_anuncio,link_anuncio,cuenta_fb) values ("A542013","https://www.mercadolibre.com.co/",4521452);

insert into anuncio (id_anuncio,link_anuncio,cuenta_fb) values ("A542014","https://www.alkosto.com/audifonos-samsung-galaxy-buds-negro/p/8806090203435",4521452);