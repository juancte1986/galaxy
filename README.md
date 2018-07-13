Ejercicio  Mercado Libre
Tecnologías utilizadas:
-	Java 1.8
-	Maven 
-	Spring Boot
-	Elastic BeansTalk (AWS)
-	DynamoDB (AWS)
-	JUnit
-	Mockito 

A continuación se detalla como consultar los servicios:
-Consultar clima por día
http://juangalaxy-env.rnwvn8bwrs.us-east-2.elasticbeanstalk.com/clima?dia=566
-Consultar periodo de sequía
http://juangalaxy-env.rnwvn8bwrs.us-east-2.elasticbeanstalk.com/reporte?tipo=sequia
-Consultar periodo de condiciones óptimas.
http://juangalaxy-env.rnwvn8bwrs.us-east-2.elasticbeanstalk.com/reporte?tipo=optimo
-Consultar periodo de condiciones naturales:
http://juangalaxy-env.rnwvn8bwrs.us-east-2.elasticbeanstalk.com/reporte?tipo=natural
-Consultar periodo de lluvias
http://juangalaxy-env.rnwvn8bwrs.us-east-2.elasticbeanstalk.com/reporte?tipo=lluvia
-Consultar pico máximo de lluvia
http://juangalaxy-env.rnwvn8bwrs.us-east-2.elasticbeanstalk.com/reporte?tipo=picoMaxLluvia

Cabe aclarar que las credenciales para conectarse a la instancia productiva de DynamoBD no están en el repo, en caso de querer trabajar en un ambiente local se sugiere descargar la versión local de DynamoDB
https://s3-us-west-2.amazonaws.com/dynamodb-local/dynamodb_local_latest.zip
