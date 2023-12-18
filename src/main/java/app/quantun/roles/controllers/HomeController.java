package app.quantun.roles.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
class HomeController {



    @GetMapping("/")
    public String home(@AuthenticationPrincipal OidcUser user) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        return "Hello, " + user.getFullName() + "!<br/><br/>Authorities: " + authorities;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('Administrator')")
    public String admin(@AuthenticationPrincipal OidcUser user) {
        log.info("User Authorities: " + user.getAuthorities());
        log.info("User Attributes: " + user.getAttributes());
        log.info("User Name: " + user.getName());
        log.info("User Email: " + user.getEmail());
        log.info("User Given Name: " + user.getGivenName());
        log.info("User Family Name: " + user.getFamilyName());
        log.info("User Picture: " + user.getPicture());
        log.info("User Locale: " + user.getLocale());
        log.info("User Zone Info: " + user.getZoneInfo());
        log.info("User Updated At: " + user.getUpdatedAt());
        log.info("User Preferred Username: " + user.getPreferredUsername());
        log.info("User Sub: " + user.getSubject());
        log.info("User Email Verified: " + user.getEmailVerified());
        log.info("User Phone Number: " + user.getPhoneNumber());
        log.info("User Phone Number Verified: " + user.getPhoneNumberVerified());
        log.info("User Address: " + user.getAddress());
        return "Hello, Admin!<br/><br/><img src=" + user.getPicture() + " width=200/>";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('SCOPE_profile')")
    public Map<String, Object> profile(OAuth2AuthenticationToken authentication) {
        log.info("User Authorities: " + authentication.getAuthorities());
        log.info("User Attributes: " + authentication.getPrincipal().getAttributes());
        log.info("User Name: " + authentication.getPrincipal().getName());
        log.info("User Email: " + authentication.getPrincipal().getAttributes().get("email"));
        log.info("User Given Name: " + authentication.getPrincipal().getAttributes().get("given_name"));
        log.info("User Family Name: " + authentication.getPrincipal().getAttributes().get("family_name"));
        log.info("User Picture: " + authentication.getPrincipal().getAttributes().get("picture"));
        log.info("User Locale: " + authentication.getPrincipal().getAttributes().get("locale"));
        log.info("User Zone Info: " + authentication.getPrincipal().getAttributes().get("zoneinfo"));
        log.info("User Updated At: " + authentication.getPrincipal().getAttributes().get("updated_at"));
        log.info("User Preferred Username: " + authentication.getPrincipal().getAttributes().get("preferred_username"));
        log.info("User Sub: " + authentication.getPrincipal().getAttributes().get("sub"));
        log.info("User Email Verified: " + authentication.getPrincipal().getAttributes().get("email_verified"));
        log.info("User Phone Number: " + authentication.getPrincipal().getAttributes().get("phone_number"));
        log.info("User Phone Number Verified: " + authentication.getPrincipal().getAttributes().get("phone_number_verified"));
        log.info("User Address: " + authentication.getPrincipal().getAttributes().get("address"));
        log.info("User Birthdate: " + authentication.getPrincipal().getAttributes().get("birthdate"));
        log.info("User Gender: " + authentication.getPrincipal().getAttributes().get("gender"));

        return authentication.getPrincipal().getAttributes();
    }

}

