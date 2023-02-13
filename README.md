# Java + Spring Boot E-Commerce | Tarvin Shop Backend
*Before reading this documentation is advised to read on the basics of OAuth 2 and JWT*

## Auth System
This project leverages modern OAuth 2.1 (Resource Server) + JWT in Spring. [No social logins](https://www.webfx.com/blog/web-design/social-logins#616723179a361-20) for now.

This project uses the new [spring-boot-starter-oauth2-resource-server]((https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html)) to avoid all the typical boilerplate
code that you will find in most JWT implementations (Filters).

In this project I'm not using a custom Authorization Server so the tokens are "self issued". 
You can see how this is implemented in the [JWTConfig](src/main/java/com/jaimayal/tarvinshop/AuthSystem/config/JwtConfig.java) class, 
Overriding the default behavior of the Resource Server.

For further understanding of this mechanism please consult [how to override the default resource server configuration](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html#oauth2resourceserver-jwt-sansboot).

If you want to decouple the Authorization Server, please refer to [this repo](https://github.com/qq253498229/spring-boot-oauth2-example) and to 
[this article](https://www.baeldung.com/keycloak-embedded-in-spring-boot-app). 
Also now there's an ongoing project to implement an Authorization Server on Spring, please, check [this project](https://spring.io/projects/spring-authorization-server) :).

### JWT Generation
You can check how tokens are generated in the [JWTService](src/main/java/com/jaimayal/tarvinshop/AuthSystem/service/JwtService.java) class.
Since they use the [RSA256 Algorithm](https://stackoverflow.com/questions/38588319/understanding-rsa-signing-for-jwt#answer-44352675), you must generate your own private and public keys. You can use `openssl` for this:


#### How to generate my own public.ky and private.ky files
1. Generate your keypair file (It contains both Private and Public key)
```Bash
openssl genrsa -out keypair.pem 2048
```

2. Extract your public key into "public.ky" file
```Bash
openssl rsa -in keypair.pem -pubout -out public.ky
```

3. Extract your private key into the "private.ky" file
```Bash
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.ky
```

4. Put these two files inside the [resources](src/main/resources) folder.