# Java + Spring Boot E-Commerce | Tarvin Shop Backend
*Before reading this documentation is advised to read on the basics of OAuth 2*

This project leverages modern OAuth 2.0 + JWT in Spring. [No social logins](https://www.webfx.com/blog/web-design/social-logins#616723179a361-20) for now.

JWT boilerplate is reduced to the bare minimum by using the new [OAuth 2 Resource Server](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html).
In this project we don't have an Authorization Server so the tokens are self issued. 
You can see how this is implemented in the [JWTConfig](src/main/java/com/jaimayal/tarvinshop/AuthSystem/config/JwtConfig.java), we define a custom JwtDecoder bean, by doing this, we override the default behavior;
instead of going to the authorization server for a token the resource server is going to generate it itself. 

For further understanding of this mechanism please consult [how to override the default resource server configuration](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html#oauth2resourceserver-jwt-sansboot).

If you want to decouple the Authorization Server, please refer to [this project](https://github.com/qq253498229/spring-boot-oauth2-example)

### JWT Generation
You can check how tokens are generated in the [JWTIssuerService](src/main/java/com/jaimayal/tarvinshop/AuthSystem/service/JwtIssuerService.java) class.
Since they use the RSA256 Algorithm, you must generate your own private and public keys. You can use `openssl` for this:


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