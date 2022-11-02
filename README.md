#Sobre la applicacion  

###Autor:Jiaming Shi  
###Proyecto:Bantumi  
###Fecha:02/11/2022  
###github:https://github.com/1257198442/Bantumi  

###Versiones históricas  
###1.version_sqlit: La base de datos utilizada en esta versión es sqlite.  
###2.version_room: La base de datos utilizada en esta versión es room.  
###3.final_version: Esta versión es la versión final con 3 nuevas características.  

##Function  
###1、Reiniciar el juego: cuando el jugador haga clic en el botón de Reiniciar, el sistema determinará si el juego actual ha comenzado y si lo ha hecho, entonces preguntará al jugador si quiere comenzar un nuevo juego.  
###2、Guardar partida: cuando el jugador pulsa el botón para guardar, el sistema genera una cadena json del estado actual del tablero, que se almacena localmente a través de la función de gestión de archivos del dispositivo.  
###3、Reanudar la partida: Cuando un jugador hace clic en el botón Ajustes, el sistema determinará si la partida actual ha comenzado, si ha comenzado preguntará al jugador si quiere descartar la partida actual y volver a la partida guardada.  
###4、Modificar el nombre del jugador: Antes de que comience el juego, los jugadores pueden acceder a la configuración haciendo clic en el botón Modificar nombre para cambiar el nombre del jugador, haga clic en el botón Confirmar para guardar los cambios. Cuando se introduce como vacío, el nombre del jugador se cambiará por el nombre por defecto.  
###5、Guardando la historia: Cuando el juego termina, se genera un dato de victoria que se guarda en la room para que sea persistente.  
###6、Ver el historial: Al hacer clic en el botón "Mejores resultados", los jugadores serán llevados a la pantalla del historial, que muestra los 10 mejores registros por número de semillas ganadas.  
###7、Nueva característica 1: Cuando un jugador hace clic en el botón "Cambiar el recuento inicial de semillas", aparecerá un cuadro emergente para solicitar al jugador que cambie el recuento de semillas; una vez completado el cambio, éste tendrá efecto al comienzo de la siguiente partida.  
###8、Nueva característica 2: Cuando un jugador hace clic en el cambio de "Modificar el orden de juego", aparecerá un cuadro de aviso que le pedirá al jugador que seleccione el jugador que tomará el primer movimiento, después de confirmar, el sistema determinará si el juego actual ha comenzado y si ha comenzado el cambio tendrá efecto en el próximo juego.  
###9、Nueva característica 3: Cuando un jugador está en la página del historial, hay un componente "spinner" en la parte superior de la página que contiene los nombres de todos los jugadores que han jugado la partida. Cuando se selecciona el jugador correspondiente, la lista siguiente mostrará los 10 mejores resultados de las victorias del jugador por su nombre.  



