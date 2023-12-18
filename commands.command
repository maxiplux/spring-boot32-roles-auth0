auth0 apps create   --name "SpringRoles2023Auth0"   --description "Spring Boot 3.2 and Java 21, with Auth0"  --type regular   --callbacks http://localhost:8080/login/oauth2/code/okta  --logout-urls http://localhost:8080  --reveal-secrets

exports.onExecutePostLogin = async (event, api) => {
  const namespace = "https://spring-boot.example.com";
  if (event.authorization) {
    api.idToken.setCustomClaim("preferred_username", event.user.email);
    api.idToken.setCustomClaim(`${namespace}/roles`, event.authorization.roles);
    api.accessToken.setCustomClaim(
      `${namespace}/roles`,
      event.authorization.roles
    );
  }
};



https://developer.auth0.com/resources/labs/authorization/rbac-in-spring-boot#recap
