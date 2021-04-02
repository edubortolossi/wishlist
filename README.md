# Projeto de Lista de Desejos

#Como executar


#Mongo Local
Inicializar container do docker com o comando:

`docker run -d -v /data/db:/data/db --name mymongo --net=host mongo:3.4.4 --bind_ip 127.0.0.1 --port 27000`