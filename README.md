# kubsei-essential-lib-java

Parent pom of every kubsei service and lib, plus the shared `essential-*` modules.

## As parent

```xml
<parent>
    <groupId>com.kubsei</groupId>
    <artifactId>kubsei-essential-lib</artifactId>
    <version>0.1.0-SNAPSHOT</version>
    <relativePath/>
</parent>
```

It extends `spring-boot-starter-parent` and adds:

- Spring Cloud BOM (`spring-cloud.version`) and the versions of the `essential-*` modules.
- Lombok as annotation processor.
- `openapi-generator-maven-plugin` preconfigured (Spring Boot 4, Jackson 3, delegate pattern, bean validation):
  a contract lib only sets `inputSpec`, `apiPackage` and `modelPackage`.

## Modules

| Module | What it does |
|---|---|
| `kubsei-essential-security` | Auto-configures the kubsei JWT verification: HS256 key, `JwtDecoder` (servlet) or `ReactiveJwtDecoder` (reactive) checking the issuer, and the `roles` claim as authorities. Services only declare their filter chain with `oauth2ResourceServer(jwt)`. |

```yaml
kubsei:
  security:
    jwt:
      secret: ${JWT_SECRET}      # required, >= 32 bytes, same in every service
      issuer: ${JWT_ISSUER:kubsei}
```

Published to GitHub Packages on every push to `main`.
